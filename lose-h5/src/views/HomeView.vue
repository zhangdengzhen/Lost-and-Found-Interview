<template>
  <van-skeleton title avatar :row="3" :loading="loading">
    <h3 style="text-align: center;">订阅</h3>
    <h6 style="text-align: center;">{{ subscribe.receive===1 ? "已订阅" : "已关闭订阅" }}（{{ subscribe ? subscribe.school : "未选择学校" }},{{ subscribe ?
      subscribe.category : "未选择类别" }}）</h6>
    <van-form @submit="onSubmit">
      <van-cell-group inset>
        <van-field name="radio" label="是否接收消息">
          <template #input>
            <van-radio-group v-model="checked" direction="horizontal">
              <van-radio name="1">是</van-radio>
              <van-radio name="0">否</van-radio>
            </van-radio-group>
          </template>
        </van-field>
        <van-field v-model="school" name="school" label="学校" placeholder="学校"
          :rules="[{ required: true, message: '请填写学校名称' }]" />
        <van-field v-model="category_item" name="category" label="类别" placeholder="类别"
          :rules="[{ required: true, message: '请填写类别' }]" @click="showPicker = true" />
      </van-cell-group>
      <div style="margin: 16px;">
        <van-button round block type="primary" native-type="submit">
          提交
        </van-button>
      </div>
    </van-form>
    <van-popup v-model:show="showPicker" round position="bottom">
      <van-picker :columns="category" :columns-field-names="field" @cancel="showPicker = false" @confirm="onConfirm" />
    </van-popup>
  </van-skeleton>
</template>

<script lang="ts">
import { defineComponent, onMounted, reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
import { getcode, getcategry, insert, UserProps, getsubscribe, Subscribe } from '@/server/home_api';
import { network } from '@/server/index'
import { showSuccessToast, showFailToast } from 'vant';
import type {

  PickerFieldNames,

} from 'vant';
const field: PickerFieldNames = {
  text: 'name', value: 'id', children: 'children'
}
export default defineComponent({
  name: 'HomeView',
  components: {

  },
  setup(props, ctx) {


    let loading = ref(false)

    let isCode = () => { return window.location.search.includes('code=') }
    let category = ref([])
    const router = useRouter()

    // 获取用户信息
    let getUserinfo = () => {
      let search = window.location.search
      const url_params = Object.fromEntries(window.location.search.slice(1).split('&').map(v => v.split('=')))
      console.log(url_params)
      console.log(search)
      network.get("/h5/getuserinfo", { code: url_params.code }).then((res: any) => {
        console.log(res.data, "用户信息")
        if (res.data.errcode) {
          getcode()
        } else {
          localStorage.setItem("userinfo", JSON.stringify(res.data))
        }
        // router.push("/category")
      })
    }

    // 登录时是否获取凑得
    if (isCode()) {
      getUserinfo()
    } else {
      getcode()
    }


    // 表单提交
    const school = ref('');
    const category_item = ref('');
    const category_id = ref(0)

    const onSubmit = (values: any) => {
      console.log('submit', values);
    
        console.log("是的")
        const { country, headimgurl, nickname, openid, sex } = JSON.parse(localStorage.getItem("userinfo")!)
        insert({
          id: 0,
          category: category_id.value,
          country: country,
          headimgurl: headimgurl,
          nickname: nickname,
          school: values.school,
          openid: openid, sex: sex,
          receive:parseInt(values.radio)
        }).then(e => {
          console.log(e, "插入成功")
          getsubscribe(openid).then((e: any) => {
          console.log("成功,我的订阅", e.data)
          if (e.data) {
            subscribe.school = e.data.school
            subscribe.category = e.data.category.name
            subscribe.receive=e.data.receive
          }
          console.log("subscribe", subscribe)
        })
        })
     
      // showSuccessToast('提交成功');
    };
    // 选择器

    let type = ref('')
    let showPicker = ref(false)
    const onConfirm = (data: any) => {
      console.log(data, '选择器')
      showPicker.value = false;

      category_item.value = data.selectedOptions[0].name;
      category_id.value = data.selectedOptions[0].id;
    }

    // const checked = ref('1');
    const checked = ref('1');

    let subscribe = reactive({ school: "", category: "",receive:0 })
    onMounted(() => {
      getcategry().then((e: any) => {
        console.log(e)
        category.value = e.data
        loading.value = false
      })
      if (localStorage.getItem("userinfo")) {
        getsubscribe(JSON.parse(localStorage.getItem("userinfo")!).openid).then((e: any) => {
          console.log("成功,我的订阅", e.data)
          if (e.data) {
            subscribe.school = e.data.school
            subscribe.category = e.data.category.name
            subscribe.receive=e.data.receive
          }
          console.log("subscribe", subscribe)
        })
      }
    })
    return {
      school, category_item, checked, onSubmit,
      category, loading, type, showPicker, onConfirm, field, subscribe
    };
  },
});
</script>
<style scoped>
.home {
  padding: 20px 30px;
  text-align: center;
}
</style>