import request from '@/utils/request'

// 查询题目列表
export function listOptions(query) {
  return request({
    url: '/exam/options/list',
    method: 'get',
    params: query
  })
}

// 查询题目详细
export function getOptions(id) {
  return request({
    url: '/exam/options/' + id,
    method: 'get'
  })
}

// 新增题目
export function addOptions(data) {
  return request({
    url: '/exam/options',
    method: 'post',
    data: data
  })
}

// 修改题目
export function updateOptions(data) {
  return request({
    url: '/exam/options',
    method: 'put',
    data: data
  })
}

// 删除题目
export function delOptions(id) {
  return request({
    url: '/exam/options/' + id,
    method: 'delete'
  })
}
