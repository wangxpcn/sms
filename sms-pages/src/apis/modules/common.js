import axios from '@/utils/axios'

export const get = (url, param) => {
  return axios({
    url: url,
    method: 'get',
    params: param
  })
}
export const add = (url, data) => {
  return axios({
    url: url,
    method: 'post',
    data: data
  })
}
export const modify = (url, data) => {
  return axios({
    url: url,
    method: 'put',
    data: data
  })
}
export const deletes = (url, param) => {
  return axios({
    url: url,
    method: 'delete',
    params: param
  })
}
