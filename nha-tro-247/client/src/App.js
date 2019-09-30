import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

import "./App.css";
import Logo from "./components/Logo";
import Field from "./components/Field";
import Maps from "./components/Maps";
import DropdownUser from "./components/DropdownUser";

export default class App extends Component {

    componentDidMount() {
        // Refesh page on page resize
        window.onresize = function() {
            this.location.reload();
        };
    }

    render() {
        return (
            <div className="app">
                <div className="app-body-left">
                    <Logo />
                    <Field />
                </div>
                <div className="app-body-right">
                    <Maps />
                </div>
                <DropdownUser />
            </div>
        );
    }
}
