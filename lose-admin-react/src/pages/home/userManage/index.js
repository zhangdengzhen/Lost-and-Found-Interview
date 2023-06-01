import React, { memo, useEffect } from "react";

//axios
import { updateAuthorize } from "@/services/users";
//antd
import { Table, Avatar, Button, Space, Tag, Popconfirm, message } from "antd";
//redux
import { useDispatch, useSelector, shallowEqual } from "react-redux";
import { getUsersThunkAction } from "./store/actionCreator";
export default memo(function App() {
  const { usersinfo } = useSelector((state) => {
    return {
      usersinfo: state.getIn(["users", "usersinfo"]),
    };
  }, shallowEqual); //浅层比较

  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getUsersThunkAction(1, 10));
  }, [dispatch]);

  //分页
  const onChange = (page, pageSize) => {
    dispatch(getUsersThunkAction(page, pageSize));
  };
  //axios
  const [messageApi, contextHolder] = message.useMessage();
  const confirm = (openid, authorize) => {
    updateAuthorize(openid, authorize === 1 ? 0 : 1).then((res) => {
      if (res > 0) {
        messageApi.open({
          type: "success",
          content: "修改成功",
        });
        dispatch(getUsersThunkAction(1, 10));
      } else {
        messageApi.open({
          type: "error",
          content: "修改失败",
        });
      }
    });
  };
  //antd
  const columns = [
    {
      title: "昵称",
      dataIndex: "nickname",
      key: "nickname",
    },

    {
      title: "权限",
      dataIndex: "authorize",
      key: "authorize",
      // eslint-disable-next-line jsx-a11y/anchor-is-valid
      render: (text) => (
        <div>
          {text === 1 ? (
            <Tag color="success">允许</Tag>
          ) : (
            <Tag color="error">禁止</Tag>
          )}
        </div>
      ),
    },
    {
      title: "头像",
      dataIndex: "avator",
      key: "avator",
      // eslint-disable-next-line jsx-a11y/anchor-is-valid
      render: (text) => <Avatar size={64} src={text} />,
    },
    {
      title: "学校",
      dataIndex: "school",
      key: "school",
    },
    {
      title: "电话",
      dataIndex: "phone",
      key: "phone",
    },
    {
      title: "微信授权openid",
      dataIndex: "openid",
      key: "openid",
    },
    {
      title: "操作",
      dataIndex: "operate",
      key: "operate",
      render: (text, record) => (
        <Space wrap>
          <Popconfirm
            placement="top"
            title="确认进行该操作？"
            description={
              record.authorize === 1 ? "取消用户授权" : "允许用户授权"
            }
            onConfirm={() => confirm(record.openid, record.authorize)}
            okText="Yes"
            cancelText="No"
          >
            <Button
              type="primary"
              danger={record.authorize === 1 ? true : false}
            >
              {record.authorize === 1 ? "禁止" : "允许"}
            </Button>
          </Popconfirm>
          <Button danger>删除</Button>
        </Space>
      ),
    },
  ];

  return (
    <div>
      {contextHolder}
      <Table
        columns={columns}
        rowKey={(record) => record.id}
        pagination={{
          onChange: onChange,
          total: usersinfo.total,
          showSizeChanger: true,
        }}
        dataSource={usersinfo.data}
      />
    </div>
  );
});
