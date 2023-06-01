import hyRequest from '../../server/index'
export function senSms(phone){
  return hyRequest.get("/sms/sendcode",{phone:phone})
}
export function validate(code,sessionid){
  return hyRequest.get("/sms/validate",{code:code},"",sessionid)
}