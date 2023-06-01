import React, { memo, useEffect, useState } from "react";
//action
import {
  getCategoryThunkAction,
  categoryDeleteThunkAction,
  categoryinsertThunkAction,
} from "./store/actionCreator";
//antd
import { Table, Button, message, Modal, Form, Input } from "antd";
//redux
import { useDispatch, useSelector, shallowEqual } from "react-redux";
export default memo(function App() {
  const [messageApi, contextHolder] = message.useMessage();
  //redux
  const { category, categorydelete } = useSelector((state) => {
    return {
      category: state.getIn(["category", "category"]),
      categorydelete: state.getIn(["category", "categorydelete"]),
    };
  }, shallowEqual); //浅层比较

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getCategoryThunkAction());
  }, [dispatch]);

  //antd
  //table
  const columns = [
    {
      title: "Id",
      dataIndex: "id",
      key: "id",
    },
    {
      title: "物品名称",
      dataIndex: "name",
      key: "name",
    },
    {
      title: "操作",
      dataIndex: "delete",
      key: "delete",
      render: (text, record) => (
        <Button danger type="primary" onClick={(e) => handledelete(record)}>
          删除
        </Button>
      ),
    },
  ];

  //antd modal
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

  //antd 表单
  const onFinish = (values) => {
    console.log("Success:", values);
    dispatch(categoryinsertThunkAction(values.name)).then((res) => {
      if (res > 0) {
        dispatch(getCategoryThunkAction());
        messageApi.open({
          type: "success",
          content: "添加成功",
        });
        handleOk()
      } else {
        messageApi.open({
          type: "error",
          content: "添加失败",
        });
      }
    });
  };
  const onFinishFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
  };

  const handledelete = (record) => {
    console.log("删除", record);
    dispatch(categoryDeleteThunkAction(record.id)).then((res) => {
      console.log("dispatch回调函数", res);
      if (res > 0) {
        dispatch(getCategoryThunkAction());
        messageApi.open({
          type: "success",
          content: "删除成功",
        });
      } else {
        messageApi.open({
          type: "error",
          content: "删除失败",
        });
      }
    });
    console.log(categorydelete);
  };
  return (
    <div>
      {contextHolder}
      <Button type="primary" onClick={showModal}>
        添加
      </Button>
      <Table
        columns={columns}
        rowKey={(record) => record.id}
        dataSource={category}
      />
      <Modal
        title="添加类别"
        open={isModalOpen}
        onOk={handleOk}
        onCancel={handleCancel}
      >
        <Form
          name="basic"
          labelCol={{
            span: 8,
          }}
          wrapperCol={{
            span: 16,
          }}
          style={{
            maxWidth: 600,
          }}
          initialValues={{
            remember: true,
          }}
          onFinish={onFinish}
          onFinishFailed={onFinishFailed}
          autoComplete="off"
        >
          <Form.Item
            label="名称"
            name="name"
            rules={[
              {
                required: true,
                message: "请输入类别名称",
              },
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            wrapperCol={{
              offset: 8,
              span: 16,
            }}
          >
            <Button type="primary" htmlType="submit">
              提交
            </Button>
          </Form.Item>
        </Form>
      </Modal>
    </div>
  );
});
