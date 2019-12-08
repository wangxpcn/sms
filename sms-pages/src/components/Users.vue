<template>
  <div class="users">
    <div class='addBtn'>
      <el-button type="primary" @click="add()">添加</el-button>
    </div>
     <el-table
      :data="users"
      style="width: 100%">
      <el-table-column
        prop="name"
        label="姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="age"
        label="年龄"
        width="180">
      </el-table-column>
      <el-table-column
        prop="sex"
        label="性别"
        width="180">
      </el-table-column>
      <el-table-column
        prop="interesting"
        label="兴趣"
        width="180">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import api from '@/apis/api'
export default {
  name: 'Users',
  props: {
  },
  data () {
    return {
      users: []
    }
  },
  methods: {
    add () {
      this.$router.push('/users/createUser')
    },
    fetchAll () {
      api.common.get('/user').then((res) => {
        // eslint-disable-next-line
        console.log(res.data)
        this.users = res.data
      })
    }
  },
  mounted () {
    this.fetchAll()
  },
  computed: {}
}
</script>

<style>
.users {
  margin: 20px auto;
  width: 70%;
}
.users .addBtn{
    text-align: right;
    margin-bottom: 20px;
}
</style>
