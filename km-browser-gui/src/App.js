import React from 'react';
import './App.css';
import Page from "./components/Page";
import Userable from "./components/Userable";
import { Provider } from 'react-redux';
import { store } from './components/Store';
import TalkingRoom  from './components/TalkingRoom';


import {
  BrowserRouter as Router,
  Route,
  Routes
} from "react-router-dom";
import Roullette from "./components/Roullette";

function App() {
  return (
      <Provider store={store}>
      <Router>
        <Routes>
          <Route path="/" element={<Page/>}/>
          <Route path="/user" element={<Userable/>}/>
            <Route path="/roulette" element={<Roullette/>}/>
            <Route path="/talking-room" element={<TalkingRoom/>}/>
        </Routes>
      </Router>
      </Provider>
  );
}

export default App;
