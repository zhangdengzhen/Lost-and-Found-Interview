import hyRequest from './index'
export function getbyid(id){
  return hyRequest.get("/publish/findByid",{id:id})
}
export function insertcomplain(openid,publishid,detail,concat){
  return hyRequest.post("/complain/insert",{concat,id:0,openid,publishid,detail})
}