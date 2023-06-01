const genericPool = require("generic-pool");
const DbDriver = require("ioredis");
/**
 * Step 1 - Create pool using a factory object
 */
const factory = {
  create: function () {
    return new DbDriver({
      host: "localhost",
      port: 6379,
    });
  },
  destroy: function (client) {
    client.disconnect();
  },
};

const opts = {
  max: 10, // maximum size of the pool
  min: 3, // minimum size of the pool
};

const myPool = genericPool.createPool(factory, opts);

/**
 * Step 2 - Use pool in your code to acquire/release resources
 */

// acquire connection - Promise is resolved
// once a resource becomes available

module.exports = {
  hset(key, field, value) {
    myPool
      .acquire()
      .then(function (client) {
        client.hset(key, field, value).then((res) => {
          // console.log(res, "哈希添加成功");
          myPool.release(client);
        });
      })
      .catch(function (error) {
        // console.log(error, "哈希出错了");
      });
  },
  sadd(key, value) {
    myPool
      .acquire()
      .then(function (client) {
        client.sadd(key, value).then((res) => {
          // console.log(res, "集合添加成功");
          myPool.release(client);
        });
      })
      .catch(function (error) {
        // console.log(error, "集合出错了");
      });
  },
  rpush(key, value) {
    myPool
      .acquire()
      .then(function (client) {
        client.rpush(key, value).then((res) => {
          // console.log(res, "列表添加成功");
          myPool.release(client);
        });
      })
      .catch(function (error) {
        // console.log(error, "列表出错了");
      });
  },
};
// resourcePromise
//   .then(function(client) {
//     client.query("select * from foo", [], function() {
//       // return object back to pool
//       myPool.release(client);
//     });
//   })
//   .catch(function(err) {
//     // handle error - this is generally a timeout or maxWaitingClients
//     // error
//   });
