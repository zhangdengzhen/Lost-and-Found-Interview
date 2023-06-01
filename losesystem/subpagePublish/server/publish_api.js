import hyRequest from '../../server/index'

export function getUserpublish(openid){
  return hyRequest.get("/publish/mypublish",{openid:openid})
}
export function getByType(openid,type){
  return hyRequest.get("/publish/selectbyOpenidAndType",{
    openid:openid,
    type:type
  })
}
export function getbyOpenidStatus(openid,status){
  return hyRequest.get("/publish/openidstatus",{
    openid,status
  })
}
export function deletebyid(id){
  return hyRequest.get("/publish/deletebyid",{id:id})
}
export function updatestatus(id){
  return hyRequest.get("/publish/updatestatus",{id:id})
}