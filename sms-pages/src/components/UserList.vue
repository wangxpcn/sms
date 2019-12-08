<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <el-table
      :data="tableData"
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
  name: 'UserList',
  data () {
    return {
      msg: 'User list',
      tableData: []
    }
  },
  methods: {
    fetchAll () {
      api.user.getUsers('/user').then((res) => {
        // eslint-disable-next-line
        console.log(res.data)
        this.tableData = res.data
      })
    }
  },
  mounted () {
    this.fetchAll()
  },
  computed: {}
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>