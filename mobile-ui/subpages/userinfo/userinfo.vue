<template>
	<view class="container">
		<!-- 头像 -->
		<view class="avtor-content" @tap="ToUserInfo">
			<text>头像</text>
			<view class="avtor">
				<u--image :src="prifix + user.avatar" shape="circle" width="80" height="80"></u--image>
				<view class="icon">
					<u-icon bold="true" name="arrow-right"></u-icon>
				</view>
			</view>
		</view>
		<!-- 昵称 -->
		<view class="common-content" @tap="modify($event)" :data-param="'nickName=' + user.nickName" :id="1">
			<text class="nick">昵称</text>
			<view class="name">
				<text class="nickname">{{user.nickName}}</text>
				<view class="icon">
					<u-icon bold="true" name="arrow-right"></u-icon>
				</view>
			</view>
		</view>
		<!-- 手机号 -->
		<view class="common-content" @tap="modify($event)" :data-param="'phonenumber=' + user.phonenumber" :id="1">
			<text class="nick">手机号</text>
			<view class="name">
				<text class="nickname">{{user.phonenumber}}</text>
				<view class="icon">
					<u-icon bold="true" name="arrow-right"></u-icon>
				</view>
			</view>
		</view>
		<!-- 学院 -->
		<view class="common-content" @tap="modify($event)" :data-param="'deptName=' + user.dept.deptName" :id="1">
			<text class="nick">学院</text>
			<view class="name">
				<text class="nickname">{{user.dept.deptName}}</text>
				<view class="icon">
					<u-icon bold="true" name="arrow-right"></u-icon>
				</view>
			</view>
		</view>
		<!-- 专业 -->
		<view class="common-content" @tap="modify($event)" :data-param="'major=' + user.dept.deptName" :id="1">
			<text class="nick">专业</text>
			<view class="name">
				<text class="nickname">{{user.dept.deptName}}</text>
				<view class="icon">
					<u-icon bold="true" name="arrow-right"></u-icon>
				</view>
			</view>
		</view>
		<!-- 班级 -->
		<view class="common-content" @tap="modify($event)" :data-param="'className=' + user.className" :id="1">
			<text class="nick">班级</text>
			<view class="name">
				<text class="nickname">{{user.className}}</text>
				<view class="icon">
					<u-icon bold="true" name="arrow-right"></u-icon>
				</view>
			</view>
		</view>
		<view style="margin-top: 20upx;">
			<u-button @tap="logout">退出登录</u-button>
		</view>
	</view>
</template>

<script>
	import {
		logout
	} from '@/api/login.js'
	
	import {
		getInfo
	} from '@/api/user.js'
	
	import {
		ACCESS_TOKEN
	} from '@/store/mutation-types'
	
	import storage from '@/utils/storage';
	
	export default {
		data() {
			return {
				src: '',
				user: {
					// userName: '张三',
					// phonenumber: '18888888888',
					// deptName: '软件学院',
					// major: '软件技术',
					// className: '2006',
					// avatar:''
					userName: '',
					nickName: '',
					phonenumber: '',
					dept: [],
					className: '',
					avatar: ''
				},
				prifix: 'http://localhost:8080'

			}
		},
		methods: {
			ToUserInfo() {
				var that = this
				console.log(that.user);
				let token = 'Bearer ' + storage.get(ACCESS_TOKEN)
				uni.chooseImage({
					count: 1, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album', 'camera'], //从相册选择、相机选择
					success: function(res) {
						let avatarfile = res.tempFilePaths[0]
						uni.uploadFile({
							url: 'http://localhost:8080/system/user/profile/avatar',
							filePath: avatarfile,
							name: 'avatarfile',
							header: {
								Authorization: token
							},
							success: (uploadFileRes) => {
								console.log(uploadFileRes.data);
								// getInfo().then(res=>{
								// 	console.log(this.user);
								// 	// this.user = res.data
								// })
							},
							complete() {
								that.getuserinfo()
							}
						})
						// avatar(avatarfile).then(res =>{
						// 	console.log(res);
						// })
						// console.log(JSON.stringify(res.tempFilePaths));
					}
				});
			},
			modify(e) {
				console.log(e);
				let param = e.currentTarget.dataset.param
				uni.navigateTo({
					url: '/subpages/userinfo/modifyuserinfo?' + param + '&user=' + JSON.stringify(this.user)
				})
			},
			getuserinfo() {
				getInfo().then(res => {
					this.user = res.data
					// this.user.avatar == '' ? this.user.avatar = this.src : this.user.avatar = this.prifix+this.user.avatar

				})
			},
			logout() {
				logout().then(()=>{
					uni.$u.toast('退出成功！')
					uni.redirectTo({
						url:'/subpages/login/login'
					})
				})
			}

		},
		onLoad(options) {
			// console.log(JSON.parse(options.user));
			this.user = JSON.parse(options.user);
		},
		onShow(user) {
			if (user) {
				this.user = user
			}
			// console.log(user);
		}
	}
</script>

<style lang="scss" scoped>
	.container {
		width: 100%;
		height: 100vh;
		background-color: #f5f6f7;
	}

	.avtor-content {
		background-color: #ffffff;
		display: flex;
		height: 130upx;

		text {
			position: relative;
			left: 20upx;
			display: flex;
			justify-content: center;
			align-items: center;
			// line-height: 130upx;
		}

		.avtor {
			display: flex;
			justify-content: center;
			align-items: center;
			margin-left: 510upx;
		}

		.icon {
			position: absolute;
			right: 30upx;
		}
	}

	.common-content {
		margin-top: 3upx;
		background-color: #ffffff;
		display: flex;
		height: 130upx;

		.nick {
			position: relative;
			left: 20upx;
			display: flex;
			justify-content: center;
			align-items: center;
		}

		.nickname {
			position: absolute;
			right: 100upx;
			display: flex;
			justify-content: center;
			align-items: center;
		}

		.name {
			display: flex;
			justify-content: center;
			align-items: center;
			margin-left: 500upx;
		}

		.icon {
			position: absolute;
			right: 30upx;
		}
	}
</style>
