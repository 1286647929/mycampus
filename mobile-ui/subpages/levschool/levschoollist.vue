<template>
	<view class="wrap">
		<u-list @scrolltolower="scrolltolower">
			<!-- 粘性布局 -->
			<u-sticky bgColor="#fff">
				<u-tabs :list="list1" @click="click" lineWidth="30" lineColor="#f56c6c" :activeStyle="{
				            color: '#303133',
				            fontWeight: 'bold',
				            transform: 'scale(1.05)'
				        }" :inactiveStyle="{
				            color: '#606266',
				            transform: 'scale(1)'
				        }" itemStyle="padding-left: 15px; padding-right: 15px; height: 34px;">
				</u-tabs>
			</u-sticky>
			<u-list-item v-for="(item, index) in userInfotemp" :key="index">
				<view class="wrap-content">

					<view class="wrap-name">
						<u-tag shape="circle" :text="'姓名：' + item.studentName"></u-tag>
						<u-tag :type="item.status == '1' ? 'success' : item.status == '2' ? 'error' : 'warning'"
							shape="circle"
							:text="item.status == '1' ? '状态：通过' : item.status == '2' ? '状态：不通过' : '状态：待审批'"></u-tag>
					</view>
					<view>
						<u-row customStyle="margin-bottom: 20px">
							<u-col span="6">
								<view class="demo-layout">{{'学院/专业：' + item.collegeName}}</view>
							</u-col>
							<u-col span="6">
								<view class="demo-layout">{{'班级：' + item.studentClass}}</view>
							</u-col>
						</u-row>
						<u-row customStyle="margin-bottom: 20px">
							<u-col span="6">
								<view class="demo-layout">{{'离校原因：' + item.levCourse}}</view>
							</u-col>
							<u-col span="6">
								<view class="demo-layout">{{'行程轨迹：' + item.travel}}</view>
							</u-col>
						</u-row>
						<u-row customStyle="margin-bottom: 20px">
							<u-col span="12">
								<view class="demo-layout">{{'离校时间：' + item.levTime}}</view>
							</u-col>
						</u-row>
						<u-row customStyle="margin-bottom: 20px">
							<u-col span="12">
								<view class="demo-layout">{{'返校时间：' + item.reschoolTime}}</view>
							</u-col>
						</u-row>
					</view>
				</view>
			</u-list-item>
		</u-list>
	</view>
</template>

<script>
	import {
		QueryLevSchool
	} from '@/api/levschool/levschool.js'

	export default {
		data() {
			return {
				queryForm: {
					pageNum: 1,
					pageSize: 10,
					studentName: null,
				},
				total: '',
				userInfo: [],
				userInfotemp: [], //临时
				indexList: [],
				list1: [{
					name: '全部',
				}, {
					name: '审批通过',
				}, {
					name: '审批失败'
				}],
			}
		},
		onLoad(options) {
			let user = JSON.parse(options.user)
			this.queryForm.studentName = user.nickName
			this.getLevSchoolList()
		},
		methods: {
			getLevSchoolList() {
				QueryLevSchool(this.queryForm).then(res => {
					if (res.code == 200) {
						this.total = res.total
						let userInfo = res.rows.map(item => {
							return {
								...item
							}
						})

						this.userInfo = [...this.userInfo, ...userInfo]
						this.userInfotemp = this.userInfo
					}
				}).catch(err => {
					console.log(err);
				})
			},
			click(options) {
				this.queryForm.pageNum = 1
				this.userInfotemp = this.userInfo
				if (options.name === '审批通过') {
					this.userInfotemp = this.userInfotemp.filter(item => {
						return item.status == '1'
					})
				} else if (options.name === '审批失败') {
					this.userInfotemp = this.userInfotemp.filter(item => {
						return item.status == '2'
					})
				} else {

				}

				// console.log(this.userInfo);
			},
			scrolltolower() {
				let maxPageNum = Math.ceil(this.total / this.queryForm.pageSize)
				if (maxPageNum <= this.queryForm.pageNum) {
					console.log('没有更多数据了');
					this.status = 'nomore'
					return
				}
				this.queryForm.pageNum++
				this.getLevSchoolList()
			},

		},
	}
</script>

<style lang="scss">
	.wrap {
		width: 100%;
		height: 100vh;
		background-color: #f5f6f7;
	}

	.wrap-content {
		background-color: #ffffff;
		margin-bottom: 30upx;
		border-radius: 50upx;
	}

	.wrap-name {
		display: flex;
		margin-bottom: 20upx;
		margin-left: 10upx;
		width: 15%;


	}

	.wrap-name>view {
		// background-color: red;
		margin-top: 10upx;
		margin-left: 20upx;
	}

	.demo-layout {
		margin-top: 16upx;
		margin-left: 20upx;
		height: 50upx;
		text-align: center;
	}
</style>
