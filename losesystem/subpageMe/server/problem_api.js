import hyRequest from '../../server/index'
/**
 * 
 * @param {string} detail 
 * @param {string} concat 
 * @param {string} imguuid 
 * @param {string} openid 
 */
export function insert(detail,concat,imguuid,openid,){
  return hyRequest.post("/problem/insert",{
    concat,
    detail,
    id: 0,
    imguuid,
    openid,
    problem_id: -1,
    time: "",
    type: "request"
  })
}
/**
 * 
 * @param {*} file 
 */
export function upload(file){
  return hyRequest.upload("/img/upload/file",file)
}
/**
 * 
 * @param {*} list 
 */
export function batchInsertImgUrl(list){
  return hyRequest.post("/img/insertBatch2",list)
}

export function myproblem(openid){
  return hyRequest.get("/problem/selectopenidtype",{openid})
}