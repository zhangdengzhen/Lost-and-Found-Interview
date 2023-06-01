import React, { useState, memo } from "react";
import { Outlet, useNavigate } from "react-router-dom";

//antd
import {
  MenuFoldOutlined,
  MenuUnfoldOutlined,
  EditOutlined,
  UserOutlined,
  GiftOutlined,
  SolutionOutlined,
  GoldOutlined,
  LogoutOutlined,
  QuestionOutlined,
} from "@ant-design/icons";

import { Layout, Menu, theme } from "antd";

import { HomeWrapper } from "./style";

const { Header, Sider, Content } = Layout;
export default memo(function App() {
  const [collapsed, setCollapsed] = useState(false);

  //antd
  const {
    token: { colorBgContainer },
  } = theme.useToken();

  // antd事件
  const navigate = useNavigate();
  const handleMenu = (e) => {
    console.log(e);
    switch (e.key) {
      case "1":
        navigate("/home/users");
        break;
      case "2":
        navigate("/home/goods");
        break;
      case "3":
        navigate("/home/appeal");
        break;
      case "4":
        navigate("/home/category");
        break;
      case "5":
        navigate("/home/mine");
        break;
      case "6":
        navigate("/");
        break;
      case "7":
        navigate("/home/problem");
        break;
      default:
        break;
    }
  };
  return (
    <HomeWrapper>
      <Layout style={{ width: "100%", height: "100%" }}>
        <Sider trigger={null} collapsible collapsed={collapsed}>
          <div className="logo" />
          <Menu
            theme="dark"
            mode="inline"
            defaultSelectedKeys={["1"]}
            onClick={handleMenu}
            items={[
              {
                key: "1",
                icon: <UserOutlined />,
                label: "用户管理",
              },
              {
                key: "2",
                icon: <GiftOutlined />,
                label: "信息管理",
              },
              {
                key: "3",
                icon: <EditOutlined />,
                label: "申诉管理",
              },
              {
                key: "7",
                icon: <QuestionOutlined />,
                label: "问题反馈",
              },
              {
                key: "4",
                icon: <GoldOutlined />,
                label: "类别管理",
              },
              {
                key: "5",
                icon: <SolutionOutlined />,
                label: "个人中心",
              },
              {
                key: "6",
                icon: <LogoutOutlined />,
                label: "退出登录",
              },
            ]}
          />
        </Sider>
        <Layout className="site-layout">
          <Header style={{ padding: 0, background: colorBgContainer }}>
            {React.createElement(
              collapsed ? MenuUnfoldOutlined : MenuFoldOutlined,
              {
                className: "trigger",
                onClick: () => setCollapsed(!collapsed),
              }
            )}
          </Header>
          <Content
            style={{
              margin: "24px 16px",
              padding: 24,
              minHeight: 280,
              background: colorBgContainer,
            }}
          >
            <Outlet />
          </Content>
        </Layout>
      </Layout>
    </HomeWrapper>
  );
});
