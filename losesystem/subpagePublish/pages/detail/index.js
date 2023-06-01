// subpagePublish/pages/detail/index.js
import {
  getbyid,insertcomplain
} from '../../server/detail_api'
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    item: null,
    show: false,
    detail:"",
    concat:""
  },
  // 弹窗
  onConfirm(event) {

    console.log("确认关闭",this.data.detail,this.data.concat,event.currentTarget.dataset.id);
    insertcomplain(app.data.openid,event.currentTarget.dataset.id,this.data.detail,this.data.concat).then(res=>{
      console.log(res)
      if(res.data>0){
        wx.showToast({
          title: '提交成功',
          icon:"success"
        })
      }else{
        wx.showToast({
          title: '提交失败',
          icon:"error"
        })
      }
    })
  },
  handlecompalin(){
    this.setData({
      show:true
    })
  },
  onClose() {
    this.setData({ show: false });
  },
  handleconcat() {
    if (app.data.openid === this.data.item.users.openid) {
      wx.showToast({
        icon: 'error',
        title: '不可与自己联系',
      })
      return
    } else {
      let item = {
        avator: this.data.item.users.avator,
        nickName: this.data.item.users.nickname,
        openid: this.data.item.users.openid
      }
      wx.navigateTo({
        url: '../../../subpageChat/pages/chat/index?data=' +
          encodeURIComponent(JSON.stringify(item)),
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    console.log(options)
    getbyid(options.id).then(e => {
      this.setData({
        item: e.data
      })
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