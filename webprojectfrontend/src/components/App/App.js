import React from 'react';
import {Route, BrowserRouter as Router, Switch} from 'react-router-dom';
import CreateBookmark from '../Bookmarks/CreateBookmark';
import Bookmarks from '../Bookmarks/Bookmarks';
import UpdateBookmark from '../Bookmarks/UpdateBookmark';
import '../App/App.css';
import {Provider} from "react-redux";
import store from "../../store";
import "../Layout/Landing";
import Landing from "../Layout/Landing";
import Register from "../UserMenagment/Register";
import Login from "../UserMenagment/Login";
import jwt_decode from "jwt-decode";
import setJWTToken from "../../securityUtils/setJWTToken";
import {SET_CURRENT_USER} from "../../actions/types";
import {logout} from "../../actions/securityActions";
import SecuredRoute from "../../securityUtils/SecuredRoute";
import CreateSport from "../Sport/CreateSport";
import Sports from "../Sport/Sports";
import UpdateSport from "../Sport/UpdateSport";
import CreateLeague from "../League/CreateLeague";
import Leagues from "../League/Leagues";

const jwtToken = localStorage.jwtToken;

if(jwtToken){
    setJWTToken(jwtToken);
    const decoded_jwtToken = jwt_decode(jwtToken);
    store.dispatch({
        type: SET_CURRENT_USER,
        payload: decoded_jwtToken
    });

    const currentTime = Date.now()/1000;
    if(decoded_jwtToken.exp < currentTime){
        //handle logout
        store.dispatch(logout());
        window.location.href = "/";
    }
}

function App() {
  return (
      <Provider store={store}>
          <Router>

              <Route exact path="/" component={Landing}/>
              <Route exact path="/register" component={Register}/>
              <Route exact path="/login" component={Login}/>

              <Switch>
                  <SecuredRoute path='/createBookmark' exact={true} component={CreateBookmark}/>
                  <SecuredRoute path='/bookmarks' exact={true} component={Bookmarks}/>
                  <SecuredRoute path="/updateBookmark/:id" exact={true} component={UpdateBookmark} />

                  <SecuredRoute path='/createSport' exact={true} component={CreateSport}/>
                  <SecuredRoute path='/sports' exact={true} component={Sports}/>
                  <SecuredRoute path="/updateSport/:id" exact={true} component={UpdateSport} />

                  <SecuredRoute path='/createLeague' exact={true} component={CreateLeague}/>
                  <SecuredRoute path='/leagues' exact={true} component={Leagues}/>

              </Switch>

          </Router>
      </Provider>
  );
}

export default App;
