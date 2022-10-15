<template>
	<view class="container">

		<!-- 内容部分 -->
		<view class="content-main">
			<u-row customStyle="margin-bottom: 10px">
				<u-col textAlign="center" span="6">
					<view :style="tab1" @tap="totab1">全国疫情数据</view>
				</u-col>
				<u-col textAlign="center" span="6">
					<view :style="tab2" @tap="totab2">
						<u-icon name="map" labelSize="16px" :label="NovlocaltotalList[0].name + '疫情数据'"></u-icon>
					</view>
					<!-- <view style="background-color: cadetblue;border-radius: 80upx;"></view> -->
				</u-col>
			</u-row>

			<!-- 全国数据 -->
			<u-row v-if="show" customStyle="margin-bottom: 10px">
				<u-col span="4">
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view class="text-tabs">境外输入</view>
						</u-col>
					</u-row>
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view style="color: rgb(255, 163, 82); font-weight: bold; font-size: 45upx;">
								{{NovChinaTotalList.input}}
							</view>
						</u-col>
					</u-row>
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view>
								<text class="today-text">较昨日+</text>
								<text
									style="color: rgb(255, 163, 82); font-weight: bold;">{{NovChinaTotalTodayList.input}}</text>
							</view>
						</u-col>
					</u-row>
				</u-col>
				<u-col span="4">
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view class="text-tabs">无症状感染者</view>
						</u-col>
					</u-row>
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view style="color: rgb(121, 22, 24); font-weight: bold; font-size: 45upx;">
								{{NovChinaTotalList.noSymptom}}
							</view>
						</u-col>
					</u-row>
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view>
								<text class="today-text">较昨日+</text>
								<text
									style="color: rgb(121, 22, 24); font-weight: bold;">{{NovChinaTotalTodayList.incrNoSymptom}}</text>
							</view>
						</u-col>
					</u-row>
				</u-col>
				<u-col span="4">
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view class="text-tabs">现有确诊</view>
						</u-col>
					</u-row>
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view style="color: rgb(228, 74, 61); font-weight: bold; font-size: 45upx;">
								{{NovChinaTotalList.confirm - NovChinaTotalList.heal - NovChinaTotalList.dead}}
							</view>
						</u-col>
					</u-row>
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view>
								<text class="today-text">较昨日+</text>
								<text
									style="color: rgb(228, 74, 61); font-weight: bold;">{{NovChinaTotalTodayList.confirm - NovChinaTotalTodayList.heal - NovChinaTotalTodayList.dead}}</text>
							</view>
						</u-col>
					</u-row>
				</u-col>
			</u-row>
			<u-row v-if="show" customStyle="margin-bottom: 10px">
				<u-col span="4">
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view class="text-tabs">累计确诊</view>
						</u-col>
					</u-row>
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view style="color: rgb(163, 29, 19); font-weight: bold; font-size: 45upx;">
								{{NovChinaTotalList.confirm}}
							</view>
						</u-col>
					</u-row>
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view>
								<text class="today-text">较昨日+</text>
								<text
									style="color: rgb(163, 29, 19); font-weight: bold;">{{NovChinaTotalTodayList.confirm}}</text>
							</view>
						</u-col>
					</u-row>
				</u-col>
				<u-col span="4">
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view class="text-tabs">累计死亡</view>
						</u-col>
					</u-row>
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view style="color: rgb(51, 51, 51); font-weight: bold; font-size: 45upx;">
								{{NovChinaTotalList.dead}}
							</view>
						</u-col>
					</u-row>
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view>
								<text class="today-text">较昨日+</text>
								<text
									style="color: rgb(51, 51, 51); font-weight: bold;">{{NovChinaTotalTodayList.dead}}</text>
							</view>
						</u-col>
					</u-row>
				</u-col>
				<u-col span="4">
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view class="text-tabs">累计治愈</view>
						</u-col>
					</u-row>
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view style="color: rgb(52, 170, 112); font-weight: bold; font-size: 45upx;">
								{{NovChinaTotalList.heal}}
							</view>
						</u-col>
					</u-row>
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view>
								<text class="today-text">较昨日+</text>
								<text
									style="color: rgb(52, 170, 112); font-weight: bold;">{{NovChinaTotalTodayList.heal}}</text>
							</view>
						</u-col>
					</u-row>
				</u-col>
			</u-row>
			<u-row v-if="show" customStyle="margin-bottom: 30px">
				<u-col textAlign="left" span="8">
					<view style="margin-left: 20px; margin-bottom: 10px; color: rgb(153, 153, 153);">截止至{{NovChinaTotalList.updateTime}}</view>
				</u-col>
				<u-col textAlign="right" span="4">
					<view style="margin-right: 20px; margin-bottom: 10px; color: rgb(153, 153, 153);">数据说明</view>
				</u-col>
			</u-row>

			<!-- 当地省份数据 -->
			<u-row v-if="show1" customStyle="margin-bottom: 10px">
				<u-col span="4">
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view class="text-tabs">累计确诊</view>
						</u-col>
					</u-row>
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view style="color: rgb(163, 29, 19); font-weight: bold; font-size: 45upx;">{{NovlocaltotalList[0].confirm}}</view>
						</u-col>
					</u-row>
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view>
								<text class="today-text">较昨日+</text>
								<text style="color: rgb(163, 29, 19); font-weight: bold;">{{NovlocaltotalList[1].confirm}}</text>
							</view>
						</u-col>
					</u-row>
				</u-col>
				<u-col span="4">
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view class="text-tabs">累计死亡</view>
						</u-col>
					</u-row>
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view style="color: rgb(51, 51, 51); font-weight: bold; font-size: 45upx;">{{NovlocaltotalList[0].dead}}</view>
						</u-col>
					</u-row>
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view>
								<text class="today-text">较昨日+</text>
								<text style="color: rgb(51, 51, 51); font-weight: bold;">{{NovlocaltotalList[1].dead}}</text>
							</view>
						</u-col>
					</u-row>
				</u-col>
				<u-col span="4">
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view class="text-tabs">累计治愈</view>
						</u-col>
					</u-row>
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view style="color: rgb(52, 170, 112); font-weight: bold; font-size: 45upx;">{{NovlocaltotalList[0].heal}}</view>
						</u-col>
					</u-row>
					<u-row customStyle="margin-bottom: 10px">
						<u-col textAlign="center" span="12">
							<view style="color: rgb(52, 170, 112); font-weight: bold;">
								<text class="today-text">较昨日+</text>
								<text>{{NovlocaltotalList[1].heal}}</text>
							</view>
						</u-col>
					</u-row>
				</u-col>
			</u-row>
			<u-row v-if="show1" customStyle="margin-bottom: 30px">
				<u-col textAlign="left" span="8">
					<view style="margin-left: 20px; margin-bottom: 10px; color: rgb(153, 153, 153);">截止至{{NovlocaltotalList[0].updateTime}}</view>
				</u-col>
				<u-col textAlign="right" span="4">
					<view style="margin-right: 20px; margin-bottom: 10px; color: rgb(153, 153, 153);">数据说明</view>
				</u-col>
			</u-row>


		</view>
		<view class="nov-button">
			<u-button shape="circle" @tap="totap" text="查看各省份疫情数据"></u-button>
		</view>
	</view>
</template>

<script>
	import {
		getNovChinaTotalList,
		getNovChinaTotalTodayList,
		getlocaltotal
	} from '@/api/nov/nov.js'

	export default {
		data() {
			return {
				// 中国疫情总体数据
				NovChinaTotalList: [],
				// 中国疫情当天数据
				NovChinaTotalTodayList: [],
				// 当地疫情数据
				NovlocaltotalList: [],
				show: true,
				show1: false,
				tab1: 'background-color: rgb(255, 255, 255);border-radius: 30px; font-size:16px;',
				tab2: 'background-color: rgb(241, 241, 241);border-radius: 30px;'
			};
		},
		onLoad() {
			this.getNovChinaTotal()
			this.getNovChinaTotalToday()
			this.getNovlocalTotal()
		},
		methods: {
			// 中国疫情总体数据
			getNovChinaTotal() {
				getNovChinaTotalList().then(res => {
					if (res.code == 200) {
						this.NovChinaTotalList = res.data
					}
				})
			},
			// 中国疫情当天数据
			getNovChinaTotalToday() {
				getNovChinaTotalTodayList().then(res => {
					if (res.code == 200) {
						this.NovChinaTotalTodayList = res.data
					}
					// console.log(res);
				})
			},
			// 当地疫情数据
			getNovlocalTotal() {
				getlocaltotal().then(res => {
					if (res.code == 200) {
						this.NovlocaltotalList = res.data
					}
					console.log(res);
				})
			},
			totab1() {
				this.tab1 = 'background-color: rgb(255, 255, 255);border-radius: 30px; font-size:16px;'
				this.tab2 = 'background-color: rgb(241, 241, 241);border-radius: 30px;'
				this.show = true
				this.show1 = false
			},
			totab2() {
				this.tab1 = 'background-color: rgb(241, 241, 241);border-radius: 30px; font-size:16px;'
				this.tab2 = 'background-color: rgb(255, 255, 255);border-radius: 30px;'
				this.show = false
				this.show1 = true
			},
			totap() {
				uni.navigateTo({
					url: "/subpages/map/map",
					success() {
						console.log('hhh');
					},
					fail() {
						console.log('sss');
					}
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.container {
		height: 100vh;
		background-color: rgb(245, 246, 247);
	}

	.content-main {
		// display: flex;
		// align-items: center;
		width: 95%;
		background-color: #ffffff;
		border-radius: 30px;
		position: relative;
		top: 20px;
		left: 10px;
		// margin-top: 50upx;
		// margin-left: 23upx;

		.text-tabs {
			color: rgb(51, 51, 51);
			font-weight: bold;
		}
		
		.today-text{
			color: rgb(153, 153, 153);
		}
	}

	.nov-button {
		width: 50%;
		position: relative;
		left: 100px;
	}
</style>
