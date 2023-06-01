import hyRequest from '../../server/index'
export let publishData = {
  contact: "",
  detail: "",
  id: 0,
  imguuid: "",
  itemCategory: 0,
  place: "",
  title: "",
  type: 0,
  useropenid:""
}
export let publishData2 = {
  contact: "",
  detail: "",
  id: 0,
  imguuid: "",
  itemCategory: 0,
  place: "",
  title: "",
  type: 1,
  useropenid:""
}
// 上传图片
export function uploadImg(file) {
  return hyRequest.upload("/img/upload/file",file)
}
// 批量插入图片url
export function insertImg(imglist) {
  return hyRequest.post("/img/insertBatch",imglist)
}
// 获取，物品类别
export function getCategory() {
  return hyRequest.get("/category/selectall")
}
export function publish(data) {
  return hyRequest.post("/publish/insert",data)
}