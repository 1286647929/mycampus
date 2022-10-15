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
						<u-tag :type="item.cough == '否' ? 'success' : 'error'" shape="circle"
							:text="'咳嗽：' + item.cough"></u-tag>
						<u-tag :type="item.fever == '否' ? 'success' : 'error'" shape="circle"
							:text="'发烧：' + item.fever"></u-tag>
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
								<view class="demo-layout">{{'行程轨迹：' + item.travel}}</view>
							</u-col>
							<u-col span="6">
								<view class="demo-layout">{{'体温：' + item.temperature}}</view>
							</u-col>
						</u-row>
						<u-row customStyle="margin-bottom: 20px">
							<u-col span="12">
								<view class="demo-layout">{{'打卡时间：' + item.punchTime}}</view>
							</u-col>
						</u-row>
					</view>
				</view>
			</u-list-item>
			<u-loadmore fontSize="25" :status="status" />
		</u-list>
	</view>
</template>

<script>
	import {
		QueryPunchByName
	} from '@/api/punch/punch.js'

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
					name: '咳嗽',
				}, {
					name: '发烧'
				}],
				// 数据加载状态
				status: 'loadmore',
			}
		},
		onLoad(options) {
			let user = JSON.parse(options.user)
			this.queryForm.studentName = user.nickName
			// this.queryForm.collegeName = user.dept.deptName
			// this.queryForm.studentClass = user.className
			// this.loadmore()
			this.getPunchList()
		},
		methods: {
			getPunchList() {
				QueryPunchByName(this.queryForm).then(res => {
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
				// console.log(this.userInfotemp);
				if (options.name === '咳嗽') {
					this.userInfotemp = this.userInfotemp.filter(item => {
						return item.cough == '是'
					})
				} else if (options.name === '发烧') {
					this.userInfotemp = this.userInfotemp.filter(item => {
						return item.fever == '是'
					})
				} else {

				}
			},
			scrolltolower() {
				let maxPageNum = Math.ceil(this.total / this.queryForm.pageSize)
				if (maxPageNum <= this.queryForm.pageNum) {
					console.log('没有更多数据了');
					this.status = 'nomore'
					return
				}
				this.queryForm.pageNum++
				this.getPunchList()
			},
			// loadmore() {
			// 	for (let i = 0; i < 30; i++) {
			// 		this.indexList.push({
			// 			url: this.urls[uni.$u.random(0, this.urls.length - 1)]
			// 		})
			// 	}
			// }
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
