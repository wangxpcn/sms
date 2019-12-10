<template>
  <div class="microblog">
    <div class="header">
      <router-link class="profile" :to="{path: '/users'}">{{userName}}</router-link>
    </div>
    <table width='100%'>
      <tr>
        <th class="input">
          <el-tabs v-model="activeName">
            <el-tab-pane name="main">
              <span slot="label"><i class="el-icon-edit-outline"></i></span>
              <el-input type="textarea" :autosize="{ maxRows: 8}" v-model="value"/>
            </el-tab-pane>
            <el-tab-pane name="search">
              <span slot="label"><i class="el-icon-search"></i></span>
              <el-input style="margin:5px 0;" v-model="searchInput" size="small">
                <i slot="prefix" class="el-input__icon el-icon-search"></i>
              </el-input>
            </el-tab-pane>
          </el-tabs>
        </th>
      </tr>
      <tr>
        <td v-show="activeName==='main'">
          <div style="text-align: right">
            <span v-if="used >= 1000" style="margin-right: 10px; color: red">已达到最大字数</span>
            <span style="margin-right: 10px; color: #f8f6f2;">{{used}}/1000</span>
            <button class="add" @click="add()">发表</button>
          </div>
        </td>
      </tr>
      <tr v-for='blog of tableData' :key='blog.id'>
        <td>
          <div :class="blog.edit?'':'content'">
            <el-input v-if="blog.edit" type="textarea" :autosize="{ maxRows: 8}" v-model="blog.message"/>
            <span @mouseup="select(blog)" v-else style="word-break: break-word;" v-html="blog.message"></span>
            <el-dialog
              :visible.sync="blog.dialogVisible"
              :append-to-body='true'
              width="15%">
              <table>
                <tr>
                  <th>
                    <el-color-picker v-model="blog.color"></el-color-picker>
                  </th>
                  <th>
                    <el-color-picker v-model="blog.bgcolor" show-alpha></el-color-picker>
                  </th>
                  <th style="width:100px;">
                    <el-select v-model="fontSize" placeholder="字体大小">
                      <el-option
                        v-for="item in fontSizes"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </th>
                </tr>
              </table>
              <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="addStyle(blog)">确 定</el-button>
              </span>
            </el-dialog>
          </div>
          <div v-show="activeName==='main'" class="operate" style="text-align: right">
            <div style="float:left;font-size: 12px;">
              <span>{{blog.createTime}}</span>
            </div>
            <button class="save" v-if="blog.edit" @click="save(blog)"><i class="el-icon-check"></i></button>
            <button class="edit" v-else @click="edit(blog)"><i class="el-icon-edit"></i></button>
            <button style="margin-left: 3px;" class="edit" @click="clearStyle(blog)"><i class="el-icon-close"></i></button>
            <button style="margin-left: 3px;" class="del" @click="del(blog.id)"><i class="el-icon-delete"></i></button>
          </div>
        </td>
      </tr>
    </table>
    <div id='foot'/>
  </div>
</template>

<script>
import api from '@/apis/api'
export default {
  name: 'microblog',
  props: {
  },
  data () {
    return {
      userId: 0,
      userName: '',
      areaStyle: "width:100%; height:100%; border:solid 0 #ccc; resize:none; border-radius: 10px;background: #f8f6f2; outline: none; font-family:'Avenir', Helvetica, Arial, sans-serif; font-size:14px;",
      blogs: [],
      searchTableData: [],
      value: '',
      searchInput: '',
      used: 0,
      fontSizes: [{
        value: '12px',
        label: '12px'
      }, {
        value: '24px',
        label: '24px'
      }, {
        value: '36px',
        label: '36px'
      }, {
        value: '48px',
        label: '48px'
      }, {
        value: '60px',
        label: '60px'
      }],
      fontSize: '16px',
      activeName: 'main'
    }
  },
  beforeRouteEnter (to, from, next) {
    next(vm => {
      vm.userId = to.query.id
      vm.userName = to.query.name
    })
  },
  methods: {
    add () {
      if (this.value) {
        let item = {
          'userId': this.userId,
          'message': this.value,
          'edit': false,
          'dialogVisible': false,
          'createTime': this.format(new Date(), 'yyyy-MM-dd HH:mm:ss')
        }
        api.common.add('/microNote', item).then(() => {
          this.fetchAll()
        })
        this.value = ''
      }
    },
    del (id) {
      this.$confirm('确认删除吗?', '提示', {
        type: 'warning'
      })
        .then(() => {
          let item = {
            'id': id
          }
          api.common.deletes('/microNote', item).then(() => {
            this.fetchAll()
          })
        })
        .catch(() => {})
    },
    edit (blog) {
      blog.edit = true
    },
    save (blog) {
      blog.edit = false
      api.common.modify('/microNote', blog).then(() => {
        this.fetchAll()
      })
    },
    fetchAll (scroll) {
      let param = {
        'userId': this.userId
      }
      api.common.get('/microNote', param).then((res) => {
        this.blogs = res.data
        if (scroll) {
          this.$nextTick(() => {
            let foot = document.getElementById('foot')
            foot.scrollIntoView()
          })
        }
      })
    },
    select (blog) {
      if (window.getSelection && window.getSelection().toString()) {
        blog.dialogVisible = true
        blog.selectValue = window.getSelection().toString()
      }
    },
    addStyle (blog) {
      blog.dialogVisible = false
      let selectValue = blog.selectValue
      let wrapSelectValue = '<span style="font-size:' + this.fontSize + ';color:' + blog.color + ';background:' + blog.bgcolor + '">' + selectValue + '</span>'
      let msg = blog.message
      blog.message = msg.replace(selectValue, wrapSelectValue)
      api.common.modify('/microNote', blog).then(() => {
        this.fetchAll()
      })
    },
    clearStyle (blog) {
      blog.dialogVisible = false
      let msg = blog.message
      let clearMsg = msg.replace(/<span.*?>/g, '').replace('</span>', '')
      blog.message = clearMsg
      api.common.modify('/microNote', blog).then(() => {
        this.fetchAll()
      })
    },
    format (date, fmt) {
      let o = {
        'M+': date.getMonth() + 1,
        'd+': date.getDate(),
        'H+': date.getHours(),
        'm+': date.getMinutes(),
        's+': date.getSeconds(),
        'q+': Math.floor((date.getMonth() + 3) / 3),
        'S': date.getMilliseconds()
      }
      if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
      }
      for (let k in o) {
        if (new RegExp('(' + k + ')').test(fmt)) {
          fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
        }
      }
      return fmt
    }
  },
  watch: {
    userId: function (val) {
      this.fetchAll()
    },
    value: function (val) {
      this.used = val.length
      if (this.used > 1000) {
        this.$nextTick(() => {
          this.value = this.value.substring(0, 1000)
        })
      }
    },
    blogs: function (val) {
      this.searchTableData = val
    },
    searchInput: function (val) {
      this.searchTableData = this.blogs
      if (val === '') {
        for (let item of this.blogs) {
          let clearMsg = item.message.replace(/<span.*?>/g, '').replace('</span>', '')
          item.message = clearMsg
        }
        return
      }
      this.searchTableData = this.blogs.filter(item => {
        let clearMsg = item.message.replace(/<span.*?>/g, '').replace('</span>', '')
        if (clearMsg && clearMsg.indexOf(val) !== -1) {
          item.message = clearMsg.replace(new RegExp(val, 'g'), '<span style="font-size:16px;color:#E0EB18;background:rgba(255, 106, 0, 1)">' + val + '</span>')
          return true
        }
        return false
      })
    }
  },
  mounted () {
    if (this.userId) {
      this.fetchAll()
    }
  },
  computed: {
    tableData: function () {
      let data = this.searchTableData
      return data.reverse()
    }
  }
}
</script>

<style>
.microblog {
  margin: 0 auto;
  margin-top: 60px;
  width: 80%;
  font-weight: 500;
  font-size: 14px;
}
.microblog textarea {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  margin: 5px 0;
}
.microblog button{
  display: inline-block;
  line-height: 1;
  white-space: nowrap;
  cursor: pointer;
  background: #f8f6f2;
  border: 0px solid #dcdfe6;
  border-color: #dcdfe6;
  -webkit-appearance: none;
  text-align: center;
  box-sizing: border-box;
  outline: none;
  margin: 0;
  transition: .1s;
  -moz-user-select: none;
  -webkit-user-select: none;
  -ms-user-select: none;
  padding: 6px 10px;
  border-radius: 4px;
}
.microblog .add:hover {
  background: #0088ff;
  color: #fff;
}
.microblog .operate button {
  padding: unset;
  background: unset;
  color: #b4ad98;
}
.microblog .operate {
  margin-top: -5px;
  color: #fff;
}
.microblog .del:hover {
  color: red;
}
.microblog .edit:hover {
  color: #fff;
}
.microblog .save:hover {
  color: #fff;
}
.microblog .edit-content {
  border-radius: 3px;
  border: 1px solid #ebeef5;
  background: #f8f6f2;
  overflow: hidden;
  box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
  margin: 5px 0;
  padding: 10px;
  text-align: left;
}
.microblog .content {
  border-radius: 3px;
  overflow: hidden;
  box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
  margin: 5px 0;
  padding: 10px;
  text-align: left;
  color: #fff;
  background: #5298da;
}
.el-dialog__body {
  padding: 15px 20px!important;
}
.el-dialog__footer {
  padding: 1px 20px 5px 20px!important;
}
.el-message-box {
  width: 60%;
}
.header{
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height:60px;
  background: #5298da;
  border-bottom: 1px solid #eee;
  z-index: 1000;
  text-align: right;
}
.profile{
  margin-right: 100px;
  color: #fff;
  font-size: 36px;
  margin-top: 10px;
  display: inline-block;
}
a {
  text-decoration: none;
}
.router-link-active {
  text-decoration: none;
}
</style>
