// subpageMe/pages/indentify/index.js
import {indentify} from '../../server/indentify_api'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    name:"",
    id_number:"",
    openid:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },
 
  confirm(){
    const {openid,name,id_number} = this.data
    indentify(openid,name,id_number).then(res=>{
      console.log(res.data,"实名认证")
      if(res.data.state===1){
        wx.showToast({
          icon:"success",
          title: "认证成功",
        })

        wx.redirectTo({
          url: '../mydetail/index',
        })
      }else{
        wx.showToast({
          icon:"error",
          title: "不一致，请重新输入",
        })
      }

    })
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
    const app = getApp()
    const {openid} = app.data
    this.setData({
      openid
    })
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