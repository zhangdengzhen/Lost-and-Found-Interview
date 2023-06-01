import request from './request'

export  function login(username,password){
  return request.post('/admin/login',{
    avator: "",
    email: "",
    id: 0,
    password: password,
    phone: "",
    username: username,
    uuid: ""
  })
}