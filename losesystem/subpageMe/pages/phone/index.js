// subpageMe/pages/phone/index.js
import {
  senSms,
  validate
} from '../../server/phone_api'
import {
  updatePhone
} from '../../server/me_api'
const app = getApp()
Page({
  data: {
    phone: "",
    sms: "",
    auto: false,
    time: 60 * 1000,
    timeData: {},
    status: false,
    sessionid: ""
  },

  start() {
    this.setData({
      status: true,
      auto: true
    })
    senSms(this.data.phone).then(e => {
      console.log(e, e.header['Set-Cookie'])
      if (e.status === 200) {
        wx.showToast({
          icon: "success",
          title: e.message,
        })
      } else {
        wx.showToast({
          icon: "error",
          title: e.message,
        })
      }
      this.setData({
        sessionid: e.header['Set-Cookie']
      })
    })
  },
  finished() {
    this.setData({
      status: false,
      auto: false,
      time: 60000
    })
  },
  onChange(e) {
    this.setData({
      timeData: e.detail,
    });
  },
  confirm() {
    validate(this.data.sms, this.data.sessionid)
      .then(e => {
        console.log(e.data)
        if (e) {

          updatePhone(app.data.openid, this.data.phone).then(e => {
            wx.showToast({
              icon: "success",
              title: '修改手机号成功',
            })
            setTimeout(() => {
              wx.redirectTo({
                url: '../mydetail/index',
              })
            }, 1500)
          })

        } else {
          wx.showToast({
            icon: "error",
            title: '验证码错误',
          })
        }
      })
    console.log(this.data.phone, this.data.sms)
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