<template>
    <div id="the-app">
        <app-header></app-header>
				<div id="content">
					<div id="app-sidebar">
						hello
					</div>
					<div id="viewport">
						world
					</div>
				</div>
    </div>
</template>

<script>
		import AppHeader from './AppHeader.vue'
    export default {
			components: {
				AppHeader
			}
    }
</script>

<style scoped lang="scss">
	* {
		padding: 0;
		margin: 0;
		box-sizing: border-box;
	}
	html, body {
		width: 100%;
		height: 100%;
	}

	#the-app {
		width: 100%;
		height: 100%;
		display: flex;
		flex-flow: column nowrap;
	}
	#app-header {
		background: red;
		height: 60px;
	}

	#content {
		background: blue;
		display: flex;
		flex-flow: row nowrap;
		flex-grow: 1;
	}

	#viewport {
		background: green;
		flex-grow: 1;
	}

	#app-sidebar {
		background: pink;
		width: 230px;
	}
</style>