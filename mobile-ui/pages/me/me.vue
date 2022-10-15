<template>
	<view class="content-wrap">
		<view>
			<view class="wrap">
				<view class="avtor">
					<u--image @tap="ToUserInfo" :src="prifix + user.avatar" shape="circle" width="100" height="100"></u--image>
				</view>
				<view class="nickname">
					<text>{{user.nickName}}</text>
				</view>
			</view>

		</view>

		<view class="tool">
			<u-grid :border="false" col="3">
				<u-grid-item @tap="toswitch(item.url)" v-for="(item,index) in baseList" :key="index">
					<u-icon :customStyle="{paddingTop:20+'rpx'}" :name="item.name" :size="50"></u-icon>
					<text class="grid-text">{{item.title}}</text>
				</u-grid-item>
			</u-grid>
			<u-toast ref="uToast" />
		</view>

	</view>
</template>

<script>
	import {
		getInfo
	} from '@/api/user.js'
	export default {
		data() {
			return {
				user: {
					// userName: '张三',
					// phonenumber: '18888888888',
					// deptName: '软件学院',
					// major: '软件技术',
					// className: '2006',
					// avatar:''
				},
				baseList: [{
						name: 'checkbox-mark',
						title: '每日打卡',
						url: '/subpages/punch/punch'
					},
					{
						name: 'pushpin',
						title: '申请离校',
						url: '/subpages/levschool/levschool'
					},
					{
						name: 'edit-pen',
						title: '打卡记录',
						url: '/subpages/punch/punchlist'
					},
					{
						name: 'edit-pen-fill',
						title: '申请离校记录',
						url: '/subpages/levschool/levschoollist'
					}
				],
				src: 'https://cdn.uviewui.com/uview/album/1.jpg',
				prifix:'http://localhost:8080'
			}
		},
		async onShow() {
			await this.getuserinfo()
		},
		// async onLoad() {
		// 	await this.getuserinfo()
		// },
		methods: {
			async getuserinfo() {
				getInfo().then(res => {
					if (!res) return
					let user = res.data
					this.user = user
					// this.user.avatar == '' ? this.user.avatar = this.src : this.user.avatar = this.prifix+this.user.avatar
					// this.user = {
					// 	userId: user.userId,
					// 	userName: user.userName,
					// 	nickName: user.nickName,
					// 	phonenumber: user.phonenumber,
					// 	dept: user.dept,
					// 	className: user.className,
					// 	avatar: user.avatar == '' ? this.src : (this.prifix+user.avatar)
					// }
					// console.log(this.user);
				})
			},
			ToLogin() {
				uni.navigateTo({
					url: '/subpages/login/login'
				})
			},
			ToUserInfo() {
				uni.navigateTo({
					url: '/subpages/userinfo/userinfo?user=' + JSON.stringify(this.user)
				})
			},
			toswitch(url) {
				uni.navigateTo({
					url: url + '?user=' + JSON.stringify(this.user)
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content-wrap {
		height: 100vh;
		background-color: rgb(245, 246, 247);
	}

	.wrap {
		background-color: #ffffff;
		display: flex;
		margin-bottom: 50upx;
		border-radius: 50upx;

		.avtor {
			display: flex;
			align-items: center;
			justify-content: center;
			margin-left: 20upx;
		}

		.nickname {

			// background-color: red;
			text {
				margin: 20upx;
				// background-color: blue;
				line-height: 130upx;
			}
		}
	}

	.grid-text {
		font-size: 14px;
		color: #909399;
		padding: 10rpx 0 20rpx 0rpx;
		/* #ifndef APP-PLUS */
		box-sizing: border-box;
		/* #endif */
	}

	.tool {
		background-color: #ffffff;
		border-radius: 50upx;
	}
</style>
