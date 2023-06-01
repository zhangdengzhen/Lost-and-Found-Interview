import axios from 'axios'
class request{
  get(url:string,data={}){
    return new Promise((resolve,reject)=>{
      axios.get(url,{params:data}).then(e=>{
        resolve(e)
      }).catch(e=>{
        reject(e)
      })
    })
  }
  post(url:string,data={}){
    return new Promise((resolve,reject)=>{
      axios.post(url,data).then(e=>{
        resolve(e)
      }).catch(e=>{
        reject(e)
      })
    })
  }
}
export const network = new request()