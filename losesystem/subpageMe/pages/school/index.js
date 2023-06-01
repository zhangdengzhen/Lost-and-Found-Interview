// subpageMe/pages/school/index.js
import {updateSchool} from '../../server/me_api'
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    nickname:""
  },

  confirm(){
    updateSchool(app.data.openid,this.data.nickname).then(e=>{
      wx.showToast({
        icon:"success",
        title: '修改成功',
      })
      wx.redirectTo({
        url: '../mydetail/index',
      })
    })
  }
})