<template>
  <div class="cover_conter">
    <el-tabs type="border-card" class="cover_tabs">
      <el-tab-pane  label="全国疫情数据(含港澳台)"   class="cover_tabs-pane">
        <div  class="cover_data_china">
          <div>
            <span>境外输入</span>
            <span style="color: #FFA352">1231</span>
            <span style="color: #FFA352"><text style="color: grey">较昨日</text>+1321</span>
          </div>
          <div>
            <span>无症状感染者</span>
            <span style="color:  #791618;">1231</span>
            <span style="color:  #791618;"><text style="color: grey">较昨日</text>+1321</span>
          </div>
          <div>
            <span>现有确诊</span>
            <span style="color: #E44A3D">1231</span>
            <span style="color: #E44A3D"><text style="color: grey">较昨日</text>+1321</span>
          </div>
          <div>
            <span>累计确诊</span>
            <span style="color:#A31D13;">1231</span>
            <span style="color:#A31D13;"><text style="color: grey">较昨日</text>+1321</span>
          </div>
          <div>
            <span>累计死亡</span>
            <span style="color: #333333">1231</span>
            <span style="color: #333333"><text style="color: grey">较昨日</text>+1321</span>
          </div>
          <div>
            <span>累计治愈</span>
            <span style="color:#34AA70;">1231</span>
            <span style="color:#34AA70;"><text style="color: grey">较昨日</text>+1321</span>
          </div>
        </div>
        <div class="bottom">
          <span>截止1321231321</span>
          <span>？数据说明</span>

        </div>
      </el-tab-pane>
      <el-tab-pane label="当地数据" class="cover_tabs-pane">
        当地数据
      </el-tab-pane>
    </el-tabs>
  </div>

  <div class="canvars" ref="myRef"></div>
</template>


<script setup name="Nov">
import  echarts from 'echarts'
import 'echarts/map/js/china'
import {ref, onMounted, reactive} from "vue";

import {novdatalist, novchinatotallist} from "@/api/campus/nov";

const myRef = ref(null) // 获取dom实例
onMounted(() => {
  novdatalist().then(res =>{
    var datas = res.data;
    for (let i = 0; i < datas.length; i++) {
      data[i].name = datas[i].name;
      data[i].value = datas[i].value;
    }
    renderChart(data);
  })
  // console.log(data)

})
const data = [
  {
    name: "北京",
    value: 212,
  },
  {
    name: "天津",
    value: 60,
  },
  {
    name: "上海",
    value: 208,
  },
  {
    name: "重庆",
    value: 337,
  },
  {
    name: "河北",
    value: 126,
  },
  {
    name: "河南",
    value: 675,
  },
  {
    name: "云南",
    value: 117,
  },
  {
    name: "辽宁",
    value: 74,
  },
  {
    name: "黑龙江",
    value: 155,
  },
  {
    name: "湖南",
    value: 593,
  },
  {
    name: "安徽",
    value: 480,
  },
  {
    name: "山东",
    value: 270,
  },
  {
    name: "新疆",
    value: 29,
  },
  {
    name: "江苏",
    value: 308,
  },
  {
    name: "浙江",
    value: 829,
  },
  {
    name: "江西",
    value: 476,
  },
  {
    name: "湖北",
    value: 13522,
  },
  {
    name: "广西",
    value: 139,
  },
  {
    name: "甘肃",
    value: 55,
  },
  {
    name: "山西",
    value: 74,
  },
  {
    name: "内蒙古",
    value: 34,
  },
  {
    name: "陕西",
    value: 142,
  },
  {
    name: "吉林",
    value: 42,
  },
  {
    name: "福建",
    value: 179,
  },
  {
    name: "贵州",
    value: 56,
  },
  {
    name: "广东",
    value: 797,
  },
  {
    name: "青海",
    value: 15,
  },
  {
    name: "西藏",
    value: 1,
  },
  {
    name: "四川",
    value: 282,
  },
  {
    name: "宁夏",
    value: 34,
  },
  {
    name: "海南",
    value: 79,
  },
  {
    name: "台湾",
    value: 10,
  },
  {
    name: "香港",
    value: 15,
  },
  {
    name: "澳门",
    value: 9,
  },
];

const renderChart = (list)=>{
  const myChart = echarts.init(myRef.value)
  var option = {
    title: {
      text: "中国疫情图",
      left: "center",
    },
    tooltip: {
      trigger: "item",
    },
    legend: {
      orient: "vertical",
      left: "left",
      data: ["中国疫情图"],
    },
    visualMap: {
      type: "piecewise",
      pieces: [
        { min: 10000, max: 100000000, label: "≥ 10000 人", color: "#7f1100",},
        { min: 1000, max: 9999, label: "1000 - 9999 人", color: "#bd1316" },
        { min: 500, max: 999, label: "500 - 999 人", color: "#e64b45" },
        { min: 100, max: 499, label: "100 - 499 人", color: "#ff8c71" },
        { min: 10, max: 99, label: "10 - 99 人", color: "#fdd2a0" },
        { min: 1, max: 9, label: "1 - 9 人", color: "#fff2cf" },
        { min: 0, max: 0, label: "0", color: "rgb(255,255,255)" },
      ],
      color: ["#E0022B", "#E09107", "#A3E00B"],
    },
    toolbox: {
      show: true,
      orient: "vertical",
      left: "right",
      top: "center",
      feature: {
        mark: { show: true },
        dataView: { show: false, readOnly: false },
        restore: { show: false },
        saveAsImage: { show: true },
      },
    },
    roamController: {
      show: true,
      left: "right",
      //   mapTypeControl: {
      //     china: true,
      //   },
    },
    // bmap: {
    //   //
    // },
    geo: {
      //地理坐标系组件用于地图的绘制
      map: "china",
      roam: false, //不开启缩放和平移
      label: {
        normal: {
          show: true,
          fontSize: "10",
          color: "rgba(0,0,0,0.7)",
        },
      },
      itemStyle: {
        normal: {
          borderColor: "rgba(0, 0, 0, 0.2)",
        },
        emphasis: {
          areaColor: "#F3B329", //鼠标选择区域颜色
          shadowOffsetX: 0,
          shadowOffsetY: 0,
          shadowBlur: 20,
          borderWidth: 0,
          shadowColor: "rgba(0, 0, 0, 0.5)",
        },
      },
    },
    series: [
      {
        name: "确诊数",
        type: "map", //图表类型
        geoIndex: 0,
        data: list, //图表的数据
      },
      //   {
      //     name: "确诊数",
      //     // type: "scatter",
      //     // coordinateSystem: "bmap",
      //     type: "map",
      //     mapType: "china",
      //     data: data,
      //     // symbolSize: function (val) {
      //     //   return val[2] / 10;
      //     // },
      //     // encode: {
      //     //   value: 2,
      //     // },
      //     // label: {
      //     //     formatter: '{b}',
      //     //     position: 'right',
      //     //     show: false
      //     // },
      //     itemStyle: {
      //       color: "purple",
      //     },

      // emphasis: {
      //   label: {
      //     show: true,
      //   },
      // },
      //   },
      //   {
      //     name: "确诊数",
      //     type: "map",
      //     mapType: "china",
      //     roam: false,
      //     label: {
      //       show: true,
      //       color: "rgb(249, 249, 249)",
      //     },

      //   },
    ],
  };
  myChart.setOption(option)
}
</script>

<style scoped>
.canvars {
  width: 100%;
  height: 800px;
}

 .cover_tabs{
   position: absolute;
   z-index: 99999;
  background-color: #f3f3f3;
  width: 27%;
  border-bottom-right-radius: 30px;

}
 .cover_data_china{
   display: flex;
   justify-content: center;
   flex-wrap: wrap;
 }
.cover_data_china div{
  width: 33%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 30px;
  letter-spacing: 1px;
}
.cover_data_china div span:nth-of-type(1){
  font-size: 16px;
}
.cover_data_china div span:nth-of-type(2){
  font-size: 20px;
  margin: 10px 0;
}
.cover_data_china div span:nth-of-type(3){
  font-size: 16px;
}
.bottom{
  margin: 0 20px;
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  align-items: center;
}

</style>