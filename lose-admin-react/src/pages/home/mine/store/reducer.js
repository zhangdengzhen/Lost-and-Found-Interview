import * as actionType from "./constants";
import { Map } from "immutable";
const defaultState = Map({
  mine:{}
});

function reducer(state = defaultState, action) {
  switch (action.type) {
    case actionType.MINE:
      return state.set("mine",action.mine)
    default:
      return state;
  }
}
export default reducer;
