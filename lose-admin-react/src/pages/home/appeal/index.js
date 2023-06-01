import React, { memo, useEffect, useState } from "react";

import {getComplain} from '@/services/appeal'
import { Table, Avatar, Button, Space, Tag, Popconfirm, message } from "antd";
export default memo(function App() {

  const [appeal,setAppeal] =useState({})
  const [page,SetPage]=useState(1)
  const [pagesize,SetPageSize] =useState(10)
  const [messageApi, contextHolder] = message.useMessage();
    //分页
    const onChange = (page, pageSize) => {
      getComplain(page,pageSize).then(res=>{
        console.log(res)
        setAppeal(res)
        SetPage(page)
        SetPageSize(pageSize)
      })
    };
  useEffect(()=>{
    console.log(page,pagesize)
    getComplain(page,pagesize).then(res=>{
      console.log(res)
      setAppeal(res)
    })
  },[])
  const columns = [
    {
      title: "申请人昵称",
      dataIndex: "nickname",
      key: "nickname",
      render: (text,record) => <div>{record.users.nickname}</div>,
    },
    {
      title: "头像",
      dataIndex: "avator",
      key: "avator",
      render: (text,record) => <Avatar size={64} src={record.users.avator} />,
    },
    {
      title: "申诉人联系方式",
      dataIndex: "phone",
      key: "phone",
      render: (text,record) => <div>{record.users.phone}</div>,
    },
    {
      title: "申诉人学校",
      dataIndex: "school",
      key: "school",
      render: (text,record) => <div>{record.users.school}</div>,
    },
    {
      title: "申诉原因",
      dataIndex: "detail",
      key: "detail",
      render: (text,record) => <div>{record.complain.detail}</div>,
    },
    {
      title: "信息id",
      dataIndex: "id",
      key: "id",
      render: (text,record) => <div>{record.publish?.id}</div>,
    },
    {
      title: "信息标题",
      dataIndex: "title",
      key: "title",
      render: (text,record) => <div>{record.publish?.title}</div>,
    },
    {
      title: "信息发布时间",
      dataIndex: "time",
      key: "time",
      render: (text,record) => <div>{record.publish?.time}</div>,
    },
    {
      title: "信息发布人",
      dataIndex: "infouser",
      key: "infouser",
      render: (text,record) => <div>{record.publish?.users.nickname}</div>,
    },]

  return (
         <div>
      {contextHolder}
      <Table
        columns={columns}
        rowKey={(record) => record.id}
        pagination={{
          onChange: onChange,
          total: appeal.total,
          showSizeChanger: true,
        }}
        dataSource={appeal.data}
      />
    </div>
  );
});