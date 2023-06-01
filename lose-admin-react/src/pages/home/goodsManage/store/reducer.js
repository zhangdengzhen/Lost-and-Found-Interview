import * as actionType from "./constants";
import { Map } from "immutable";
const defaultState = Map({
  publish:{}
});

function reducer(state = defaultState, action) {
  switch (action.type) {
    case actionType.MYPUBLISH:
      return state.set("publish",action.publish)
    default:
      return state;
  }
}
export default reducer;
