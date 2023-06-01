export  function debounce(fn,delay){
  let timer=null
  return function () {
      if (timer) {
          clearTimeout(timer)
      }
      timer=setTimeout(() => {
          //模拟触发change事件
          fn.apply(this,arguments)
          // 清空计时器
          timer=null
      }, delay);
  }
}