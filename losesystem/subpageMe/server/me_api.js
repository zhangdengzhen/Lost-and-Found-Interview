import hyRequest from '../../server/index'
// 上传图片
export function uploadImg(file) {
  return hyRequest.uploadbyurl("/img/upload/file",file)
}

export function updateAvator(openid,avator){
  return hyRequest.post("/users/update",{
    avator: avator,
    id: 0,
    nickname: "",
    openid: openid,
    phone: "",
    school: ""
  })
}
export function updateNickname(openid,nickname){
  return hyRequest.post("/users/update",{
    avator: "",
    id: 0,
    nickname: nickname,
    openid: openid,
    phone: "",
    school: ""
  })
}
export function updatePhone(openid,phone){
  return hyRequest.post("/users/update",{
    avator: "",
    id: 0,
    nickname: "",
    openid: openid,
    phone: phone,
    school: ""
  })
}
export function updateSchool(openid,school){
  return hyRequest.post("/users/update",{
    avator: "",
    id: 0,
    nickname: "",
    openid: openid,
    phone: "",
    school: school
  })
}
export function getUserinfo(openid){
  return hyRequest.get("/users/selectByopenid",{openid:openid})
}