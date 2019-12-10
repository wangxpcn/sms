<template>
  <div class="edit-user">
    <div class='opr'>
      <h1>修改用户</h1>
    </div>
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
        <el-button type="primary" @click="modify()">修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import api from '@/apis/api'
export default {
  name: 'ModifyUser',
  data () {
    return {
      form: {
        id: 0,
        name: '',
        age: '',
        sex: '',
        interesting: ''
      }
    }
  },
  beforeRouteEnter (to, from, next) {
    next(vm => {
      let row = to.query.row
      vm.form.id = row.id
      vm.form.name = row.name
      vm.form.age = row.age
      vm.form.sex = row.sex
      vm.form.interesting = row.interesting
    })
  },
  methods: {
    modify () {
      api.common.modify('/user', this.form).then(() => {
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
.edit-user {
  margin: 20px auto;
  width: 30%;
}
</style>
