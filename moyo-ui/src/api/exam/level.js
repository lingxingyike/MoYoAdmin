import request from '@/utils/request'

// 查询科目列表
export function listLevel(query) {
  return request({
    url: '/exam/level/list',
    method: 'get',
    params: query
  })
}

// 查询科目详细
export function getLevel(levelId) {
  return request({
    url: '/exam/level/' + levelId,
    method: 'get'
  })
}

// 新增科目
export function addLevel(data) {
  return request({
    url: '/exam/level',
    method: 'post',
    data: data
  })
}

// 修改科目
export function updateLevel(data) {
  return request({
    url: '/exam/level',
    method: 'put',
    data: data
  })
}

// 删除科目
export function delLevel(levelId) {
  return request({
    url: '/exam/level/' + levelId,
    method: 'delete'
  })
}
