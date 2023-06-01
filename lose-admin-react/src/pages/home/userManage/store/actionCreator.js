import * as actionType from "./constants";
import {getusers} from '@/services/users'

const getUsersAction = (res)=>{
  return {
    type:actionType.USER_INFO,
    usersinfo:res
  }
}
export const getUsersThunkAction = (currenindex,pagesize)=>{
return (dispatch)=>{
  getusers(currenindex,pagesize).then(res=>{
    console.log("获取用户信息",res)
    dispatch(getUsersAction(res))
  })
}
}