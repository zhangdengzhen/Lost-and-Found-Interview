// subpageMe/pages/mydetail/index.js
import {getUserinfo,updateAvator,uploadImg} from '../../server/me_api'
let app =getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    avatarUrl: "",
    nickname:"",
    openid:"",
    school:"",
    phone:"",
    id_number:"",
    encode_id_number:"",
    name:""
  },
  onChooseAvatar(e) {
    console.log(e)
    const {
      avatarUrl
    } = e.detail
    wx.showToast({
      icon:"loading",
      title:"加载中..."
    })
    console.log(e)
    let this_=this
    uploadImg(avatarUrl).then(res=>{
      const {url} = JSON.parse(res.data)
      updateAvator(app.data.openid,url).then(e=>{
        wx.showToast({
          icon:"success",
          title:"修改成功"
        })
        app.data.avator=url
        console.log("图像",res)
        this_.setData({
          avatarUrl:url,
        })
      })
    })

  },
  toNickname(){
    wx.navigateTo({
      url: '../nickname/index',
    })
  },
  toIndentify(){
    wx.navigateTo({
      url: '../indentify/index',
    })
  },
  toSchool(){
    wx.navigateTo({
      url: '../school/index',
    })
  },
  toPhone(){
    wx.navigateTo({
      url: '../phone/index',
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
    const app = getApp()
    const {id_number,name,encode_id_number} = app.data
    getUserinfo(app.data.openid).then(e=>{
      console.log(e.data,"用户信息")
      this.setData({
        avatarUrl:e.data.users.avator,
        nickname:e.data.users.nickname,
        phone:e.data.users.phone,
        school:e.data.users.school,
        openid:e.data.users.openid,
        id_number:e.data.indentify.id_number,
        name:e.data.indentify.name,
        encode_id_number:e.data.indentify.encode_id_number
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