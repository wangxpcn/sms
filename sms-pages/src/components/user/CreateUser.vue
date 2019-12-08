<template>
  <div class="add-user">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="姓名">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="年龄">
        <el-input v-model="form.age"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-input v-model="form.sex"></el-input>
      </el-form-item>
      <el-form-item label="兴趣">
        <el-input v-model="form.interesting"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="cancel()">取消</el-button>
        <el-button type="primary" @click="create()">创建</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import api from '@/apis/api'
export default {
  name: 'CreateUser',
  data () {
    return {
      form: {
        name: '',
        age: '',
        sex: '',
        interesting: ''
      }
    }
  },
  methods: {
    create () {
      api.common.add('/user', this.form).then(() => {
        this.$router.push('/users')
      })
    },
    cancel () {
      this.$router.push('/users')
    }
  }
}
</script>

<style>
.add-user {
  margin: 20px auto;
  width: 30%;
}
.add-user .el-form-item__label{
  color: #fff;
}
</style>
