import request from './request'

export function getComplain(currIndex,pageSize){
  return request.get("/complain/select",{params:{currIndex,pageSize}})
}