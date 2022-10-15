<template>
	<view class="content-wrap">
		<view class="content-head">
			校内新闻
		</view>
		<!-- 标题 -->
		<view class="content-title">
			{{news.newTitle}}
		</view>
		<!-- 时间、作者、来源 -->
		<view class="content-tool">
			<u-row customStyle="margin-top: 10px">
				<u-col span="4">
					<view>{{'时间：' + $u.timeFormat(news.createTime,'yyyy年mm月dd日')}}</view>
				</u-col>
				<u-col span="4">
					<view>{{'作者：' + news.newAuthor}}</view>
				</u-col>
				<u-col span="4">
					<view>{{'来源：' + news.newSource}}</view>
				</u-col>
			</u-row>
		</view>
		
		<!-- 分隔槽 -->
		<u-gap height="6" bgColor="#f5f6f7"></u-gap>
		
		<!-- 内容部分  -->
		<view class="content-main">
			<text user-select="true" space="emsp">{{news.newContent[0]}}</text>
		</view>

		<view class="content-tail">
			<view v-for="(item,index) in news.cover" :key="index">
				<view class="content-tail-image">
					<u--image :showLoading="true" :src="item"></u--image>
				</view>
				<view class="content-tail-text">
					<text user-select="true" space="emsp">{{news.newContent[index+1]}}</text>
				</view>
			</view>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				news: {
					newTitle: '',
					newAuthor: '',
					newContent: '',
					newSource: '',
					newHits: '',
					image: '',
					createBy: '',
					createTime: '',
					updateBy: '',
					updateTime: '',
					remark: '',
					cover: ''
				}
			}
		},
		onLoad(options) {
			this.news = JSON.parse(options.news)
			this.news.newContent = this.news.newContent.split("\n\n")
			console.log(this.news);
		},
		methods: {

		}
	}
</script>

<style lang="scss" scoped>
	.content-wrap {
		// height: 100vh;
		// background-color: rgb(245, 246, 247);
	}

	.content-head {
		margin-top: 20upx;
		text-align: center;
		font-size: 30upx;
	}

	.content-title {
		margin-top: 20upx;
		font-size: 47upx;
	}

	.content-tool {
		margin-left: 20upx;
		text-align: center;
		font-size: 23upx;
	}
	
	.content-main{
		margin-top: 16upx;
	}

	.content-tail {
		margin-top: 50upx;

		.content-tail-image {

			display: flex;
			align-items: center;
			justify-content: center;
		}

		.content-tail-text {
			margin-bottom: 50upx;
			display: flex;
			align-items: center;
			justify-content: center;
		}
	}
</style>
