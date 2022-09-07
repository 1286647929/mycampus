<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="studentName">
        <el-input
          v-model="queryParams.studentName"
          placeholder="请输入姓名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学院名称" prop="collegeName">
        <el-input
          v-model="queryParams.collegeName"
          placeholder="请输入学院名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="班级" prop="studentClass">
        <el-input
          v-model="queryParams.studentClass"
          placeholder="请输入班级"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="离校原因" prop="levCourse">
        <el-input
          v-model="queryParams.levCourse"
          placeholder="请输入离校原因"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="行程轨迹" prop="travel">
        <el-input
          v-model="queryParams.travel"
          placeholder="请输入行程轨迹"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="离校时间" prop="levTime">
        <el-date-picker clearable
          v-model="queryParams.levTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择离校时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="返校时间" prop="reschoolTime">
        <el-date-picker clearable
          v-model="queryParams.reschoolTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择返校时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['campus:levscl:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['campus:levscl:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['campus:levscl:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['campus:levscl:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="levsclList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="学号" align="center" prop="studentId" />
      <el-table-column label="姓名" align="center" prop="studentName" />
      <el-table-column label="学院名称" align="center" prop="collegeName" />
      <el-table-column label="班级" align="center" prop="studentClass" />
      <el-table-column label="离校原因" align="center" prop="levCourse" />
      <el-table-column label="行程轨迹" align="center" prop="travel" />
      <el-table-column label="离校时间" align="center" prop="levTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.levTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="返校时间" align="center" prop="reschoolTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.reschoolTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审批状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['campus:levscl:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['campus:levscl:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
        v-show="total > 0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
    />

    <!-- 添加或修改离校管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="levsclRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="studentName">
          <el-input v-model="form.studentName" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="学院名称" prop="collegeName">
          <el-input v-model="form.collegeName" placeholder="请输入学院名称" />
        </el-form-item>
        <el-form-item label="班级" prop="studentClass">
          <el-input v-model="form.studentClass" placeholder="请输入班级" />
        </el-form-item>
        <el-form-item label="离校原因" prop="levCourse">
          <el-input v-model="form.levCourse" placeholder="请输入离校原因" />
        </el-form-item>
        <el-form-item label="行程轨迹" prop="travel">
          <el-input v-model="form.travel" placeholder="请输入行程轨迹" />
        </el-form-item>
        <el-form-item label="离校时间" prop="levTime">
          <el-date-picker clearable
            v-model="form.levTime"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择离校时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="返校时间" prop="reschoolTime">
          <el-date-picker clearable
            v-model="form.reschoolTime"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择返校时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script setup name="Levscl">
import { listLevscl, getLevscl, delLevscl, addLevscl, updateLevscl } from "@/api/campus/levscl";

import useUserStore from '@/store/modules/user'

const {proxy} = getCurrentInstance()

const levsclList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    studentName: null,
    collegeName: null,
    studentClass: null,
    levCourse: null,
    travel: null,
    levTime: null,
    reschoolTime: null,
    status: null
  },
  rules: {
    studentName: [{ required: true, message: "学生姓名不能为空", trigger: "blur" }],
    collegeName: [{ required: true, message: "学院名称不能为空", trigger: "blur" }],
    studentClass: [{ required: true, message: "班级不能为空", trigger: "blur" }],
  },
});

const { queryParams, form, rules } = toRefs(data);

/** 查询离校管理列表 */
function getList() {
  loading.value = true;
  listLevscl(queryParams.value).then(response => {
    levsclList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}
/** 取消按钮 */
function cancel() {
  open.value = false;
  reset();
}
/** 表单重置 */
function reset() {
  form.value = {
    studentId: null,
    studentName: null,
    collegeName: null,
    studentClass: null,
    levCourse: null,
    travel: null,
    levTime: null,
    reschoolTime: null,
    status: "0"
  };
  proxy.resetForm("levsclRef");
}
/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}
/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}
/** 多选框选中数据 */
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.studentId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}
/** 新增按钮操作 */
function handleAdd() {
  reset();
  //获取用户信息
  useUserStore().getInfo().then(userinfo => {
    // console.log(userinfo)
    form.value.studentName = userinfo.user.nickName;
    form.value.collegeName = userinfo.user.dept.deptName;
    form.value.studentClass = userinfo.user.className;
  })
  open.value = true;
  title.value = "添加离校管理";
}
/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const studentId = row.studentId || ids.value;
  getLevscl(studentId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改离校管理";
  });
}
/** 提交按钮 */
function submitForm() {
  proxy.$refs["levsclRef"].validate(valid => {
    if (valid) {
      if (form.value.studentId != undefined) {
        updateLevscl(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addLevscl(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}
/** 删除按钮操作 */
function handleDelete(row) {
  const studentId = row.studentId || ids.value;
  proxy.$modal.confirm('是否确认删除打卡编号为"' + studentId + '"的数据项？').then(function() {
    return delLevscl(studentId);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}
/** 导出按钮操作 */
function handleExport() {
  proxy.download("campus/levscl/export", {
    ...queryParams.value
  }, `levscl${new Date().getTime()}.xlsx`);
}

getList();
</script>
