// index.js
const app = getApp()
import {
  getStatus,
  getBytype
} from '../../server/home_api'
Page({
  data: {
    value: "",
    radio: 'pick',
    curPage: 1,
    empty: false,
    list: []
  },
  handleSearch() {
    wx.navigateTo({
      url: '../../subpagePublish/pages/search/index',
    })
  },
  onSearch: function () {
    console.log(this.data.value)
  },
  onChange(event) {
    let this_ = this
    console.log(event.detail)
    switch (event.detail) {
      // case 'all':
      //   getBypage(this.data.curPage, 20).then(e => {
      //     console.log(e.data.data)
      //     this_.setData({
      //       list: e.data.data,
      //       empty: e.data.data.length > 0 ? false : true
      //     })
      //   })
      //   break
      case 'lose':
        getBytype(0, 1, 20, app.data.token).then(e => {
          console.log(e)
          this_.setData({
            list: e.data.data,
            empty: e.data.data.length > 0 ? false : true
          })
        })
        break
      case 'pick':
        getBytype(1, 1, 20, app.data.token).then(e => {
          console.log(e)
          this_.setData({
            list: e.data.data,
            empty: e.data.data.length > 0 ? false : true
          })
        })
        break
      case 'finish':
        getStatus(1, 20).then(e => {
          console.log(e,"已完成")
          this_.setData({
            list: e.data.data,
            empty: e.data.data.length > 0 ? false : true
          })
        })
        break
    }
    this.setData({
      radio: event.detail,
    });
  },
  handlebottom() {
    console.log("滑动到底部")
  },
  handleTodetail(e) {
    console.log(e.currentTarget.dataset.id)
    wx.navigateTo({
      url: '../../subpagePublish/pages/detail/index?id=' + e.currentTarget.dataset.id,
    })
  },
  onLoad() {
    console.log(app.data.token, "token")
  },
  onShow() {
    let this_ = this
    getBytype(1, 1, 20, app.data.token).then(e => {
      console.log(e)
      this_.setData({
        list: e.data.data,
        radio:"pick",
        empty: e.data.data.length > 0 ? false : true
      })
    })
  },

})