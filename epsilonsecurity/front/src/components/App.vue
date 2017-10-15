<template>
	<div>
		<p>Hello User</p>
		<p>
			<router-link to="/foo">Link-1</router-link>
			<router-link to="/bar">Link-2</router-link>
		</p>
		<router-view></router-view>
	</div>
</template>

<script>
export default {
    // empty
}
</script>