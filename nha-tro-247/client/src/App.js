import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Container, Row, Col } from "react-bootstrap";

import "./App.css";
import Maps from "./components/Maps";
import Field from "./components/Field";

export default class App extends Component {
    render() {
        return (
            <div className="app">
                {/* <Row className="app-row">
                    <Col sm={12} md={4} className="app-body-left">
                        <Field />
                    </Col>
                    <Col sm={12} md={8} className="app-body-right">
                        <Maps />
                    </Col>
                </Row> */}

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
