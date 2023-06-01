import * as actionType from "./constants";
import {getinfo} from '@/services/mine'

const geMineAction = (res)=>{
  return {
    type:actionType.MINE,
    mine:res
  }
}
export const getMineThunkAction=(username,password)=>{
return dispatch=>{
   getinfo(username,password).then(res=>{
    console.log("我的",res)
    dispatch(geMineAction(res))
   })
}
}