<template>
  <el-menu
      default-active="user"
      class="el-menu-vertical-demo"
      style="width: 200px; height: calc(100vh - 50px)"
  >
    <el-sub-menu index="1">
      <template #title>
        <el-icon><Grid /></el-icon>
        <span>系统管理</span>
      </template>

      <el-menu-item index="user" :route="{ path: '/' }">用户管理</el-menu-item>

    </el-sub-menu>
    <el-menu-item index="data" :route="{ path: '/' }">数据管理</el-menu-item>

  </el-menu>
</template>

<script>
export default {
  name: 'TheAside'
};
</script>

<style scoped>

</style>