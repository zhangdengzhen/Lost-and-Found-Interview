// pages/info/index.js
import {
  getfriend
} from '../../server/info_api'
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list: [],
    empty: false
  },
  handlebottom: function () {
    console.log("滑动到底部")
  },
  handleChat(e) {
    console.log(e.currentTarget.dataset.item)
    wx.navigateTo({
      url: '../../subpageChat/pages/chat/index?data=' + encodeURIComponent(JSON.stringify(e.currentTarget.dataset.item))
    })
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
    getfriend(app.data.openid).then(e => {
      console.log(e)
      if (e.statusCode === 200) {
        this.setData({
          list: e.data,
          empty: e.data.length > 0 ? false : true
        })

      } else {
        this.setData({
          empty: true
        })
      }
    }).catch(e => {
      this.setData({
        empty: true
      })
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