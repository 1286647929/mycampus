<template>
	<view class="container">
		<view class="content">
			<!-- 注意，如果需要兼容微信小程序，最好通过setRules方法设置rules规则 -->
			<u--form labelWidth="auto" labelPosition="left" :model="model1" :rules="rules" ref="form1">
				<u-form-item label="姓名" prop="userInfo.studentName" borderBottom>
					<u--input disabled="true" v-model="model1.userInfo.studentName" border="none"></u--input>
				</u-form-item>
				<u-form-item label="学院" prop="userInfo.collegeName" borderBottom>
					<u--input disabled="true" v-model="model1.userInfo.collegeName" border="none"></u--input>
				</u-form-item>
				<u-form-item label="班级" prop="userInfo.studentClass" borderBottom>
					<u--input disabled="true" v-model="model1.userInfo.studentClass" border="none"></u--input>
				</u-form-item>
				<u-form-item label="打卡时间" prop="userInfo.punchTime" borderBottom>
					<u--input v-model="model1.userInfo.punchTime" border="none"></u--input>
				</u-form-item>
				<u-form-item label="时间段" prop="userInfo.timePart" borderBottom>
					<u--input v-model="model1.userInfo.timePart" border="none"></u--input>
				</u-form-item>
				<u-form-item label="行程轨迹" prop="userInfo.travel" borderBottom>
					<u--input v-model="model1.userInfo.travel" border="none"></u--input>
				</u-form-item>
				<u-form-item label="体温" prop="userInfo.temperature" borderBottom>
					<u--input v-model="model1.userInfo.temperature" border="none"></u--input>
				</u-form-item>
				<u-form-item label="是否咳嗽" prop="radiovalue1" borderBottom>
					<!-- <u--input v-model="model1.userInfo.cough" border="none"></u--input> -->
					<u-radio-group v-model="model1.radiovalue1" size="30" placement="row">
						<u-radio labelSize="25" :customStyle="{marginLeft: '16px'}"
							v-for="(item, index) in model1.radiolist1" :key="index" :label="item.name" :name="item.name"
							@change="coughradioChange">
						</u-radio>
					</u-radio-group>
				</u-form-item>
				<u-form-item label="是否发烧" prop="radiovalue2" borderBottom>
					<!-- <u--input v-model="model1.userInfo.fever" border="none"></u--input> -->
					<u-radio-group v-model="model1.radiovalue2" size="30" placement="row">
						<u-radio labelSize="25" :customStyle="{marginLeft: '16px'}"
							v-for="(item, index) in model1.radiolist2" :key="index" :label="item.name" :name="item.name"
							@change="feverradioChange">
						</u-radio>
					</u-radio-group>
				</u-form-item>
				<u-button type="primary" text="提交" customStyle="margin-top: 50px" @click="submit"></u-button>
				<u-button type="error" text="重置" customStyle="margin-top: 10px" @click="reset"></u-button>
			</u--form>
		</view>
	</view>
</template>

<script>
	import {
		AddPunch
	} from '@/api/punch/punch.js'

	export default {
		data() {
			return {
				model1: {
					userInfo: {
						studentName: '',
						collegeName: '',
						studentClass: '',
						punchTime: uni.$u.timeFormat(new Date(), 'yyyy-mm-dd hh:MM:ss'),
						timePart: new Date().getHours() + '-' + (parseInt(new Date().getHours()) + 1),
						travel: '',
						temperature: '',
						cough: '',
						fever: ''
					},
					radiovalue1: '',
					radiovalue2: '',
					radiolist1: [{
							name: '是',
							disabled: false
						},
						{
							name: '否',
							disabled: false
						},
					],
					radiolist2: [{
							name: '是',
							disabled: false
						},
						{
							name: '否',
							disabled: false
						},
					],
				},

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
					'userInfo.punchTime': {
						type: 'string',
						required: true,
						message: '请填写打卡时间',
						trigger: ['blur', 'change']
					},
					'userInfo.timePart': {
						type: 'string',
						required: true,
						message: '请填写时间段',
						trigger: ['blur', 'change']
					},
					'userInfo.travel': {
						type: 'string',
						required: true,
						message: '请填写行程轨迹',
						trigger: ['blur', 'change']
					},
					'userInfo.temperature': {
						type: 'string',
						required: true,
						message: '请填写体温',
						trigger: ['blur', 'change']
					},
					'radiovalue1': {
						type: 'string',
						required: true,
						message: '请填写是否咳嗽',
						trigger: ['blur', 'change']
					},
					'radiovalue2': {
						type: 'string',
						required: true,
						message: '请填写是否发烧',
						trigger: ['blur', 'change']
					},
				}
			}
		},
		onReady() {
			// 如果需要兼容微信小程序，并且校验规则中含有方法等，只能通过setRules方法设置规则
			// this.$refs.form1.setRules(this.rules)
		},
		onLoad(options) {
			let user = JSON.parse(options.user)
			// console.log(user);
			this.model1.userInfo.studentName = user.nickName
			this.model1.userInfo.collegeName = user.dept.deptName
			this.model1.userInfo.studentClass = user.className
		},
		methods: {
			coughradioChange(n) {
				this.model1.userInfo.cough = n
			},
			feverradioChange(n) {
				this.model1.userInfo.fever = n
			},
			submit() {
				// 如果有错误，会在catch中返回报错信息数组，校验通过则在then中返回true
				this.$refs.form1.validate().then(res => {
					// uni.$u.toast('校验成功')
					// console.log(this.model1.userInfo);
					AddPunch(this.model1.userInfo).then(res => {
						if (res.code == 200) {
							// uni.$u.toast('添加成功！')
							uni.navigateBack()
						}
					})
				}).catch(errors => {
					uni.$u.toast('校验失败')
				})
			},
			reset() {
				const validateList = ['userInfo.studentName', 'userInfo.collegeName', 'userInfo.studentClass',
					'userInfo.punchTime',
					'userInfo.timePart',
					'userInfo.travel', 'userInfo.temperature', 'userInfo.cough', 'userInfo.fever', 'radiovalue1',
					'radiovalue2'
				]
				// this.$refs.form1.resetFields()
				// this.$refs.form1.resetFields()
				// this.$refs.form1.clearValidate()
				setTimeout(() => {
					this.model1.userInfo = {
						studentName: this.model1.userInfo.studentName,
						collegeName: this.model1.userInfo.collegeName,
						studentClass: this.model1.userInfo.studentClass,
						punchTime: uni.$u.timeFormat(new Date(), 'yyyy-mm-dd hh:MM:ss'),
						timePart: new Date().getHours() + '-' + (parseInt(new Date().getHours()) + 1),
						travel: '',
						temperature: '',
						cough: '',
						fever: ''
					}

					this.model1.radiovalue1 = ''
					this.model1.radiovalue2 = ''
					this.$refs.form1.clearValidate(validateList)
					// 或者使用 this.$refs.form1.clearValidate()
				}, 10)
			},
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
