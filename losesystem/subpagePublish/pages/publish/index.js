// subpagePublish/pages/publish/index.js
import {
  getByType,
  getUserpublish,
  deletebyid,
  updatestatus,
  getbyOpenidStatus
} from '../../server/publish_api'
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    radio: "pick",
    list: [],
    empty: false
  },
  onChange(event) {
    let this_ = this
    switch (event.detail) {
      // case 'all':
      //   getUserpublish(app.data.openid).then(e => {
      //     console.log(e.data)

      //     this_.setData({
      //       list: e.data,
      //       empty: e.data.length > 0 ? false : true
      //     })
      //   })
      //   break
      case 'lose':
        getByType(app.data.openid, 0).then(e => {
          console.log(e.data)
          this_.setData({
            list: e.data,
            empty: e.data.length > 0 ? false : true
          })
        })
        break
      case 'pick':
        getByType(app.data.openid, 1).then(e => {
          console.log(e.data)
          this_.setData({
            list: e.data,
            empty: e.data.length > 0 ? false : true
          })
        })
        break
      case 'finish':
        getbyOpenidStatus(app.data.openid, "ed").then(e => {
          console.log(e.data,"获取数据")
          this_.setData({
            list: e.data,
            empty: e.data.length > 0 ? false : true
          })
        })
        break
    }
    this.setData({
      radio: event.detail,
    });
  },
  updateItem(event) {
    console.log(event)
    updatestatus(event.currentTarget.dataset.id).then(e => {
      if (e.data > 0) {
        wx.showToast({
          icon: "success",
          title: '修改成功',
        })
      } else {
        wx.showToast({
          icon: "error",
          title: '修改失败',
        })
      }
    })
  },
  deleteItem(event) {
    console.log(event)
    let this_ = this
    let {
      list
    } = this.data
    deletebyid(event.currentTarget.dataset.id).then(e => {
      console.log(e.data, "删除")
      wx.showToast({
        icon: "success",
        title: '删除成功',
      })
      this_.setData({
        list: list.filter(item => item.id != event.currentTarget.dataset.id),
        empty: list.length > 0 ? false : true
      })
    })

  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    let this_ = this
    getByType(app.data.openid, 1).then(e => {
      console.log(e.data)
      this_.setData({
        list: e.data,
        empty: e.data.length > 0 ? false : true,
        radio:"pick"
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