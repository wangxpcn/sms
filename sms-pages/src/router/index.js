import Vue from 'vue'
import Router from 'vue-router'
import Microblog from '@/components/Microblog'
import Users from '@/components/Users'
import CreateUser from '@/components/user/CreateUser'
import ModifyUser from '@/components/user/ModifyUser'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/users'
    },
    {
      path: '/microNote',
      name: 'Microblog',
      component: Microblog
    },
    {
      path: '/users',
      name: 'Users',
      component: Users
    },
    {
      path: '/users/createUser',
      name: 'CreateUser',
      component: CreateUser
    },
    {
      path: '/users/modifyUser',
      name: 'ModifyUser',
      component: ModifyUser
    }
  ]
})
