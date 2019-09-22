import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";

import TopMenu from "./components/TopMenu.component";
import Home from "./components/Home.component";
import User from "./components/Users.component";

const HomeComp = () => <Home />;
const UsersComp = () => <User />;

function App() {
    return (
        <Router>
            <div className="App">
                <TopMenu />

                <Route path="/" exact component={HomeComp} />
                <Route path="/users" component={UsersComp} />
            </div>
        </Router>
    );
}

export default App;
