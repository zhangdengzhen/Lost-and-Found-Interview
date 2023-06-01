import React, { memo, useEffect, useState } from "react";

//axios
import { getProblem, deletebyId } from "@/services/problem";
import { Table, Image, Popconfirm, Button, message } from "antd";
export default memo(function App() {
  const [problem, setProblem] = useState({});
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(10);
  useEffect(() => {
    getProblem(1, 10).then((res) => {
      setProblem(res);
      console.log(res);
    });
  }, []);

  // antd
  const [messageApi, contextHolder] = message.useMessage();
  //antd
  //分页
  const onChange = (page, pageSize) => {
    getProblem(page, pageSize).then((res) => {
      setProblem(res);
      console.log(res);
    });
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
      title: "用户openid",
      dataIndex: "openid",
      key: "openid",
      render: (text) => <div>{text}</div>,
    },
    {
      title: "联系方式",
      dataIndex: "concat",
      key: "concat",
      render: (text) => <div>{text}</div>,
    },
    {
      title: "时间",
      dataIndex: "time",
      key: "time",
      render: (text) => <div>{text}</div>,
    },
    {
      title: "操作",
      dataIndex: "delete",
      key: "delete",
      render: (text, record) => (
        <Popconfirm
          placement="top"
          title="确认删除该问题反馈"
          description="删除问题反馈"
          onConfirm={() => confirm(record.id)}
          okText="Yes"
          cancelText="No"
        >
          <Button danger type="primary"> 删除</Button>
        </Popconfirm>
      ),
    },
  ];
  const confirm = (id) => {
    console.log("id是",id)
    deletebyId(id).then(res => {
      if (res > 0) {
        messageApi.open({
          type: "success",
          content: "删除成功",
        });
        getProblem(page, pageSize).then((res) => {
          setProblem(res);
          console.log(res);
        });
      } else {
        messageApi.open({
          type: "error",
          content: "删除失败",
        });
      }
    });
  };
  return (
    <div>
      {contextHolder}
      <Table
        columns={columns}
        pagination={{
          onChange: onChange,
          total: problem.total,
          showSizeChanger: true,
        }}
        dataSource={problem.data}
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
