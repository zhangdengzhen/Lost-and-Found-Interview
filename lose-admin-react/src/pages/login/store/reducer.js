
import { Map } from "immutable";
import * as actionType from "./constants";
const defaultState = Map({
 token:""
});

function reducer(state = defaultState, action) {
  switch (action.type) {
    case actionType.LOGIN:
       return state.set("token",action.token)
    default:
      return state;
  }
}
export default reducer;
