// subpageMe/pages/nickname/index.js
import {updateNickname} from '../../server/me_api'
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    nickname:""
  },
  confirm(){
    updateNickname(app.data.openid,this.data.nickname).then(e=>{
      wx.red({
        icon:"success",
        title: '修改成功',
      })
      wx.redirectTo({
        url: '../mydetail/index',
      })
    })
    console.log(this.data.nickname)
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})