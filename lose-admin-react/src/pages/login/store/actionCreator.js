import * as actionType from "./constants";

import {login} from '@/services/login'

const changeLoginAvtion=(res)=>{
  return {
    type:actionType.LOGIN,
    token:res
  }
}
export const loginaction = (username,password)=>{
  return dispatch=>{
    login(username,password).then(res=>{
      // console.log(res,"登录")
      dispatch(changeLoginAvtion(res))
    })
  }
}