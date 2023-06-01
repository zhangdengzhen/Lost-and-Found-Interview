// subpageChat/pages/chat/index.js
import {
  getrecord
} from '../../server/chat_api'
const app =getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    myitem: null,
    avator: "",
    nickName: "",
    openid: "",
    list: [],
    toitem: "",
    windowHeight: "",
    say: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    let i = 1
    app.data.SocketTask.onMessage(e => {
      console.log("接收到消息",e)
      let {
        list
      } = this.data
      
      list.push(JSON.parse(e.data))
      this.setData({
        list,
        toitem: "item" + list.length
      })
      console.log(this.data.list)

    })
    console.log(options)
    if (options.data) {
      let myitem = JSON.parse(decodeURIComponent(options.data))
      console.log(JSON.parse(decodeURIComponent(options.data)), "解析的数据")
      wx.setNavigationBarTitle({
        title: myitem.nickname
      })
      this.setData({
        avator: app.data.avator,
        nickName: app.data.nickname,
        openid: app.data.openid,
        myitem,
        windowHeight: app.data.windowHeight
      })
      getrecord(this.data.openid, this.data.myitem.openid).then(e => {
        this.setData({
          list: e.data,
          toitem: "item" + e.data.length,
          empty: e.data.length > 0 ? false : true
        })
      })
    }
  },
  sendMessage() {
    console.log("发送消息")
    let {
      myitem,
      say,
    } = this.data

    let this_ = this
    app.data.SocketTask.send({
      data: JSON.stringify({
        fromOpenid: app.data.openid,
        toOpenid: myitem.openid,
        type: "text",
        message: say
      }),
      suceess:function(){
        this_.setData({
          say:""
        })
      },
      fail:function(){
        wx.showToast({
          icon:"error",
          title: '连接出错，稍后再试',
        })
      }
    })
  },
  // 输入聚焦
  foucus: function (e) {
    var that = this;
    that.setData({
      inputBottom: e.detail.height
    })

  },
  //失去聚焦
  blur: function (e) {
    var that = this;
    that.setData({
      inputBottom: 0
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