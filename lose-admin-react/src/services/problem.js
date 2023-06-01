import request from './request'

export function getProblem(currIndex,pageSize){
  return request.get("/problem/selectpage",{params:{currIndex,pageSize}})
}
export function deletebyId(id){
  return request.post("/problem/deletebyid?id="+id)
}