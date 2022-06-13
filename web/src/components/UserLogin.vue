<template>
  <div style="width: 100%; height: 100vh; background-color: cadetblue; overflow: hidden">
    <div style="width: 400px; margin: 150px auto">
      <div style="color: #cccccc; font-size: 30px; text-align: center; padding: 30px 0">欢迎登录</div>
      <el-form :model="form" size="normal">
        <el-form-item>
          <el-input v-model="form.name" :prefix-icon="Search">
            <template #prefix>
              <el-icon class="el-input__icon">
                <el-icon><User /></el-icon>
              </el-icon>
            </template>
          </el-input>

        </el-form-item>
        <el-form-item>
          <el-input v-model="form.password" :prefix-icon="Search" show-password>
            <template #prefix>
              <el-icon class="el-input__icon">
                <el-icon><Lock /></el-icon>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="login">登 录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from '@/util/request';

export default {
  name: 'UserLogin',
  data() {
    return {
      form: {}
    }
  },
  methods: {
    login() {
      request.post("http://localhost:9090/login", this.form).then(
          res => {
            if (res.code === '0') {
              this.$message({
                type: "success",
                message: "登录成功"
              });
              this.$router.push('/home'); // 页面跳转。
            } else {
              this.$message({
                type: "error",
                message: "登录失败"
              });
            }
          }
      )
    }
  }
};
</script>

<style scoped>

</style>