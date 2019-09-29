import React, { Component } from "react";

import "./Field_Results_Item.css";

export default class Fields_Result_Item extends Component {
    render() {
        return (
            <div className="field-results-item">
                <div className="field-results-item-img">
                    <img src="images/nancy.jpg" alt="" />
                </div>
                <div className="field-results-item-desc">
                    <div className="field-results-item-desc-title">
                        Cho thuê phòng trọ cao cấp, HIỆN ĐẠI, ĐẦY ĐỦ TIỆN NGHI.
                        MẶT TIỀN ĐƯỜNG D2 NGUYỄN GIA TRÍ NỐI DÀI
                    </div>
                    <div>
                        <span className="field-results-item-desc-price">
                            Giá: 1,4 tr/tháng
                        </span>
                        <span className="field-results-item-desc-time-up">
                            16/09/2019
                        </span>
                    </div>
                    <div className="field-results-item-desc-sub-desc">
                        Cho thuê Phòng trọ CAO CẤP HIỆN ĐẠI ĐẦY ĐỦ TIỆN NGHI có
                        dịch vụ vệ sinh. Phòng rộng ở được 6 - 7 người chỉ t
                    </div>
                    <div>
                        <span className="field-results-item-desc-rate">
                            <img alt="" src="icons/star_liked.svg"></img>
                            <img alt="" src="icons/star_liked.svg"></img>
                            <img alt="" src="icons/star_liked.svg"></img>
                            <img alt="" src="icons/star_liked.svg"></img>
                            <img alt="" src="icons/star_not_liked.svg"></img>
                        </span>
                        <span className="field-results-item-desc-contact">
                            <img alt="" src="icons/phone.svg"></img>
                            <span>01696696969</span>
                        </span>
                    </div>
                </div>
                <hr className="field-results-item-hr" />
            </div>
        );
    }
}
