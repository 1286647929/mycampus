<template>
	<view class="container">
		<view class="common-view" v-if="nameflag">
			<view class="nick-view">
				<text class="username">昵称 :</text>
			</view>
			<view class="usernameval">
				<input class="input-view" maxlength="-1" placeholder="请输入内容" :focus="focusFlag" :value="user.nickName"
					@input="syndata" :id="0" @blur="valid($event,'username')" />
				<view class="close-icon" v-if="!IsClose">
					<u-icon bold="true" size="50" name="close" @tap.prevent="reset"></u-icon>
				</view>
			</view>
			<view class="tip-view">
				<text class="tip-message" :style="'color: '+ color + ';'">{{message[0]}}</text>
			</view>
			<view class="submit">
				<u-button text="提交" size="25" shape="circle" color="rgb(255, 209, 0)" @tap="submit"></u-button>
			</view>
		</view>
		<view class="common-view" v-if="phoneflag">
			<view class="nick-view">
				<text class="username">手机号 :</text>
			</view>
			<view class="usernameval">
				<input class="input-view" maxlength="-1" placeholder="请输入内容" :focus="true" :value="user.phonenumber"
					@input="syndata" :id="1" @blur="valid($event,'phone')" />
				<view class="close-icon" v-if="!IsClose">
					<u-icon bold="true" size="50" name="close" @tap="reset"></u-icon>
				</view>
			</view>
			<view class="tip-view">
				<text class="tip-message" :style="'color: '+ color + ';'">{{message[1]}}</text>
			</view>
			<view class="submit">
				<u-button text="提交" size="25" shape="circle" color="rgb(255, 209, 0)" @tap="submit"></u-button>
			</view>
		</view>
		<view class="common-view" v-if="collegeflag">
			<view class="nick-view">
				<text class="username">学院 :</text>
			</view>
			<view class="usernameval">
				<input class="input-view" maxlength="-1" placeholder="请输入内容" :focus="true" :value="user.deptName"
					@input="syndata" :id="2" @blur="valid($event,'college')" />
				<view class="close-icon" v-if="!IsClose">
					<u-icon bold="true" size="50" name="close" @tap="reset"></u-icon>
				</view>
			</view>
			<view class="tip-view">
				<text class="tip-message" :style="'color: '+ color + ';'">{{message[2]}}</text>
			</view>
			<view class="submit">
				<u-button text="提交" size="25" shape="circle" color="rgb(255, 209, 0)" @tap="submit"></u-button>
			</view>
		</view>
		<view class="common-view" v-if="majorflag">
			<view class="nick-view">
				<text class="username">专业 :</text>
			</view>
			<view class="usernameval">
				<input class="input-view" maxlength="-1" placeholder="请输入内容" :focus="true" :value="user.major"
					@input="syndata" :id="3" @blur="valid($event,'major')" />
				<view class="close-icon" v-if="!IsClose">
					<u-icon bold="true" size="50" name="close" @tap="reset"></u-icon>
				</view>
			</view>
			<view class="tip-view">
				<text class="tip-message" :style="'color: '+ color + ';'">{{message[2]}}</text>
			</view>
			<view class="submit">
				<u-button text="提交" size="25" shape="circle" color="rgb(255, 209, 0)" @tap="submit"></u-button>
			</view>
		</view>
		<view class="common-view" v-if="classflag">
			<view class="nick-view">
				<text class="username">班级 :</text>
			</view>
			<view class="usernameval">
				<input class="input-view" maxlength="-1" placeholder="请输入内容" :focus="true" :value="user.className"
					@input="syndata" :id="4" @blur="valid($event,'classname')" />
				<view class="close-icon" v-if="!IsClose">
					<u-icon bold="true" size="50" name="close" @tap="reset"></u-icon>
				</view>
			</view>
			<view class="tip-view">
				<text class="tip-message" :style="'color: '+ color + ';'">{{message[2]}}</text>
			</view>
			<view class="submit">
				<u-button text="提交" size="25" shape="circle" color="rgb(255, 209, 0)" @tap="submit"></u-button>
			</view>
		</view>
		<u-toast ref="uToast"></u-toast>
	</view>
</template>

<script>
	import {
		updateProfile
	} from '@/api/user.js'
	export default {
		data() {
			return {
				user: {
					nickName: null,
					phonenumber: null,
					deptName: null,
					major: null,
					className: null,
				},
				prifix: 'http://localhost:8080',
				userinfo: {},
				IsClose: false,
				message: ['请以英文字母或汉字开头，限2-10个字符', '请输入11位手机号', ''],
				color: 'rgb(153, 146, 166)',
				submitflag: true,
				inputval: '',
				nameflag: false,
				phoneflag: false,
				collegeflag: false,
				majorflag: false,
				classflag: false,
				focusFlag: true

			}
		},
		onLoad(options) {
			this.userinfo = JSON.parse(options.user)
			// console.log(this.userinfo);
			let nickName = options.nickName
			nickName == null ? this.nameflag = false : this.nameflag = true
			let phonenumber = options.phonenumber
			phonenumber == null ? this.phoneflag = false : this.phoneflag = true
			let deptName = options.deptName
			deptName == null ? this.collegeflag = false : this.collegeflag = true
			let major = options.major
			major == null ? this.majorflag = false : this.majorflag = true
			let className = options.className
			className == null ? this.classflag = false : this.classflag = true
			// console.log(options);
			this.user.nickName = nickName
			this.user.phonenumber = phonenumber
			this.user.deptName = deptName
			this.user.major = major
			this.user.className = className
		},
		methods: {
			// 重置表单
			reset() {
				this.focusFlag = false
				console.log(this.focusFlag);
				this.inputval = ''
				this.user.nickName = '',
					this.user.phonenumber = '',
					this.user.deptName = '',
					this.user.major = '',
					this.user.className = ''
				this.IsClose = !this.IsClose
			},
			// 同步输入框数据
			syndata(e) {
				console.log(e);
				this.inputval = e.detail.value
				this.user = {
					nickName: this.inputval,
					phonenumber: this.inputval,
					deptName: this.inputval,
					major: this.inputval,
					className: this.inputval,
				}
				let id = e.currentTarget.id
				id == 0 ? this.userinfo.nickName = this.inputval : this.userinfo.nickName
				id == 1 ? this.userinfo.phonenumber = this.inputval : this.userinfo.phonenumber
				id == 2 ? this.userinfo.deptName = this.inputval : this.userinfo.deptName
				id == 3 ? this.user.major = this.inputval : this.user.major
				id == 4 ? this.userinfo.className = this.inputval : this.userinfo.className
				console.log(e.currentTarget.id);
				this.inputval == '' ? this.IsClose = true : this.IsClose = false
			},
			// 验证输入内容
			valid(e, param) {
				// console.log(param);

				let content = e.detail.value

				// 通用
				let commonre = /.*/

				// 昵称正则
				let namere = new RegExp("^[\u4E00-\u9FA5a-zA-Z].{1,10}$")
				// 手机号正则 不带区号校验
				let phonere = /^((1[3,5,8,7,9][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/

				let reList = [namere, phonere, commonre]



				let userlist = ['username', 'phone']
				// let tipmessage = ['请以英文字母或汉字开头，限2-10个字符','请输入11位手机号','']

				let reindex = userlist.indexOf(param)
				let re = null
				reindex == -1 ? re = reList[2] : re = reList[reindex]
				// let re = reList[reindex]
				console.log(userlist.indexOf(param));


				if (!re.test(content)) {
					this.color = 'red'
					this.submitflag = false
				} else {
					this.color = 'rgb(153, 146, 166)'
					this.submitflag = true
				}
			},
			// 提交按钮
			submit() {
				console.log(this.inputval);
				console.log(this.user);
				if (this.submitflag) {
					let userinfo = this.userinfo
					updateProfile(userinfo).then(res => {
						// console.log(res);
						if (res.code == 200) {
							this.$refs.uToast.show({
								type: 'success',
								icon: 'checkbox-mark',
								duration: '500',
								message: "添加成功"
							})
						}

					}).catch(err => {
						console.log(err);
					}).finally(() => {
						setTimeout(() => {
							let pages = getCurrentPages()
							let prevPage = pages[pages.length - 2]; //上一个页面
							// this.userinfo.avatar = this.prifix + this.us erinfo.avatar
							let user = this.userinfo
							console.log(user);
							prevPage.onShow(user) //页面传参
							uni.navigateBack()
						}, 1000)
					})

				} else {
					this.$refs.uToast.show({
						type: 'error',
						icon: 'close',
						duration: '500',
						message: "输入格式错误，请重试！"
					})
				}
			}
		},
	}
</script>

<style lang="scss" scoped>
	.container {
		width: 100%;
		height: 100vh;
		background-color: #f5f6f7;
	}

	.common-view {

		width: 100%;


		.nick-view {
			display: flex;

			.username {
				width: 100%;
				margin-top: 30upx;
				margin-left: 25upx;
			}
		}


		.usernameval {
			margin-top: 30upx;
			height: 116upx;
			width: 100%;
			background-color: #ffffff;

			.input-view {
				margin-left: 25upx;
				height: 100%;
				width: 80%;
				align-items: center;
			}

			.close-icon {
				position: absolute;
				z-index: 99;
				right: 50upx;
				top: 140upx;
			}
		}

		.submit {
			margin-top: 50upx;
			margin-left: 35upx;
			width: 675upx;
		}

		.tip-view {
			margin-top: 15upx;

			.tip-message {
				margin-left: 25upx;
				text-align: center;
				font-size: 25upx;
				color: rgb(153, 146, 166);
			}
		}

	}
</style>
