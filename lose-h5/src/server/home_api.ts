import {network} from './index'
export function getcode(){
  network
  .get('/h5/getcode')
  .then((response:any) => (window.location.href=response.data))
  .catch(function (error) { // 请求失败处理
    console.log(error);
  });

}
// 获取类别
export function getcategry(){
  return network.get("/category/selectall")
}

export interface UserProps{
    category:number,
    country:string,
    headimgurl: string,
    id:number,
    nickname: string,
    openid: string,
    school: string,
    sex: number
    receive:number
}
// 插入数据
export function insert(user:UserProps){
  return network.post("/h5/inserth5user",user)
}
// 查看我的订阅
export interface Subscribe{
  id: number,
  openid:string,
  nickname: string,
  headimgurl: string,
  school: string,
  country: string,
  sex: 0,
  category:category
}
export interface category {
  id: number,
  name: string
}
export function getsubscribe(openid:string){
  return network.get("/h5/subscribe",{openid:openid})
}