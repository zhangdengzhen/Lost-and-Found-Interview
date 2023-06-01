import hyRequest from './index'
// 获取openid
export function getCode(){
  return hyRequest.login()
  }
  // 发送code
  export function sendCode(data){
    return hyRequest.get("/author/getopenid",{code:data})
  }
  // 设置缓存
  
  // 根据openid获取用户
  export function getUserByopenid(openid) {
    return hyRequest.get("/users/selectByopenid",{openid:openid})
  }
  // 插入用户
  export function insertUser(openid) {
    return hyRequest.post("/users/insert",{id:0,nickname:"",
    avator:"",
    phone:"",
    school:"",
    openid:openid})
  }
  // 根据openid修改用户
  export function updateUser(user) {
    return hyRequest.post("/users/update",user)
  }
  export function getToken(openid){
    return hyRequest.get("/author/token",{openid:openid})
  }