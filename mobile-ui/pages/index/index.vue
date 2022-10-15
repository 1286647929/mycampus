<template>
	<view id="index">

		<!-- 顶部导航 -->
		<uni-nav-bar v-if="navBarShowTag">
			<view class="tabs-box">
				<view class="one-nav" :class="currentSwiperIndex === 0 ? 'nav-actived' : '' " @tap="swiperChange(0)">推荐
				</view>
				<view class="one-nav" :class="currentSwiperIndex === 1 ? 'nav-actived' : '' " @tap="swiperChange(1)">资讯
				</view>
			</view>
		</uni-nav-bar>

		<!-- 页面 header 相关部分 -->
		<view class="header-box">
			<!-- 顶部广告位轮播图 -->
			<swiper class="swiper" :indicator-dots="false" :autoplay="true" :interval="2500" :duration="500"
				:circular="true">
				<swiper-item v-for="(item,index) in swiperAdList" :key="index">
					<navigator open-type="navigate" :url=" '/pages/webview/webview?url=' + encodeURI(item.link)">
						<image class="banner-swiper-img" :src="item.image" mode="aspectFill" />
					</navigator>
				</swiper-item>
			</swiper>
			<!-- 遮罩使用弧形框 -->
			<image class="crile" src="@/static/crile.png" mode="aspectFill" />
			<!-- 两个选项按钮 -->
			<view class="card-header">
				<view class="card-one card-left" @tap="gotoFeeds('/pages/feeds/feeds')">
					<image class="img" src="@/static/coffee.png" mode="aspectFill" />
					<view class="iright">
						<view class="title">精彩动态</view>
					</view>
				</view>
				<view class="card-one card-right" @tap="gotoFeeds('/pages/me/me')">
					<image class="img" src="@/static/ran.png" mode="aspectFill" />
					<view class="iright">
						<view class="title">个人中心</view>
					</view>
				</view>
			</view>
			<!-- Tab 选项卡 -->
			<view class="tabs-box">
				<view class="one-nav" :class="currentSwiperIndex === 0 ? 'nav-actived' : '' " @tap="swiperChange(0)">推荐
				</view>
				<view class="one-nav" :class="currentSwiperIndex === 1 ? 'nav-actived' : '' " @tap="swiperChange(1)">资讯
				</view>
			</view>


			<!-- 内容轮播导航实现 -->
			<swiper class="swiper-box" :style=" 'height:'+ swiperSliderHeight " :current="currentSwiperIndex"
				@animationfinish="swiperSlider">
				<!-- 推荐列表实现 -->
				<swiper-item class="swiper-item sns-news">
					<view v-for="(item, index) in feedsList" :key="index">
						<navigator class="one-new" open-type="navigate"
							:url=" '/subpages/newinfo/newinfo?news=' + JSON.stringify(item)">
							<view class="left">
								<view class="title u-line-2">{{item.newTitle}}</view>
								<view class="uinfo">
									<view class="iview">
										<view class="utime">
											<text class="name">{{ item.newAuthor }}</text>
										</view>
									</view>
									<text class="uptime">{{ $u.timeFormat(item.createTime,'yyyy-mm-dd') }}</text>
								</view>
							</view>
							<view class="right">
								<image class="pic" mode="aspectFill" :src="item.cover[0]" />
							</view>
						</navigator>
					</view>
					<u-loadmore fontSize="25" :status="status" />
				</swiper-item>
				<!-- 资讯列表实现 -->
				<swiper-item class="swiper-item sns-news">
					<view v-for="(item, index) in newsList" :key="index">
						<navigator class="one-new" open-type="navigate"
							:url=" '/subpages/newinfo/newinfo?news=' + JSON.stringify(item)">
							<view class="left">
								<view class="title u-line-2">{{item.newTitle}}</view>
								<view class="uinfo">
									<view class="iview">
										<view class="utime">
											<text class="name">{{ item.newAuthor }}</text>
										</view>
									</view>
									<text class="uptime">{{ $u.timeFormat(item.createTime,'yyyy-mm-dd') }}</text>
								</view>
							</view>
							<view class="right">
								<image class="pic" mode="aspectFill" :src="item.cover[0]" />
							</view>
						</navigator>
					</view>
					<u-loadmore fontSize="25" :status="status" />
				</swiper-item>
			</swiper>
			<!--回到顶部-->
			<u-back-top :scrollTop="scrollTop" :mode="mode" :iconStyle="iconStyle"></u-back-top>

		</view>
	</view>
</template>

<script>
	import {
		getNews,
		getFeeds
	} from '@/api/news/news.js'

	export default {
		data() {
			return {
				// navBar 显示状态控制
				navBarShowTag: false,
				// 顶部 轮播图列表
				swiperAdList: [{
					link: 'https://www.hniu.cn/info/1035/4313.htm',
					image: 'https://www.hniu.cn/images/guoqing1.png'
				}, {
					link: 'https://www.hniu.cn/info/1035/4313.htm',
					image: 'https://www.hniu.cn/images/123456.png'
				}, {
					link: 'https://www.hniu.cn/info/1035/4313.htm',
					image: 'https://www.hniu.cn/images/FY003.jpg'
				}, {
					link: 'https://www.hniu.cn/info/1035/4313.htm',
					image: 'https://www.hniu.cn/PIC/724A1189333.JPG'
				}, {
					link: 'https://www.hniu.cn/info/1035/4313.htm',
					image: 'https://www.hniu.cn/PIC/724A0063111.JPG'
				}],
				// 动态列表数据
				feedsList: [],
				// 分页数据	
				feedsqueryParams: {
					pageNum: 1,
					pageSize: 10,
				},
				newsqueryParams: {
					pageNum: 1,
					pageSize: 10,
				},
				// 总数
				total: '',
				// 资讯列表数据
				newsList: [],
				// 当前 推荐 资讯 滑动位置
				currentSwiperIndex: 0,
				// 滑动页面轮播器的高度
				swiperSliderHeight: '500px',
				swiperSliderFeedsHeight: 0,
				swiperSliderNewsHeight: 0,
				// 记录滚动所在的位置
				oldFeedsScrollTop: 0,
				oldNewsScrollTop: 0,
				//回到顶部菜单
				scrollTop: 0,
				mode: 'circle',
				iconStyle: {
					fontSize: '32rpx',
					color: '#2979ff'
				},
				// 数据加载状态
				status: 'loadmore',
			}
		},
		async onLoad() {
			// this.getAdverts()
			this.getNewsList(this.newsqueryParams)
			this.getFeedsList(this.feedsqueryParams)
		},
		onPageScroll(event) {
			if (this.currentSwiperIndex === 0) {
				this.scrollTop = event.scrollTop;
				this.oldFeedsScrollTop = event.scrollTop
			} else {
				this.scrollTop = event.scrollTop;
				this.oldNewsScrollTop = event.scrollTop
			}
			if (event.scrollTop > 220) {
				this.navBarShowTag = true
			} else {
				this.navBarShowTag = false
			}
		},
		onReachBottom() {
			console.log('下拉到底啦')
			if (this.currentSwiperIndex === 0) {
				let maxpageNum = Math.ceil(this.total / this.feedsqueryParams.pageSize)
				if (maxpageNum <= this.feedsqueryParams.pageNum) {
					this.status = 'nomore'
					return
				}
				this.status = 'loading'
				this.feedsqueryParams.pageNum++
				this.getFeedsList(this.feedsqueryParams)
			} else {
				// 请求新的数据
				let maxpageNum = Math.ceil(this.total / this.newsqueryParams.pageSize)
				if (maxpageNum <= this.newsqueryParams.pageNum) {
					this.status = 'nomore'
					return
				}
				this.status = 'loading'
				this.newsqueryParams.pageNum++
				this.getNewsList(this.newsqueryParams)
			}
		},
		onPullDownRefresh() {
			console.log('顶部下拉刷新')
			if (this.currentSwiperIndex === 0) {
				this.feedsqueryParams.pageNum = 1
				this.feedsList = []
				this.getFeedsList(this.feedsqueryParams)
			} else {
				// 请求新的数据
				this.newsqueryParams.pageNum = 1
				this.newsList = []
				this.getNewsList(this.newsqueryParams)
			}
		},
		methods: {
			// 请求 广告轮播图信息
			// async getAdverts() {
			// 	let adverts = await getAdvers({
			// 		space: '1,2,3'
			// 	})
			// 	// console.log(adverts);
			// 	this.swiperAdList = adverts.map(item => {
			// 		return {
			// 			id: item.id,
			// 			link: item.data.link,
			// 			image: item.data.image
			// 		}
			// 	})
			// },

			// 请求推荐列表数据
			async getFeedsList(data) {
				let feeds = await getFeeds(data)
				this.total = feeds.total
				// console.log(feeds.rows);
				let feedsList = feeds.rows.map(item => {
					return {
						...item,
						cover: item.image.split(',')
					}
				})

				this.feedsList = [...this.feedsList, ...feedsList]
				// console.log(this.feedsList);
				// console.log(this.feedsList)
				this.swiperSliderFeedsHeight = this.feedsList.length * 95 + 50 + 'px'
				this.swiperSliderHeight = this.swiperSliderFeedsHeight
			},

			// 请求资讯列表数据
			async getNewsList(data) {
				let news = await getNews(data)
				this.total = news.total
				// console.log(news);
				let newsList = news.rows.map(item => {
					return {
						...item,
						cover: item.image.split(',')
					}
				})

				this.newsList = [...this.newsList, ...newsList]
				// console.log(this.newsList)
				this.swiperSliderNewsHeight = this.newsList.length * 95 + 50 + 'px'
				this.swiperSliderHeight = this.swiperSliderNewsHeight
			},
			gotoFeeds(url) {
				uni.switchTab({
					url
				})
			},
			swiperChange(index) {
				if (index === 0) {
					this.swiperSliderHeight = this.swiperSliderFeedsHeight
					setTimeout(() => {
						uni.pageScrollTo({
							duration: 0, //过渡时间必须为0，uniapp bug，否则运行到手机会报错
							scrollTop: this.oldFeedsScrollTop, //滚动到目标位置
						})
					}, 0)

				} else {
					this.swiperSliderHeight = this.swiperSliderNewsHeight
					setTimeout(() => {
						uni.pageScrollTo({
							duration: 0, //过渡时间必须为0，uniapp bug，否则运行到手机会报错
							scrollTop: this.oldNewsScrollTop, //滚动到目标位置
						})
					}, 0)

				}
				this.currentSwiperIndex = index
			},
			swiperSlider(event) {
				// console.log(event);
				if (event.detail.current === 0) {
					this.swiperSliderHeight = this.swiperSliderFeedsHeight
					setTimeout(() => {
						uni.pageScrollTo({
							duration: 0, //过渡时间必须为0，uniapp bug，否则运行到手机会报错
							scrollTop: this.oldFeedsScrollTop, //滚动到目标位置
						})
					}, 0)

				} else {
					this.swiperSliderHeight = this.swiperSliderNewsHeight
					setTimeout(() => {
						uni.pageScrollTo({
							duration: 0, //过渡时间必须为0，uniapp bug，否则运行到手机会报错
							scrollTop: this.oldNewsScrollTop, //滚动到目标位置
						})
					}, 0)

				}
				this.currentSwiperIndex = event.detail.current
			}
		}
	}
</script>

<style lang="scss" scoped>
	// 推荐、咨询 按钮样式
	.tabs-box {
		display: flex;
		flex-direction: row;
		justify-content: center;
		width: 750upx;

		.one-nav {
			width: 120upx;
			color: #9B9B9B;
			font-size: 36upx;
			text-align: center;
			font-weight: blod;

			&.nav-actived {
				color: #0050FF;
			}
		}
	}

	.header-box {
		position: relative;
		left: 0;
		height: 500upx;
		background-color: #f1f1f1;
		z-index: 1;

		// 广告位轮播器相关样式
		.swiper {
			width: 750upx;
			height: 400upx;
			position: absolute;
			left: 0;
			top: 0;
			text-align: center;
			z-index: 1;

			.banner-swiper-img {
				width: 750upx;
				height: 400upx;
				box-shadow: 0 0 2px 0 rgb(188, 188, 188);
			}
		}

		.crile {
			width: 750upx;
			height: 50upx;
			position: absolute;
			left: 0;
			top: 355upx;
			z-index: 9;
		}

		// 新鲜事 活动墙 按钮样式
		.card-header {
			position: absolute;
			left: 0;
			top: 320upx;
			height: 160upx;
			z-index: 99;
			width: 710upx;
			margin-left: 20upx;
			display: flex;
			flex-direction: row;
			flex-wrap: wrap;
			justify-content: space-between;
			align-items: center;
			align-content: center;

			.card-one {
				width: 328upx;
				height: 96upx;
				border-radius: 49upx;
				background-color: #fff;
				margin: 0 10upx;
				box-shadow: 0 0 2px 0 rgb(188, 188, 188);
				display: flex;
				flex-direction: row;
				flex-wrap: wrap;
				justify-content: flex-start;
				align-items: center;
				align-content: center;

				.img {
					width: 44upx;
					height: 44upx;
					margin-left: 50upx;
				}

				.iright {
					margin-left: 30upx;
					text-align: left;
					color: #888;

					.title {
						font-size: 30upx;
						color: #001432;
					}

					.iview {
						display: flex;
						flex-direction: row;
						flex-wrap: wrap;
						justify-content: space-between;
						align-items: center;
						align-content: center;
						font-size: 20upx;
						margin-top: -5upx;
					}
				}
			}
		}

		// 推荐、咨询 按钮样式
		.tabs-box {
			width: 750upx;
			position: absolute;
			z-index: 1;
			left: 0;
			top: 480upx;
			display: flex;
			flex-direction: row;
			justify-content: center;

			.one-nav {
				height: 80upx;
				width: 110upx;
				color: #9B9B9B;
				font-size: 36upx;
				text-align: center;
				font-weight: blod;

				&.nav-actived {
					color: #0050FF;
				}
			}
		}
	}

	// 此刻 栏目样式\
	.swiper-box {
		position: relative;
		background-color: #f1f1f1;
		padding: 60upx 0 40upx;
		top: 600upx;
	}

	// 轮播页面 资讯
	.sns-news {
		background-color: #fff;
		width: 750upx;

		.one-new {
			width: 700upx;
			height: 74px;
			display: flex;
			flex-direction: row;
			flex-wrap: wrap;
			justify-content: space-around;
			align-items: flex-start;
			align-content: center;
			padding-bottom: 10px;
			padding-top: 10px;
			padding-left: 25upx;
			padding-right: 25upx;
			border-bottom: 1px solid #f1f1f1;

			.left {
				width: 490upx;
				height: 140upx;
				text-align: left;
				display: flex;
				flex-direction: column;
				justify-content: space-between;
				align-items: flex-start;

				.title {
					font-size: 30upx;
					line-height: 42upx;
					color: #001432;
					margin-top: 21upx;
				}

				.uinfo {
					width: 490upx;
					display: flex;
					flex-direction: row;
					flex-wrap: nowrap;
					justify-content: space-between;
					align-items: center;
					align-content: center;
					margin-top: 6upx;
					font-size: 20upx;
					color: #999;

					.utime {
						font-size: 24upx;

						.name {
							max-width: 120upx;
							color: #777;
						}
					}
				}
			}

			.right {
				width: 120upx;

				.pic {
					width: 120upx;
					height: 120upx;
					margin-top: 20upx;
					border-radius: 6upx;
				}
			}
		}
	}
</style>
