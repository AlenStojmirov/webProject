import {combineReducers} from "redux";
import errorReducer from "./errorReducer";
import bookmarkReducer from "./bookmarkReducer";
import sportReducer from "./sportReducer";
import leagueReducer from "./leagueReducer";
import securityReducer from "./securityReducer";

export default combineReducers({
    errors: errorReducer,
    bookmark: bookmarkReducer,
    sport: sportReducer,
    league: leagueReducer,
    security: securityReducer
});