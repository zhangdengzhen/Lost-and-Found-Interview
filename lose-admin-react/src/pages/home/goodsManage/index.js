import React, { memo, useEffect, useState } from "react";

//action
import { getPublishThunkAction } from "./store/actionCreator";
// axios
import { getall } from "@/services/category";
import { deletePublishDb } from "@/services/goods";
//redux
import { useDispatch, useSelector, shallowEqual } from "react-redux";
import { Avatar, Table, Image, Tag, Button, Popconfirm, message } from "antd";
export default memo(function App() {
  //react-hook
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(10);
  //redux-hook
  const { publish } = useSelector((state) => {
    return {
      publish: state.getIn(["goods", "publish"]),
    };
  }, shallowEqual); //浅层比较
  const dispatch = useDispatch();

  //react-hock
  useEffect(() => {
    dispatch(getPublishThunkAction(page, pageSize));
  }, [dispatch]);

  const [category, setCategory] = useState([]);
  useEffect(() => {
    getall().then((res) => {
      let list = [];
      res.map((item) => {
        list.push({ text: item.name, value: item.name });
      });
      setCategory(list);
      console.log(res);
    });
  }, []);
  //antd
  const [messageApi, contextHolder] = message.useMessage();
  //分页
  const onChange = (page, pageSize) => {
    dispatch(getPublishThunkAction(page, pageSize));
    console.log("页面变化", page, pageSize);
    setPage(page);
    setPageSize(pageSize);
  };
  const columns = [
    {
      title: "ID",
      dataIndex: "id",
      key: "id",
      render: (text) => <div>{text}</div>,
    },
    {
      title: "标题",
      dataIndex: "title",
      key: "title",
      render: (text) => <div>{text}</div>,
    },

    {
      title: "物品类别",
      dataIndex: "category",
      key: "category",
      filters: category,
      onFilter: (value, record) => record.category.name === value,
      filterSearch: true,
      render: (text, record) => <div>{record.category.name}</div>,
    },
    {
      title: "信息分类",
      dataIndex: "type",
      key: "type",
      filters: [
        {
          text: "失物招领",
          value: 1,
        },
        {
          text: "寻物启事",
          value: 0,
        },
      ],
      onFilter: (value, record) => record.type === value,
      filterSearch: true,
      render: (text) => (
        <Tag color={text === 1 ? "success" : "warning"}>
          {text === 1 ? "失物招领" : "寻物启事"}
        </Tag>
      ),
    },
    {
      title: "发布人",
      dataIndex: "nickname",
      key: "nickname",
      render: (text, record) => <div>{record.users.nickname}</div>,
    },
    {
      title: "时间",
      dataIndex: "time",
      key: "time",
      render: (text) => <div>{text}</div>,
    },
    {
      title: "头像",
      dataIndex: "avator",
      key: "avator",
      render: (text, record) => <Avatar src={record.users.avator} />,
    },
    {
      title: "电话",
      dataIndex: "phone",
      key: "phone",
      render: (text, record) => <div>{record.users.phone}</div>,
    },
    {
      title: "deleted",
      dataIndex: "deleted",
      filters: [
        {
          text: "已被删除",
          value: 1,
        },
        {
          text: "未被删除",
          value: 0,
        },
      ],
      onFilter: (value, record) => record.deleted === value,
      filterSearch: true,
      key: "deleted",
      render: (text, record) => (
        <Tag color={text === 1 ? "error" : "success"}>
          {text === 1 ? "已被用户删除" : "未被用户删除"}
        </Tag>
      ),
    },
    {
      title: "操作",
      dataIndex: "delete",
      key: "delete",
      render: (text, record) => (
        <Popconfirm
          placement="top"
          title="确认进行该操作？"
          description={"彻底删除"}
          onConfirm={() => handleconfirm(record.id)}
          okText="是"
          cancelText="否"
        >
          <Button type="primary" danger>
            删除
          </Button>
        </Popconfirm>
      ),
    },
  ];
  const handleconfirm = (id) => {
    console.log(id);
    deletePublishDb(id).then((res) => {
      console.log(res);
      if (res > 0) {
        messageApi.open({
          type: "success",
          content: "删除成功",
        });
        dispatch(getPublishThunkAction(page, pageSize));
      } else {
        messageApi.open({
          type: "error",
          content: "删除失败",
        });
      }
    });
  };
  console.log(publish, "我的发布");
  return (
    <div>
      {contextHolder}
      <Table
        columns={columns}
        pagination={{
          onChange: onChange,
          total: publish.total,
          showSizeChanger: true,
        }}
        dataSource={publish.data}
        rowKey={(record) => record.id}
        expandable={{
          expandedRowRender: (record) => (
            <div>
              <p>详情：{record.detail}</p>
              {record.imgs.map((item, index) => {
                return <Image key={item.url} width={200} src={item.url} />;
              })}
            </div>
          ),
          rowExpandable: (record) => record.name !== "Not Expandable",
        }}
      />
    </div>
  );
});
