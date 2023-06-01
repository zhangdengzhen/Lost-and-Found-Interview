import hyRequest from '../../server/index'
import  hyRequest3  from '../../server/index3'
export function search(title){
  return hyRequest.get("/publish/search",{title:title})
}
export function upload(file){
  return hyRequest3.upload("/upload",file)
}
export function getbyfilename(filename){
  return hyRequest.get("/img/getbyfilename",{filename:filename})
}