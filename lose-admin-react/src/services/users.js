import request from './request'


// 获取用户信息
export function getusers(currIndex,pageSize) {
  return request.get("/users/getBypage",{params:{currIndex,pageSize}})
}
export function updateAuthorize(openid,authorize){
  return request.post("/users/update",{
    authorize,
    avator: "",
    id: 0,
    nickname: "",
    openid,
    phone: "",
    school: ""
  })
}