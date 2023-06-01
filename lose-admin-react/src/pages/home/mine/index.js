import React, { memo, useEffect, useState } from "react";

//style
import { MineWrapper } from "./style";
//antd
import {
  Button,
  Avatar,
  Descriptions,
  Modal,
  Checkbox,
  Form,
  Input,
} from "antd";
//
import { getMineThunkAction } from "./store/actionCreator";
//redux
import { useDispatch, useSelector, shallowEqual } from "react-redux";
export default memo(function App() {
  const { mine } = useSelector((state) => {
    return {
      mine: state.getIn(["mine", "mine"]),
    };
  }, shallowEqual); //浅层比较
  const { username, password } = JSON.parse(localStorage.getItem("user"));
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getMineThunkAction(username, password));
  }, [dispatch, username, password]);
  console.log(mine);
  //antd
  const [isModalOpen, setIsModalOpen] = useState(false);
  const showModal = () => {
    setIsModalOpen(true);
  };
  const handleOk = () => {
    setIsModalOpen(false);
  };
  const handleCancel = () => {
    setIsModalOpen(false);
  };
  const onFinish = (values) => {
    console.log("Success:", values);
  };
  const onFinishFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
  };
  return (
    <MineWrapper>
      <Descriptions
        title="用户信息"
        column={2}
        extra={
          <Button type="primary" onClick={showModal}>
            修改个人信息
          </Button>
        }
      >
        <Descriptions.Item label="用户名">{mine.username}</Descriptions.Item>
        <Descriptions.Item label="电话">{mine.phone}</Descriptions.Item>
        <Descriptions.Item label="邮箱">{mine.email}</Descriptions.Item>
        <Descriptions.Item label="头像">
          <Avatar src={mine.avator} />
        </Descriptions.Item>
        <Descriptions.Item label="uuid">{mine.uuid}</Descriptions.Item>
      </Descriptions>
      <Modal
        title="修改用户信息"
        open={isModalOpen}
        onOk={handleOk}
        onCancel={handleCancel}
      >
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
            label="Username"
            name="username"
            rules={[{ required: true, message: "Please input your username!" }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="Username"
            name="username"
            rules={[{ required: true, message: "Please input your username!" }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            label="Username"
            name="username"
            rules={[{ required: true, message: "Please input your username!" }]}
          >
            <Input />
          </Form.Item>

          <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
            <Button type="primary" htmlType="submit">
              Submit
            </Button>
          </Form.Item>
        </Form>
      </Modal>
    </MineWrapper>
  );
});
