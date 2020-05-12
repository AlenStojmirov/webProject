import axios from "axios";
import {GET_ERRORS, GET_SPORTS, GET_SPORT, DELETE_SPORT} from "./types";

export const createSport = (sport,history) => async dispatch =>{
    try {
        await axios.post("/sports/create",sport);
        history.push("/sports");

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

export const getSports = () => async dispatch =>{
    const res = await axios.get("/sports");
    dispatch({
        type: GET_SPORTS,
        payload: res.data
    });
};

export const getSport = (id,history) => async dispatch =>{
    try{
        const res = await axios.get(`/sports/${id}`);
        dispatch({
            type: GET_SPORT,
            payload: res.data
        });
    }catch (error) {
        history.push("/sports");
    }
};

export const deleteSport = id => async dispatch =>{
    await axios.delete(`/sports/${id}`);
    dispatch({
        type: DELETE_SPORT,
        payload: id
    })
};