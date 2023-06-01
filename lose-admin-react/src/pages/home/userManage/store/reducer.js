import * as actionType from "./constants";
import { Map } from "immutable";
const defaultState = Map({
  usersinfo:[]
});

function reducer(state = defaultState, action) {
  switch (action.type) {
    case actionType.USER_INFO:
      return state.set("usersinfo",action.usersinfo)
    default:
      return state;
  }
}
export default reducer;
