<template>
	<view>
		<view class="wrap">
			<mpvue-echarts id="main" ref="mapChart" :echarts="echarts" @onInit="renderMap" />
		</view>
	</view>
</template>

<script>
	import * as echarts from '../../echarts/echarts.min.js'; /*echarts.min.js为在线定制*/
	import * as chinaJson from '../../echarts/map/json/china.json'; /*echart.min.js为在线定制*/
	import mpvueEcharts from '../../mpvue-echarts';

	import {
		GetNovList
	} from '@/api/nov/nov.js'

	export default {
		data() {
			return {
				echarts,
				data: [{
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
				]
			};
		},
		components: {
			mpvueEcharts
		},
		onLoad() {
			this.getnovlist()
		},
		methods: {
			renderMap(e) {
				const that = this;
				var mydata = that.data;
				let {
					canvas,
					width,
					height
				} = e;
				width = width - 20;
				echarts.setCanvasCreator(() => canvas);
				const chart = echarts.init(canvas, null, {
					width: width,
					height: height
				})
				echarts.registerMap('china', chinaJson);
				canvas.setChart(chart)
				var optionMap = {
					title: {
						text: "中国疫情图",
						x: 'center'
					},
					tooltip: {
						trigger: 'item',
						formatter: '{b}:{c}'
					},
					//左侧小导航图标
					visualMap: {
						// min: 0,
						// max: 25,
						left: 'left',
						top: 'bottom',
						// orient:'horizontal',
						// text: ['高', '低'], //取值范围的文字
						inRange: {
							color: ['#fff8ed', '#f2b95b'] //取值范围的颜色
						},
						show: true, //图注
						pieces: [{
								min: 10000,
								max: 100000000,
								label: "≥ 10000 人",
								color: "#7f1100",
							},
							{
								min: 1000,
								max: 9999,
								label: "1000 - 9999 人",
								color: "#bd1316"
							},
							{
								min: 500,
								max: 999,
								label: "500 - 999 人",
								color: "#e64b45"
							},
							{
								min: 100,
								max: 499,
								label: "100 - 499 人",
								color: "#ff8c71"
							},
							{
								min: 10,
								max: 99,
								label: "10 - 99 人",
								color: "#fdd2a0"
							},
							{
								min: 1,
								max: 9,
								label: "1 - 9 人",
								color: "#fff2cf"
							},
							{
								min: 0,
								max: 0,
								label: "0",
								color: "rgb(255,255,255)"
							},
						],
						hoverLink: true,
						textStyle: {
							fontSize: 8
						},
					},
					geo: {
						map: 'china',
						roam: false, //不开启缩放和平移
						zoom: 1.25, //视角缩放比例
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
					//配置属性
					series: [{
						type: 'map',
						geoIndex: 0,
						animation: false,
						data: mydata,
					}]
				};
				//初始化echarts实例
				chart.setOption(optionMap);
				this.$refs.mapChart.setChart(chart);
			},
			getnovlist() {
				GetNovList().then(res => {
					let novlist = res.data
					this.data = novlist.map(item => {
						return {
							name: item.name,
							value: item.value
						}
					})
					console.log(this.data);
				})
			}
		}
	};
</script>

<style scoped lang="scss">
	.wrap {
		width: 100%;
		height: 900upx;
		border: 1rpx solid #ddd;
	}
</style>
