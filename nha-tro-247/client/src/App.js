import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.min.css";

import "./App.css";
import Maps from "./components/Maps";
import Field from "./components/Field";

export default class App extends Component {
    render() {
        return (
            <div className="app">
                <div className="app-body-left">
                    <Field />
                </div>
                <div className="app-body-right">
                    <Maps />
                </div>
            </div>
        );
    }
}
