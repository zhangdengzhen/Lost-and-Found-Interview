import * as actionType from "./constants";
import {getpublish} from '@/services/goods'

const getPublishAction=(res)=>{
  return {
    type:actionType.MYPUBLISH,
    publish:res
  }
}

export const getPublishThunkAction = (currIndex,pageSize)=>{
  return dispatch=>{
    getpublish(currIndex,pageSize).then(res=>{
      console.log(res,"我的发布action")
      dispatch(getPublishAction(res))
    })
  }
}