import request from './request'

export function getall(){
  return request.get('/category/selectall')
}

export function categorydelete(id){
  return request.get("/category/delete",{params:{id}})
}

export function categoryinsert(name){
  return request.post("/category/insert",{
    id:0,
    name
  })
}