import React from 'react';
import './App.css';
import Page from "./components/Page";
import Userable from "./components/Userable";
import { Provider } from 'react-redux';
import { store } from './components/Store';
import TalkingRoom  from './components/TalkingRoom';
import NotificationModal from './extra/NotificationModal';


import {
  BrowserRouter as Router,
  Route,
  Routes
} from "react-router-dom";
import Roullette from "./components/Roullette";

function App() {
    const sessionChattD = sessionStorage.getItem("sessionChatId");
    const isModalOpen = sessionChattD !== "undefined" && sessionChattD !== "0";

    return (
      <Provider store={store}>
        <div>
          <NotificationModal isOpen={!isModalOpen} />
        </div>
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
