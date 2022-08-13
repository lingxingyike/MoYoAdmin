import request from '@/utils/request'

// 查询岗位列表
export function listDetail(query) {
  return request({
    url: '/student/studentDetail/list',
    method: 'get',
    params: query
  })
}

// 查询岗位详细
export function getDetail(detailId) {
  return request({
    url: '/student/studentDetail/' + detailId,
    method: 'get'
  })
}

// 新增岗位
export function addDetail(data) {
  return request({
    url: '/student/studentDetail',
    method: 'post',
    data: data
  })
}

// 修改岗位
export function updateDetail(data) {
  return request({
    url: '/student/studentDetail',
    method: 'put',
    data: data
  })
}

// 删除岗位
export function delDetail(detailId) {
  return request({
    url: '/student/studentDetail/' + detailId,
    method: 'delete'
  })
}
