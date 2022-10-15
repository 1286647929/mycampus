<template>
	<view class="container">
		<view class="content">
			<!-- 注意，如果需要兼容微信小程序，最好通过setRules方法设置rules规则 -->
			<u--form labelWidth="auto" labelPosition="left" :model="model1" :rules="rules" ref="form1">
				<u-form-item label="姓名" prop="userInfo.studentName" borderBottom>
					<u--input v-model="model1.userInfo.studentName" border="none"></u--input>
				</u-form-item>
				<u-form-item label="学院" prop="userInfo.collegeName" borderBottom>
					<u--input v-model="model1.userInfo.collegeName" border="none"></u--input>
				</u-form-item>
				<u-form-item label="班级" prop="userInfo.studentClass" borderBottom>
					<u--input v-model="model1.userInfo.studentClass" border="none"></u--input>
				</u-form-item>
				<u-form-item label="离校原因" prop="userInfo.levCourse" borderBottom>
					<u--input v-model="model1.userInfo.levCourse" border="none"></u--input>
				</u-form-item>
				<u-form-item label="行程轨迹" prop="userInfo.travel" borderBottom>
					<u--input v-model="model1.userInfo.travel" border="none"></u--input>
				</u-form-item>
				<u-form-item label="离校时间" prop="userInfo.levTime" borderBottom @click="show1 = true; hideKeyboard()">
					<!-- <u--input v-model="model1.userInfo.levTime" border="none"></u--input> -->
					<u--input v-model="model1.userInfo.levTime" disabled disabledColor="#ffffff" placeholder="请选择离校时间"
						border="none"></u--input>
					<u-icon slot="right" name="arrow-right"></u-icon>
				</u-form-item>
				<u-form-item label="返校时间" prop="userInfo.reschoolTime" borderBottom
					@click="show2 = true; hideKeyboard()">
					<!-- <u--input v-model="model1.userInfo.reschoolTime" border="none"></u--input> -->
					<u--input v-model="model1.userInfo.reschoolTime" disabled disabledColor="#ffffff"
						placeholder="请选择返校时间" border="none"></u--input>
					<u-icon slot="right" name="arrow-right"></u-icon>
				</u-form-item>
				<u-button type="primary" text="提交" customStyle="margin-top: 50px" @click="submit"></u-button>
				<u-button type="error" text="重置" customStyle="margin-top: 10px" @click="reset"></u-button>
			</u--form>

			<u-datetime-picker :show="show1" :value="value1" mode="datetime" closeOnClickOverlay
				@confirm="levtimeConfirm" @cancel="levtimeClose" @close="levtimeClose"></u-datetime-picker>

			<u-datetime-picker :show="show2" :value="value1" mode="datetime" closeOnClickOverlay
				@confirm="reSchooltimeConfirm" @cancel="reSchooltimeClose" @close="reSchooltimeClose">
			</u-datetime-picker>
		</view>
	</view>
</template>

<script>
	import {
		AddLevSchool
	} from '@/api/levschool/levschool.js'

	export default {
		data() {
			return {
				model1: {
					userInfo: {
						studentName: '',
						collegeName: '',
						studentClass: '',
						levCourse: '',
						travel: '',
						levTime: '',
						reschoolTime: '',
						status: '0' //0待审批 1通过 2不通过
					},
				},
				show1: false,
				show2: false,
				value1: Number(new Date()),

				rules: {
					'userInfo.studentName': {
						type: 'string',
						required: true,
						message: '请填写姓名',
						trigger: ['blur', 'change']
					},
					'userInfo.collegeName': {
						type: 'string',
						required: true,
						message: '请填写学院',
						trigger: ['blur', 'change']
					},
					'userInfo.studentClass': {
						type: 'string',
						required: true,
						message: '请填写班级',
						trigger: ['blur', 'change']
					},
					'userInfo.levCourse': {
						type: 'string',
						required: true,
						message: '请填写离校原因',
						trigger: ['blur', 'change']
					},
					'userInfo.travel': {
						type: 'string',
						required: true,
						message: '请填写行程轨迹',
						trigger: ['blur', 'change']
					},
					'userInfo.levTime': {
						type: 'string',
						required: true,
						message: '请填写离校时间',
						trigger: ['blur', 'change']
					},
					'userInfo.reschoolTime': {
						type: 'string',
						required: true,
						message: '请填写返校时间',
						trigger: ['blur', 'change']
					}
				}
			}
		},
		onReady() {
			// 如果需要兼容微信小程序，并且校验规则中含有方法等，只能通过setRules方法设置规则
			// this.$refs.form1.setRules(this.rules)
			// this.$refs.datetimePicker.setFormatter(this.formatter)
		},
		onLoad(options) {
			let user = JSON.parse(options.user)
			// console.log(user);
			this.model1.userInfo.studentName = user.nickName
			this.model1.userInfo.collegeName = user.dept.deptName
			this.model1.userInfo.studentClass = user.className
		},
		methods: {
			addLevSchool() {
				AddLevSchool(this.model1.userInfo).then(res => {
					console.log(res.code);
					if (res.code == 200) {
						uni.$u.toast('添加成功')
					}
				})
			},
			submit() {
				// 如果有错误，会在catch中返回报错信息数组，校验通过则在then中返回true
				this.$refs.form1.validate().then(res => {
					console.log(this.model1.userInfo);
					uni.$u.toast('校验通过')
					this.addLevSchool()
					uni.$u.sleep(1000).then(() => {
						uni.navigateBack()
					})
				}).catch(errors => {
					uni.$u.toast('校验失败')
				})
			},
			reset() {
				const validateList = ['userInfo.studentName', 'userInfo.collegeName', 'userInfo.studentClass',
					'userInfo.levCourse',
					'userInfo.travel', 'userInfo.levTime', 'userInfo.reschoolTime'
				]
				// this.$refs.form1.resetFields()
				// this.$refs.form1.resetFields()
				// this.$refs.form1.clearValidate()
				setTimeout(() => {
					this.model1.userInfo = {
						studentName: this.model1.userInfo.studentName,
						collegeName: this.model1.userInfo.collegeName,
						studentClass: this.model1.userInfo.studentClass,
						levCourse: '',
						travel: '',
						levTime: '',
						reschoolTime: '',
						status: '0'
					}
					this.$refs.form1.clearValidate(validateList)
					// 或者使用 this.$refs.form1.clearValidate()
				}, 10)
			},
			levtimeClose() {
				this.show1 = false
				this.$refs.form1.validateField('userInfo.levTime')
			},
			levtimeConfirm(e) {
				this.show1 = false
				this.model1.userInfo.levTime = uni.$u.timeFormat(e.value, 'yyyy-mm-dd hh:MM:ss')
				this.$refs.form1.validateField('userInfo.levTime')
			},
			reSchooltimeClose() {
				this.show2 = false
				this.$refs.form1.validateField('userInfo.reschoolTime')
			},
			reSchooltimeConfirm(e) {
				this.show2 = false
				this.model1.userInfo.reschoolTime = uni.$u.timeFormat(e.value, 'yyyy-mm-dd hh:MM:ss')
				this.$refs.form1.validateField('userInfo.reschoolTime')
			},
			hideKeyboard() {
				uni.hideKeyboard()
			}
		},
	}
</script>

<style lang="scss">
	.container {
		width: 100%;
		height: 100vh;
	}

	.content {
		margin-left: 30upx;
	}
</style>
