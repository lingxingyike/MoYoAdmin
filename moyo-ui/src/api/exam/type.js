import request from '@/utils/request'

// 查询证件类型列表
export function listType(query) {
  return request({
    url: '/exam/type/list',
    method: 'get',
    params: query
  })
}

// 查询证件类型详细
export function getType(studentId) {
  return request({
    url: '/exam/type/' + studentId,
    method: 'get'
  })
}

// 新增证件类型
export function addType(data) {
  return request({
    url: '/exam/type',
    method: 'post',
    data: data
  })
}

// 修改证件类型
export function updateType(data) {
  return request({
    url: '/exam/type',
    method: 'put',
    data: data
  })
}

// 删除证件类型
export function delType(studentId) {
  return request({
    url: '/exam/type/' + studentId,
    method: 'delete'
  })
}
