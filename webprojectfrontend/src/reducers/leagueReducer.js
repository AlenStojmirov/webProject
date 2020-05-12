import {GET_LEAGUES, GET_LEAGUE, DELETE_LEAGUE} from "../actions/types";

const initialState = {
    leagues: [],
    league: {}
};

export default function (state = initialState, action) {
    switch (action.type) {
        case GET_LEAGUES:
            return{
                ...state,
                leagues: action.payload
            };
        case GET_LEAGUE:
            return {
                ...state,
                league: action.payload
            };
        case DELETE_LEAGUE:
            return {
                ...state,
                leagues: state.leagues.filter(
                    league => league.id !== action.payload)
            };
        default:
            return state;
    }
}