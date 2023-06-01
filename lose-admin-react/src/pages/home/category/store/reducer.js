import * as actionType from "./constants";
import { Map } from "immutable";
const defaultState = Map({
  category: [],
  categorydelete: 0,
  categoryinsert: 0,
});

function reducer(state = defaultState, action) {
  switch (action.type) {
    case actionType.CATEGORY:
      return state.set("category", action.category);
    case actionType.CATEGORYDELETE:
      return state.set("categorydelete", action.categorydelete);
    case actionType.CATEGORYINSERT:
      return state.set("categoryinsert", action.categoryinsert);
    default:
      return state;
  }
}
export default reducer;
