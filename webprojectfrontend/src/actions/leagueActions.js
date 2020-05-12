import axios from "axios";
import {GET_ERRORS, GET_LEAGUES, GET_LEAGUE, DELETE_LEAGUE} from "./types";

export const createLeague = (league,history) => async dispatch =>{
    try {
        await axios.post("/leagues/create",league);
        history.push("/leagues");

        dispatch({
            type:GET_ERRORS,
            payload:{}
        })
    }catch (err) {
        dispatch({
            type:GET_ERRORS,
            payload:err.response.data
        })
    }
};

export const getLeagues = () => async dispatch =>{
    const res = await axios.get("/leagues");
    dispatch({
        type: GET_LEAGUES,
        payload: res.data
    });
};

export const getLeague = (id,history) => async dispatch =>{
    try{
        const res = await axios.get(`/leagues/${id}`);
        dispatch({
            type: GET_LEAGUE,
            payload: res.data
        });
    }catch (error) {
        history.push("/leagues");
    }
};

export const deleteLeague = id => async dispatch =>{
    await axios.delete(`/leagues/${id}`);
    dispatch({
        type: DELETE_LEAGUE,
        payload: id
    })
};