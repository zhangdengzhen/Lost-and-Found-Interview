import hyRequest from './index'
// 分页获取数据
export function getBypage(currentPage=1,pageSize=10){
  return hyRequest.get("/publish/all",{currentPage:currentPage,pageSize})
}
// 根据类别获取数据  类型/失物（捡到），寻物（丢失）,1,0
export function getBytype(type,currentPage=1,pageSize=10,token) {
  return hyRequest.get("/publish/type",
  {type:type,
    currentPage:currentPage,
    pageSize:pageSize},token)
}

export function getStatus(currIndex,pageSize){
  return hyRequest.get("/publish/getStatusByPage",{currIndex,pageSize})
}

