let ws = require("ws");
let socketServer = ws.Server;
let ws2 = new socketServer({ port: 3000 }); //创建websocketServer实例监听3000端口
let clients = new Map();
const redis = require("./redis.js");
const Curtime = require("./utils.js");
console.log("运行在3000端口");
//监听连接
ws2.on("connection", function (ws, req) {
  let client = req.url.split("/")[1];
  // console.log(req.url.split("/")[1]);

  clients.set(req.url.split("/")[1], ws);
  /*监听断开连接*/
  ws.on("close", function () {
    clients.delete(client);
  });

  ws.on("message", function (msg) {
    // console.log(msg.toString());
    if(JSON.parse(msg.toString()).ping){
      return
    }
    const { fromOpenid, toOpenid, type, message } = JSON.parse(msg.toString());
    redis.sadd(fromOpenid, JSON.stringify({ openid: toOpenid }));
    redis.sadd(toOpenid, JSON.stringify({ openid: fromOpenid }));
    // 保存聊天记录
    redis.rpush(
      fromOpenid + "-" + toOpenid + "-record",
      JSON.stringify({
        fromOpenid,
        toOpenid,
        type,
        message,
        time: Curtime.getNowFormatDate(),
      })
    );
    redis.rpush(
      toOpenid + "-" + fromOpenid + "-record",
      JSON.stringify({
        fromOpenid,
        toOpenid,
        type,
        message,
        time: Curtime.getNowFormatDate(),
      })
    );
    console.log(fromOpenid, toOpenid, type, message, "\n");
    ws.send(msg.toString());
    if (clients.has(toOpenid)) {
      // console.log("存在", toOpenid);
      clients.get(toOpenid).send(msg.toString());
    } else {
      //客户端不在线，存入redis
    }
  });
});
