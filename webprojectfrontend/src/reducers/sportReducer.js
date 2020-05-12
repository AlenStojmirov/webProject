import {GET_SPORTS, GET_SPORT, DELETE_SPORT} from "../actions/types";

const initialState = {
    sports: [],
    sport: {}
};

export default function (state = initialState, action) {
    switch (action.type) {
        case GET_SPORTS:
            return{
                ...state,
                sports: action.payload
            };
        case GET_SPORT:
            return {
                ...state,
                sport: action.payload
            };
        case DELETE_SPORT:
            return {
                ...state,
                sports: state.sports.filter(
                    sport => sport.id !== action.payload)
            };
        default:
            return state;
    }
}