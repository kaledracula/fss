<style>
  #app{
    position: relative;
  }
</style>
<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
import orderServer from './services/oms/order'
export default {
  name: 'app',
  mounted () {
    orderServer.postOrder({
      name: 'ssss'
    }).done(function (m) {
      console.log(m)
    })
    orderServer.postOrder({
      name: 'ssss',
      id: 2
    }).done(function (m) {
      console.log(m)
    })
    orderServer.getOrder().done(function (m) {
      console.log(m)
    })
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
