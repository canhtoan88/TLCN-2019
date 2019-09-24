import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";

import "./App.css";

import TopMenu from "./components/TopMenu";
import Home from "./components/Home";
import User from "./components/Users";
import Map from "./components/Maps";

const HomeComp = () => <Home />;
const UsersComp = () => <User />;

function App() {
    return (
        <div className="App">
            <div className="App-body-left">body left</div>
            <div className="App-body-right"><Map/></div>
        </div>
    );
}

export default App;
