import axios from '@/utils/axios'

export const getUsers = (url) => {
  return axios({
    url: url,
    method: 'get'
  })
}
export const addUser = (url, data) => {
  return axios({
    url: url,
    method: 'post',
    data: data
  })
}
export const modifyUser = (url, data) => {
  return axios({
    url: url,
    method: 'post',
    data: data
  })
}
export const deleteUser = (url, data) => {
  return axios({
    url: url,
    method: 'post',
    data: data
  })
}