<template>
  <div class="mod-home">
    <h3>光网在线</h3>
    <ul>
      <li @click="gwzx">光网在线</li>
      <li>工作流</li>
      <li @click="zygl">资源管理</li>
    </ul>
  </div>
</template>

<script>
  export default {
      methods:{
          gwzx(){
              const { href } = this.$router.resolve({
                           name: 'larg_screen'
                        })
                       window.open(href, '_blank')

          },
          zygl(){
              const { href } = this.$router.resolve({
                  name: 'zhzygl'
              })
              window.open(href, '_blank')
          }
      }
  }
</script>

<style>
  .mod-home {
    line-height: 1.5;
  }
</style>

