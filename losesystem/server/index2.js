const BASE_URL = "http://localhost:8001"
// const token = wx.getStorageSync("token")
// 操作 websocket
class HYRequest {
  request(url, method, params,token,sessionid) {
    return new Promise((resolve, reject) => {
      wx.request({
        url: BASE_URL + url,
        method: method,
        header:{
          'Authorization': token,
          'Cookie':sessionid
        },
        data: params,
        success: function(res) {
          resolve(res)
        },
        fail: reject
      })
    })
  }

  upload(url,file){
    return new Promise((resolve, reject) => {
      wx.uploadFile({
        url: BASE_URL + url, // 仅为示例，非真实的接口地址
        filePath: file.url,
        name: 'file',
        formData: { user: 'test' },
        success(res) {
          resolve(res)
        },
        fail:reject
      });
    })
   
  }
  uploadbyurl(url,avator){
    return new Promise((resolve, reject) => {
      wx.uploadFile({
        url: BASE_URL + url, // 仅为示例，非真实的接口地址
        filePath: avator,
        name: 'file',
        formData: { user: 'test' },
        success(res) {
          resolve(res)
        },
        fail:reject
      });
    })
   
  }

  login(){
    return new Promise((resolve,reject)=>{
      wx.login({
        success (res) {
          resolve(res)
        },
        fail(res){
          reject(res)
        }
      })
    })
    
  }
  get(url, params,token="",sessionid="") {
    return this.request(url, "GET", params,token,sessionid)
  }

  post(url, data,token="",sessionid="") {
    return this.request(url, "POST", data,token,sessionid)
  }
}

const hyRequest = new HYRequest()

export default hyRequest
