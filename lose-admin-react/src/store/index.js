import {applyMiddleware, createStore,compose} from 'redux'
import thunk from 'redux-thunk'//中间件，增强
import reducer from './reducer'//合并reducer

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

const store = createStore(reducer,composeEnhancers(
  applyMiddleware(thunk)
))

export default store
