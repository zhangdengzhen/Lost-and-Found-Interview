// import { combineReducers } from "redux";
import { combineReducers } from "redux-immutable";
import { reducer as userReducer } from '@/pages/home/userManage/store';
import { reducer as categoryReducer } from '@/pages/home/category/store';
import {reducer as loginReducer} from '@/pages/login/store'
import { reducer as mineReducer } from '@/pages/home/mine/store';
import { reducer as goodsReducer } from '@/pages/home/goodsManage/store';
// 合并reducer
const cReducer = combineReducers({
  users: userReducer,
  login:loginReducer,
  category:categoryReducer,
  mine:mineReducer,
  goods:goodsReducer
});

export default cReducer;
