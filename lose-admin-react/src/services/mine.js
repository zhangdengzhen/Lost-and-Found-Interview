import request from './request'

export function getinfo(username,password) {
  return request.post("/admin/getbyusername",{username,password})
}