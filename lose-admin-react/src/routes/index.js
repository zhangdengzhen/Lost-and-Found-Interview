import React from "react";
import { createBrowserRouter } from "react-router-dom";

import Login from "@/pages/login";
import ErrorPage from "@/pages/error";
import HomePage from "@/pages/home";
import UsersPage from "@/pages/home/userManage";
import GoodsPage from "@/pages/home/goodsManage";
import Appeal from "@/pages/home/appeal";

import Category from "@/pages/home/category";
import Mine from "@/pages/home/mine";
import Problem from '@/pages/home/problem'
const router = createBrowserRouter([
  {
    path: "/",
    element: <Login />,
    errorElement: <ErrorPage />,
  },
  {
    path: "/home",
    element: <HomePage />,
    children: [
      {
        path: "users",
        element: <UsersPage />,
      },
      {
        path: "goods",
        element: <GoodsPage />,
      },
      {
        path: "appeal",
        element: <Appeal />,
      },
      {
        path: "category",
        element: <Category />,
      },
      {
        path: "mine",
        element: <Mine/>,
      },
      {
        path:'problem',
        element:<Problem/>
      }
    ],
  },
]);

export default router;
