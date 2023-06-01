import request from './request'

// 获取发布信息
export function getpublish(currIndex,pageSize) {
  return request.get("/admin/getpublishBypage",{params:{currIndex,pageSize}})
}

// 从数据库中删除，
export function deletePublishDb(id){
  return request.get("/admin/deleteDb",{params:{id}})
}