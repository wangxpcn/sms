<template>
  <div class="users">
    <div class='addBtn'>
      <el-button type="primary" @click="add()">添加</el-button>
    </div>
     <el-table
      :data="users"
      border
      style="width: 100%">
      <el-table-column
        prop="name"
        label="姓名"
        width="180">
        <template slot-scope='scope'>
          <router-link :to="{path: '/microNote', query: {id: scope.row.id, name: scope.row.name}}">{{scope.row.name}}</router-link>
        </template>
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
        label="兴趣">
      </el-table-column>
      <el-table-column
        align="center"
        label="操作">
        <template slot-scope='scope'>
          <el-button type="text" @click="edit(scope.row)">修改</el-button>
          <el-button type="text" @click="del(scope.row.id)">删除</el-button>
        </template>
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
    edit (row) {
      this.$router.push({
        path: '/users/modifyUser',
        query: {
          row: row
        }
      })
    },
    del (id) {
      this.$confirm('确认删除吗?', '提示', {
        type: 'warning'
      })
        .then(() => {
          let item = {
            'id': id
          }
          api.common.deletes('/user', item).then(() => {
            this.fetchAll()
          })
        })
        .catch(() => {})
    },
    fetchAll () {
      api.common.get('/user').then((res) => {
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
a {
  text-decoration: none;
}
.router-link-active {
  text-decoration: none;
}
</style>
