import axios from "axios";
import {GET_ERRORS, GET_BOOKMARKS, GET_BOOKMARK, DELETE_BOOKMARK} from "./types";

export const createBookmark = (bookmark,history) => async dispatch =>{
    try {
        await axios.post("/bookmarks/create",bookmark);
        history.push("/bookmarks");

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

export const getBookmarks = () => async dispatch =>{
    const res = await axios.get("/bookmarks");
    dispatch({
        type: GET_BOOKMARKS,
        payload: res.data
    });
};

export const getBookmark = (id,history) => async dispatch =>{
    try{
        const res = await axios.get(`/bookmarks/${id}`);
        dispatch({
            type: GET_BOOKMARK,
            payload: res.data
        });
    }catch (error) {
        history.push("/bookmarks");
    }
};

export const deleteBookmark = id => async dispatch =>{
    await axios.delete(`/bookmarks/${id}`);
    dispatch({
        type: DELETE_BOOKMARK,
        payload: id
    })
};