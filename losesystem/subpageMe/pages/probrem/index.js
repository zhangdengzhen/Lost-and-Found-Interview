// subpageMe/pages/probrem/index.js
import {
  insert,
  upload,
  batchInsertImgUrl,
  myproblem

} from '../../server/problem_api'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    problem: "",
    concat: "",
    fileList: [],
    imglist: [],
    imguuid: "",
    show: false,
    myproblem:[]
  },
  handledelete(event) {
    let fileList = this.data.fileList.filter((value, key) => {
      return key != event.detail.index
    })
    this.setData({
      fileList
    })
    console.log(event.detail.index)
  },
  afterRead(event) {
    const {
      file
    } = event.detail;
    console.log(file, "文件")
    let {
      fileList
    } = this.data
    fileList.push(file)
    this.setData({
      fileList
    })
    // 当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
  },
  async onChange(event) {
    console.log(event.detail)
    if(event.detail.index>0){
      const {openid} = getApp().data
      const res = await myproblem(openid)
      console.log(res.data,"问题反馈")
      this.setData({
        myproblem:res.data
      })
    }
  },
  async handleSubmit() {
    const {
      fileList,
      problem,
      concat,

    } = this.data
    if (!concat || !problem) {
      wx.showToast({
        icon: "error",
        title: '请输入完整内容',
      })
      return
    }
    this.setData({
      show: true
    })
    let imguuid = ""
    const app = getApp()
    const {
      openid
    } = app.data
    let insert_res = 0
    if (fileList.length > 0) {
      let list = []
      for (let i = 0; i < fileList.length; i++) {
        const res = await upload(fileList[i])
        list.push({
          filename: JSON.parse(res.data).filename,
          id: 0,
          uuid: "",
          url: JSON.parse(res.data).url
        })
      }
      this.setData({
        imglist: list
      })
      const res = await batchInsertImgUrl(list)
      console.log(res, "批量插入")
      imguuid = res.data.message
      insert_res = await insert(problem, concat, imguuid, openid)
      console.log("提交成功1", insert_res)
    } else {
      insert_res = await insert(problem, concat, imguuid, openid)
      console.log("提交成功2", insert_res)
    }
    if (insert_res.data > 0) {
      wx.showToast({
        icon: "success",
        title: '提交成功',
      })
      setTimeout(()=>{
        wx.switchTab({
          url: '../../../pages/me/index',
        })
      },1500)
    } else {
      wx.showToast({
        icon: "error",
        title: '提交失败',
      })
    }

  },
  onClickHide() {
    this.setData({
      show: false
    });
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