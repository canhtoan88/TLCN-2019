import React, { Component } from "react";

import "./Field_Results_Item.css";
import { Container, Button } from "react-bootstrap";

export default class Fields_Result_Item extends Component {
    render() {
        return (
            <div className="field-results-item">
                <div className="item-img">
                    <img
                        src="https://scontent.fsgn4-1.fna.fbcdn.net/v/t1.0-9/61774418_1552052588257986_8218342675909181440_n.jpg?_nc_cat=103&_nc_oc=AQkfUDd0y9fnZaT6uQr76uJJmzqGuZDpVYEsrvvteTXiZKpUhqRfYB9xTA4FaMj-6vg&_nc_ht=scontent.fsgn4-1.fna&oh=e5bee77808080b40622d3645706d7c4f&oe=5E31A6D3"
                        alt=""
                    />
                </div>
                <Container className="item-desc">
                    <div className="item-title">
                        Cho thuê phòng trọ cao cấp, HIỆN ĐẠI, ĐẦY ĐỦ TIỆN NGHI.
                        MẶT TIỀN ĐƯỜNG D2 NGUYỄN GIA TRÍ NỐI DÀI
                    </div>
                    <div>
                        <span className="item-price">Giá: 1,4 tr/tháng</span>
                        <span className="item-time-up">16/09/2019</span>
                    </div>
                    <div className="item-hostel-desc">
                        Cho thuê Phòng trọ CAO CẤP HIỆN ĐẠI ĐẦY ĐỦ TIỆN NGHI có
                        dịch vụ vệ sinh. Phòng rộng ở được 6 - 7 người chỉ t
                    </div>
                    <div>
                        <span className="item-rate">
                            <img alt="" src="./../icons/star_liked.svg"></img>
                            <img alt="" src="./../icons/star_liked.svg"></img>
                            <img alt="" src="./../icons/star_liked.svg"></img>
                            <img alt="" src="./../icons/star_liked.svg"></img>
                            <img
                                alt=""
                                src="./../icons/star_not_liked.svg"
                            ></img>
                        </span>
                        <span className="item-contact">
                            <img alt="" src="./../icons/phone.svg"></img>
                            <span>01696696969</span>
                        </span>
                    </div>
                </Container>
                <hr className="item-clear-both" />
            </div>
        );
    }
}
