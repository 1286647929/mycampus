<template>
	<view>
		<web-view :src="image"></web-view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				image:''
			}
		},
		onLoad(options){
			this.image = decodeURI(options.url)
		},
		methods: {
			
		}
	}
</script>

<style>

</style>
