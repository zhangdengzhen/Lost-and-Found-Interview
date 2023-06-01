//react
import React, { memo } from "react";
// import request from "@/services/request";
import md5 from 'blueimp-md5'
//router
import { useNavigate } from "react-router-dom";
//redux

import { loginaction } from "./store/actionCreator";
import { useDispatch, useSelector, shallowEqual } from "react-redux";
//antd
import { Button, Checkbox, Form, Input, message } from "antd";

//style
import { LoginWrapper } from "./style";
export default memo(function App() {
  //antd
  const [messageApi, contextHolder] = message.useMessage();
  //router
  const navigate = useNavigate();
  //redux
  const { token } = useSelector((state) => {
    return {
      token: state.getIn(["login", "token"]),
    };
  }, shallowEqual); //浅层比较

  const dispatch = useDispatch();
  const onFinish = async (values) => {
    dispatch(loginaction(values.username, values.password));
    console.log("token", token);
    if (token) {
      // request.defaults.headers.common["Authorization"] = token;
      localStorage.setItem("token", token);
      messageApi.open({
        type: "success",
        content: "登录成功,即将跳转到首页",
      });
      localStorage.setItem(
        "user",
        JSON.stringify({ username: values.username, password: md5(values.password) })
      );
      setTimeout(() => {
        navigate("/home/users");
      }, 1000);
    }
  };

  const onFinishFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
  };

  return (
    <LoginWrapper>
      {contextHolder}
      <div>
      <h1>失物招领后台管理系统</h1>
      <Form
        name="basic"
        labelCol={{ span: 8 }}
        wrapperCol={{ span: 16 }}
        style={{ maxWidth: 600 }}
        initialValues={{ remember: true }}
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        autoComplete="off"
      >
        <Form.Item
          label="用户名"
          name="username"
          rules={[{ required: true, message: "请输入用户名" }]}
        >
          <Input />
        </Form.Item>

        <Form.Item
          label="密码"
          name="password"
          rules={[{ required: true, message: "请输入密码" }]}
        >
          <Input.Password />
        </Form.Item>

        <Form.Item
          name="remember"
          valuePropName="checked"
          wrapperCol={{ offset: 8, span: 16 }}
        >
          <Checkbox>记住我</Checkbox>
        </Form.Item>

        <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
          <Button type="primary" htmlType="submit">
            登录
          </Button>
        </Form.Item>
      </Form>
      </div>
    </LoginWrapper>
  );
});
