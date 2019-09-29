import React, { Component } from "react";
import { Row, Col } from "react-bootstrap";

import "./Footer.css";

export default class Footer extends Component {
    render() {
        return (
            <div className="footer">
                <div className="footer-header">
                    <div className="footer-header-title">
                        <img alt="247 hostel" src="icons/logo.svg"></img>
                        <span>247 hostel</span>
                    </div>
                    <div className="footer-header-title-sub">
                        Website thuê và cho thuê phòng trọ uy tín nhất Việt Nam.
                    </div>
                    <div className="footer-header-content">
                        Giúp bạn tìm kiếm, thuê và cho thuê nhà trọ với chất
                        lượng tốt nhất!
                    </div>
                </div>
                <div className="footer-body">
                    <Row>
                        <Col sm={6} md={3}>
                            <div className="footer-body-item">
                                <div className="footer-body-title">
                                    Liên kết
                                </div>
                                <div className="social-network">
                                    <img
                                        className="social-network-icon"
                                        alt=""
                                        src="icons/facebook.svg"
                                    ></img>
                                    <span className="social-network-title">
                                        Facebook
                                    </span>
                                </div>
                                <div className="social-network">
                                    <img
                                        className="social-network-icon"
                                        alt=""
                                        src="icons/google-plus.svg"
                                    ></img>
                                    <span className="social-network-title">
                                        Google+
                                    </span>
                                </div>
                                <div className="social-network">
                                    <img
                                        className="social-network-icon"
                                        alt=""
                                        src="icons/youtube.svg"
                                    ></img>
                                    <span className="social-network-title">
                                        Youtube
                                    </span>
                                </div>
                                <div className="social-network">
                                    <img
                                        className="social-network-icon"
                                        alt=""
                                        src="icons/zalo.svg"
                                    ></img>
                                    <span className="social-network-title">
                                        Zalo
                                    </span>
                                </div>
                            </div>
                        </Col>
                        <Col sm={6} md={3}>
                            <div className="footer-body-item">
                                <div className="footer-body-title">
                                    Hỗ trợ khách hàng
                                </div>
                                <div className="footer-body-content">
                                    Trung tâm trợ giúp
                                </div>
                                <div className="footer-body-content">
                                    An toàn mua bán
                                </div>
                                <div className="footer-body-content">
                                    Quy định cần biết
                                </div>
                                <div className="footer-body-content">
                                    Liên hệ hỗ trợ
                                </div>
                            </div>
                        </Col>
                        <Col sm={6} md={3}>
                            <div className="footer-body-item">
                                <div className="footer-body-title">
                                    Về chúng tôi
                                </div>
                                <div className="footer-body-content">
                                    Giới thiệu
                                </div>
                                <div className="footer-body-content">
                                    Quy định, điều khoản sử dụng
                                </div>
                                <div className="footer-body-content">
                                    Hướng dẫn
                                </div>
                                <div className="footer-body-content">
                                    Liên hệ
                                </div>
                            </div>
                        </Col>
                        <Col sm={6} md={3}>
                            <div className="footer-body-item">
                                <div className="footer-body-title">
                                    Thanh toán
                                </div>
                            </div>
                        </Col>
                    </Row>
                </div>
            </div>
        );
    }
}
