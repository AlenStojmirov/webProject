import React from 'react';
import {Route, BrowserRouter as Router, Switch} from 'react-router-dom';
import CreateBookmark from '../Bookmarks/CreateBookmark';
import Bookmarks from '../Bookmarks/Bookmarks';
import UpdateBookmark from '../Bookmarks/UpdateBookmark';
import '../App/App.css';
import {Provider} from "react-redux";
import store from "../../store";

function App() {
  return (
      <Provider store={store}>
          <Router>
            <Switch>
              <Route path='/createBookmark' exact={true} component={CreateBookmark}/>
              <Route path='/bookmarks' exact={true} component={Bookmarks}/>
              <Route path="/updateBookmark/:id" exact={true} component={UpdateBookmark} />
            </Switch>
          </Router>
      </Provider>
  );
}

export default App;
