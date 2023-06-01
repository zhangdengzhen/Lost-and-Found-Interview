import hyRequest from '../../server/index'
export function indentify(openid, name, id_number) {
  return hyRequest.post("/identify/indentify", {
    openid,
    name,
    id_number
  })
}