import * as actionType from "./constants";
import {getall,categorydelete,categoryinsert} from '@/services/category'

//获取类别
const getCategoryAction = (res)=>{
  return {
    type:actionType.CATEGORY,
    category:res
  }
}
export const getCategoryThunkAction = ()=>{
return (dispatch)=>{
  getall().then(res=>{
    console.log("获取类别",res)
    dispatch(getCategoryAction(res))
  })
}
}

//删除类别
const categoryDeleteAction = (res)=>{
  return {
    type:actionType.CATEGORYDELETE,
    categorydelete:res
  }
}
export const categoryDeleteThunkAction = (id)=>{
  return async dispatch=>{
     const data = await categorydelete(id)
    dispatch(categoryDeleteAction(data))
    return data
  }
}
//添加类别
const categoryInsertAction = (res)=>{
  return {
    type:actionType.CATEGORYINSERT,
    categoryinsert:res
  }
}
export const categoryinsertThunkAction=(name)=>{
  return async dispatch=>{
    const data = await categoryinsert(name)
    dispatch(categoryDeleteAction(data))
    return data
  }
}