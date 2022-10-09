<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="newTitle">
        <el-input
          v-model="queryParams.newTitle"
          placeholder="请输入标题"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作者" prop="newAuthor">
        <el-input
          v-model="queryParams.newAuthor"
          placeholder="请输入作者"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="来源" prop="newSource">
        <el-input
          v-model="queryParams.newSource"
          placeholder="请输入来源"
          clearable
          @keyup.enter="handleQuery"
        />
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
          v-hasPermi="['campus:news:add']"
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
          v-hasPermi="['campus:news:edit']"
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
          v-hasPermi="['campus:news:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['campus:news:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="newsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="新闻id" align="center" prop="newId" />
      <el-table-column label="标题" align="center" prop="newTitle" />
      <el-table-column label="作者" align="center" prop="newAuthor" />
      <el-table-column label="内容" align="center" prop="newContent" :show-overflow-tooltip="true"/>
      <el-table-column label="来源" align="center" prop="newSource" />
      <el-table-column label="点击数" align="center" prop="newHits" />
      <el-table-column label="图片地址" align="center" prop="image" width="100">
        <template #default="scope">
          <image-preview :src="scope.row.image" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['campus:news:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['campus:news:remove']"
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

    <!-- 添加或修改新闻对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="newsRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="newTitle">
          <el-input v-model="form.newTitle" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="作者" prop="newAuthor">
          <el-input v-model="form.newAuthor" placeholder="请输入作者" />
        </el-form-item>
        <el-form-item label="内容">
          <el-input v-model="form.newContent" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="来源" prop="newSource">
          <el-input v-model="form.newSource" placeholder="请输入来源" />
        </el-form-item>
        <el-form-item label="点击数" prop="newHits">
          <el-input v-model="form.newHits" placeholder="请输入点击数" />
        </el-form-item>
        <el-form-item label="图片地址">
          <image-upload v-model="form.image"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script setup name="news">
import { listNews, getNews, delNews, addNews, updateNews } from "@/api/campus/news";

const {proxy} = getCurrentInstance()

const newsList = ref([]);
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
    newTitle: null,
    newAuthor: null,
    newSource: null,
  },
  rules: {
    newTitle: [{ required: true, message: "新闻标题不能为空", trigger: "blur" }],
    newAuthor: [{ required: true, message: "作者不能为空", trigger: "blur" }],
    newSource: [{ required: true, message: "来源不能为空", trigger: "blur" }],
  },
});

const { queryParams, form, rules } = toRefs(data);

/** 查询离校管理列表 */
function getList() {
  loading.value = true;
  listNews(queryParams.value).then(response => {
    newsList.value = response.rows;
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
    newId: null,
    newTitle: null,
    newAuthor: null,
    newContent: null,
    newSource: null,
    newHits: null,
    image: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  };
  proxy.resetForm("newsRef");
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
  ids.value = selection.map(item => item.newId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}
/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加新闻";
}
/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const newId = row.newId || ids.value;
  getNews(newId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改新闻";
  });
}
/** 提交按钮 */
function submitForm() {
  proxy.$refs["newsRef"].validate(valid => {
    if (valid) {
      if (form.value.newId != undefined) {
        updateNews(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addNews(form.value).then(response => {
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
  const newId = row.newId || ids.value;
  proxy.$modal.confirm('是否确认删除打卡编号为"' + newId + '"的数据项？').then(function() {
    return delNews(newId);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}
/** 导出按钮操作 */
function handleExport() {
  proxy.download("campus/news/export", {
    ...queryParams.value
  }, `news_${new Date().getTime()}.xlsx`);
}

getList();

</script>
