import {combineReducers} from "redux";
import errorReducer from "./errorReducer";
import bookmarkReducer from "./bookmarkReducer";

export default combineReducers({
    errors: errorReducer,
    bookmark: bookmarkReducer

});