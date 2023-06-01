import "./style.js";
import React from "react";
import store from "./store";
import { Provider } from "react-redux";
import { RouterProvider } from "react-router-dom";
import router from "@/routes";
import { GlobalStyle } from "./style";
function App() {
  return (
    <Provider store={store}>
      <div className="App" style={{ width: "100%", height: "100%" }}>
        <RouterProvider router={router} />
        <GlobalStyle />
      </div>
    </Provider>
  );
}

export default App;
