import request from '@/utils/request'

// 查询岗位列表
export function listStudent(query) {
  return request({
    url: '/student/studentUser/list',
    method: 'get',
    params: query
  })
}

// 查询岗位详细
export function getStudent(studentId) {
  return request({
    url: '/student/studentUser/' + studentId,
    method: 'get'
  })
}

// 新增岗位
export function addStudent(data) {
  return request({
    url: '/student/studentUser',
    method: 'post',
    data: data
  })
}

// 修改岗位
export function updateStudent(data) {
  return request({
    url: '/student/studentUser',
    method: 'put',
    data: data
  })
}

// 删除岗位
export function delStudent(studentId) {
  return request({
    url: '/student/studentUser/' + studentId,
    method: 'delete'
  })
}
