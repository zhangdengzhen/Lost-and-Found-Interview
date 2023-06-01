import hyRequest from './index'
/** 
 * @param {string} openid
*/

export function getfriend(openid) {
  return hyRequest.get("/chatroom/friend",{openid})
}
