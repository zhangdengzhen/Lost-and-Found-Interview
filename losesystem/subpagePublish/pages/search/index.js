// subpagePublish/pages/search/index.js
import {search,upload,getbyfilename} from '../../server/search_api'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list:[],
    liststate:false,
    listimg:[],
    value:"",
    empty:false
  },
  async afterRead(event) {
    const { file } = event.detail;
    // 当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
    let this_ = this
    try {
      wx.showToast({
        icon:'loading',
        title: '加载中',
      })
      let data = await upload(file)
      let list = JSON.parse(data.data).data
      console.log(list)
      this_.setData({
        listimg:list,
        liststate:false,
        empty:list.length>0?false:true
      })
    } catch (error) {
      console.log("失败",error)
      wx.showToast({
        icon:'error',
        title: '加载失败',
      })
    }
  },
  handletodetail(e){
    console.log(e.currentTarget.dataset.item.id)
    wx.navigateTo({
      url: '../detail/index?id='+e.currentTarget.dataset.item.id,
    })
  },
  handeltodetail2(e){
      console.log(e,e.currentTarget.dataset.name.split("/").slice(-1)[0])
      getbyfilename(e.currentTarget.dataset.name.split("/").slice(-1)[0]).then(e=>{
        console.log(e.data)
        wx.navigateTo({
          url: '../detail/index?id='+e.data.id,
        })
      }).catch(e=>{
        wx.showToast({
          icon:"error",
          title: '出错了',
        })
      })
  },
  onSearch(){
    wx.showToast({
      title: '搜索中...',
      icon:'loading'
    })
    search(this.data.value)
    .then(e=>{
      console.log(e.data)
      this.setData({
        list:e.data,
        liststate:true,
        empty:e.data.length>0?false:true
      })
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