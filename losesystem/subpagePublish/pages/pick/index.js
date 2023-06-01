// subpagePublish/pages/lose/index.js
const app = getApp()
import {
  getCategory,
  uploadImg,
  insertImg,
  publish,
  publishData2
} from '../../server/lose_api'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    title: "",
    category: "",
    category_id: 0,
    place: "",
    detail: "",
    phone: "",
    columns: [],
    columns2:[],//备份数组
    show: false,
    fileList: [],
    imguuid: "",
    imglist: []
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
  // 弹出层显示时，发送网络请求获取类别
  async PopSelect() {
    let res = await getCategory()

    let data = []
    res.data.forEach(item => {
      data.push({text:item.name,id:item.id})
    })
    this.setData({
      columns:data,
      show: true
    })
    console.log(res)
  },
  onConfirm(event) {
    const {
      picker,
      value,
      index
    } = event.detail;
    console.log("确认", event.detail)
    this.setData({
      show: false,
      category: value.text,
      category_id: value.id
    })
  },
  onCancel(event) {
    const {
      picker,
      value,
      index
    } = event.detail;
  },
  // 弹出层改变时
  onChange(event) {
    console.log(event.detail)
    const {
      picker,
      value,
      index
    } = event.detail;
    this.setData({
      category_id: value.id
    })
  },
  async confirm() {
    wx.showToast({
      title: '发布中...',
      icon:"loading"
    })
    console.log("确认发布")
    let {
      title,
      category_id,
      place,
      detail,
      phone,
      imguuid,
      fileList,
      imglist
    } = this.data
    for (let i = 0; i < fileList.length; i++) {
      let res = await uploadImg(fileList[i])
      //  下面这些是同步的
      if (res.statusCode === 200) {
        imglist.push({
          id: 0,
          url: JSON.parse(res.data).url,
          uuid: "",
          filename: JSON.parse(res.data).filename
        })
      }
    }

    let uuid = await insertImg(imglist)
    console.log(uuid.data,"uuid")
    this.setData({
      imglist,
      imguuid:uuid.data.code===200?uuid.data.message:""
    })

    publishData2.title = title
    publishData2.itemCategory = category_id
    publishData2.place = place
    publishData2.detail = detail
    publishData2.contact = phone
    publishData2.useropenid = app.data.openid
    publishData2.imguuid = uuid.data.message
    const p = await publish(publishData2)
    console.log(p.data)
    if(p.data>0){
      wx.showToast({
        title: '发布成功',
        icon:"success"
      })
      setTimeout(()=>{
        wx.switchTab({
          url: '../../../pages/index/index',
        })
      },1500)
    }else{
      wx.showToast({
        title: '出错了,请联系客服',
        icon:"error"
      })
    }
    console.log(publishData2)
  }

})