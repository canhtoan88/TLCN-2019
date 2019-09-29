import React, { Component } from "react";
import { Image } from "react-bootstrap";

import "./DropdownUser.css";

export default class DropdownUser extends Component {
    constructor() {
        super();

        this.onClicked = this.onClicked.bind(this);
    }

    onClicked() {
        let x = document.getElementById("dropdown-user-body");
        if (x.style.display === "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }

    render() {
        return (
            <div className="dropdown-user">
                <Image
                    className="dropdown-user-image"
                    src="images/nancy.jpg"
                    width={50}
                    height={50}
                    roundedCircle
                    onClick={this.onClicked}
                />
                <div className="dropdown-user-body" id="dropdown-user-body">
                    <div className="dropdown-user-body-sub"></div>
                    <div className="dropdown-user-body-main">
                        <div className="dropdown-user-body-content">
                            <img
                                className="dropdown-user-body-content-imgage"
                                alt=""
                                src="icons/user.svg"
                            ></img>
                            <span className="dropdown-user-body-content-title">
                                NANCY - KOREA
                            </span>
                        </div>
                        <hr className="dropdown-user-body-content-divide" />
                        <div className="dropdown-user-body-content">
                            <img
                                className="dropdown-user-body-content-imgage"
                                alt=""
                                src="icons/user.svg"
                            ></img>
                            <span className="dropdown-user-body-content-title">
                                Thông tin cá nhân
                            </span>
                        </div>
                        <hr className="dropdown-user-body-content-divide" />
                        <div className="dropdown-user-body-content">
                            <img
                                className="dropdown-user-body-content-imgage"
                                alt=""
                                src="icons/list.svg"
                            ></img>
                            <span className="dropdown-user-body-content-title">
                                Quản lý nhà trọ
                            </span>
                        </div>
                        <hr className="dropdown-user-body-content-divide" />
                        <div className="dropdown-user-body-content">
                            <img
                                className="dropdown-user-body-content-imgage"
                                alt=""
                                src="icons/logout.svg"
                            ></img>
                            <span className="dropdown-user-body-content-title">
                                Thoát
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
