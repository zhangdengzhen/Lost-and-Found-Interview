// app.js
import {
  getCode,
  sendCode,
} from './server/app_api'
App({
  // 生命周期回调——监听小程序初始化。
  data: {
    baseurl: "localhost",
    openid: "",
    session_key: "",
    unionid: "",
    nickname: "",
    phone: "",
    school: "",
    windowHeight: 0,
    avator: "",
    ws: false,
    token: "",
    SocketTask: null,
    encode_id_number: "",
    id_number: "",
    name: "",
    ws_time:null
  },
  onLaunch() {
    wx.showToast({
      icon: "loading",
      title: '加载中',
    })
    let this_ = this

    // 获取系统信息
    wx.getSystemInfo({
      success: function (res) {
        // 获取可使用窗口宽度
        let clientHeight = res.windowHeight;
        // 获取可使用窗口高度
        let clientWidth = res.windowWidth;
        // 算出比例
        let ratio = 750 / clientWidth;
        // 算出高度(单位rpx)
        let height = clientHeight * ratio;
        // 设置高度
        this_.data.windowHeight = height
        console.log("屏幕高度", height)
      }
    });
    getCode().then(e => {
      console.log("登录", e.code)
      //获取openid
      sendCode(e.code).then(e => {
        console.log("授权数据", e.data)
        this_.data.session_key = e.data.session_key
        this_.data.openid = e.data.openid
        this_.data.unionid = e.data.unionid
        this_.data.token = e.data.token
        this_.data.encode_id_number = e.data.indentify.encode_id_number
        this_.data.id_number = e.data.indentify.id_number
        this_.data.name = e.data.indentify.name

        if (e.data.users) {
          console.log("存在users")
          this_.data.avator = e.data.users.avator
          this_.data.phone = e.data.users.phone
          this_.data.school = e.data.users.school
          this_.data.nickname = e.data.users.nickname
        }

        // wx.setStorageSync("token", e)
        this_.initwebsocket(e.data.openid)
      })
    })
  },
  initwebsocket(openid) {
    let this_ = this
    
    this_.data.SocketTask = wx.connectSocket({
      url: 'ws://localhost:3000/' + openid,
      success: function (e) {
        this_.data.ws = true
        console.log("ws链接成功")
   
      },
      fail: function (err) {
        console.log(err, "ws连接失败")
      }
    })
    
    this_.data.SocketTask.onOpen(e => {
      console.log("打开ws链接")
    })
    this_.data.SocketTask.onClose(e => {
      console.log("关闭ws链接")
      
    })
    this_.data.SocketTask.onError(e => {
      console.log("ws出错")
     
    })

    if(this_.data.ws_time){
      clearInterval(this_.data.ws_time)
    }
    this_.data.ws_time=setInterval(function () {
      this_.data.SocketTask.send({
        data:JSON.stringify({ping:2}),
        success:function () {
          console.log("心跳包")
        }
      })
    },8000)
  },
  onUnload() {
    if (this.data.ws) {
      this.data.SocketTask.close()
      this.data.ws = false
    }
  }
})