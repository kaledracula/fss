<docs>
  * https://github.com/auth0-blog/vue-jwt-authentication
  * https://auth0.com/blog/build-an-app-with-vuejs/
</docs>
<style scoped>

</style>
<template>
    <div class="d-content">
      <d-filter></d-filter>
      <d-result></d-result>
    </div>
</template>

<script>
  import dFilter from './d-filter'
  import dResult from './d-result'

  export default {
    name: 'd-content1',
    title: 'd-content1',
    components: {
      dFilter,
      dResult
    },
    methods: {
    }
  }
</script>

<style>
</style>
