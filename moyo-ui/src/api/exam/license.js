import request from '@/utils/request'

// 查询题型列表
export function listLicense(query) {
  return request({
    url: '/exam/license/list',
    method: 'get',
    params: query
  })
}

// 查询题型详细
export function getLicense(licenseId) {
  return request({
    url: '/exam/license/' + licenseId,
    method: 'get'
  })
}

// 新增题型
export function addLicense(data) {
  return request({
    url: '/exam/license',
    method: 'post',
    data: data
  })
}

// 修改题型
export function updateLicense(data) {
  return request({
    url: '/exam/license',
    method: 'put',
    data: data
  })
}

// 删除题型
export function delLicense(licenseId) {
  return request({
    url: '/exam/license/' + licenseId,
    method: 'delete'
  })
}
