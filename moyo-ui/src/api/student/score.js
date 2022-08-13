import request from '@/utils/request'

// 查询岗位列表
export function listScore(query) {
  return request({
    url: '/student/studentScore/list',
    method: 'get',
    params: query
  })
}

// 查询岗位详细
export function getScore(scoreId) {
  return request({
    url: '/student/studentScore/' + scoreId,
    method: 'get'
  })
}

// 新增岗位
export function addScore(data) {
  return request({
    url: '/student/studentScore',
    method: 'post',
    data: data
  })
}

// 修改岗位
export function updateScore(data) {
  return request({
    url: '/student/studentScore',
    method: 'put',
    data: data
  })
}

// 删除岗位
export function delScore(scoreId) {
  return request({
    url: '/student/studentScore/' + scoreId,
    method: 'delete'
  })
}
