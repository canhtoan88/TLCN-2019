import React, { Component } from "react";

import "./Logo.css";

export default class Logo extends Component {
    render() {
        return (
            <div className="logo">
                <img
                    className="logo-image"
                    alt="247 hostel"
                    src="icons/logo.svg"
                ></img>
                <span className="logo-title">247 Hostel</span>
            </div>
        );
    }
}
