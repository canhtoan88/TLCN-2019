import React, { Component } from "react";
import {
    Container,
    Form,
    Row,
    Col,
    DropdownButton,
    Dropdown
} from "react-bootstrap";

import "./Field.css";
import Field_Results_Item from "./Field_Results_Item";

export default class Fields extends Component {
    render() {
        return (
            <div className="field">
                <div className="field-filter">
                    <Form>
                        <Container>
                            <Row>
                                <Col>
                                    <Form.Group className="form-group-custom">
                                        <Form.Control
                                            type="text"
                                            placeholder="Tìm kiếm.."
                                            className="input-search"
                                        />
                                    </Form.Group>
                                </Col>
                            </Row>
                        </Container>
                        <Container>
                            <Row>
                                <Col sm={12} md={6}>
                                    <Form.Group className="form-group-custom">
                                        <Form.Label className="input-label">
                                            Tỉnh/Thành phố
                                        </Form.Label>
                                        <Form.Control as="select">
                                            <option>Tỉnh/Thành phố</option>
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>
                                            <option>5</option>
                                        </Form.Control>
                                    </Form.Group>
                                </Col>
                                <Col sm={12} md={6}>
                                    <Form.Group className="form-group-custom">
                                        <Form.Label className="input-label">
                                            Quận/Huyện
                                        </Form.Label>
                                        <Form.Control as="select">
                                            <option>Quận/Huyện</option>
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>
                                            <option>5</option>
                                        </Form.Control>
                                    </Form.Group>
                                </Col>
                            </Row>
                        </Container>
                        <Container>
                            <Row>
                                <Col sm={12} md={6}>
                                    <Form.Group className="form-group-custom">
                                        <Form.Label className="input-label">
                                            Tên đường
                                        </Form.Label>
                                        <Form.Control as="select">
                                            <option>Tên đường</option>
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>
                                            <option>5</option>
                                        </Form.Control>
                                    </Form.Group>
                                </Col>
                                <Col sm={12} md={6}>
                                    <Form.Group className="form-group-custom">
                                        <Form.Label className="input-label">
                                            Số nhà
                                        </Form.Label>
                                        <Form.Control
                                            type="text"
                                            placeholder="Số nhà.."
                                        />
                                    </Form.Group>
                                </Col>
                            </Row>
                        </Container>
                        <Container>
                            <Row>
                                <Col md={3} xs={4}>
                                    <Form.Label className="input-label input-label2">
                                        Giá thuê
                                    </Form.Label>
                                </Col>
                                <Col md={9} xs={8}>
                                    <Form.Group className="form-group-custom">
                                        <Form.Control as="select">
                                            <option>Tất cả</option>
                                            <option>&lt; 2 tr</option>
                                            <option>2 - 3 tr</option>
                                            <option>3 - 4 tr</option>
                                            <option>4 - 6 tr</option>
                                            <option>6 - 10 tr</option>
                                            <option>&gt; 10 tr</option>
                                        </Form.Control>
                                    </Form.Group>
                                </Col>
                            </Row>
                        </Container>
                        <Container>
                            <Row>
                                <Col md={3} xs={4}>
                                    <Form.Label className="input-label input-label2">
                                        Diện tích
                                    </Form.Label>
                                </Col>
                                <Col md={9} xs={8}>
                                    <Form.Group className="form-group-custom">
                                        <Form.Control as="select">
                                            <option>Tất cả</option>
                                            <option>&lt; 20 m2</option>
                                            <option>20 - 30 m2</option>
                                            <option>30 - 40 m2</option>
                                            <option>40 - 60 m2</option>
                                            <option>60 - 100 m2</option>
                                            <option>&gt; 100 m2</option>
                                        </Form.Control>
                                    </Form.Group>
                                </Col>
                            </Row>
                        </Container>
                        <Container>
                            <Row>
                                <Col md={3} xs={4}>
                                    <Form.Label className="input-label input-label2">
                                        Bán kính
                                    </Form.Label>
                                </Col>
                                <Col md={9} xs={8}>
                                    <Form.Group className="form-group-custom">
                                        <Form.Control as="select">
                                            <option>Tất cả</option>
                                            <option>&lt; 20 m2</option>
                                            <option>20 - 30 m2</option>
                                            <option>30 - 40 m2</option>
                                            <option>40 - 60 m2</option>
                                            <option>60 - 100 m2</option>
                                            <option>&gt; 100 m2</option>
                                        </Form.Control>
                                    </Form.Group>
                                </Col>
                            </Row>
                        </Container>
                    </Form>
                    <hr className="hr-custom" />
                    <div className="field-results">
                        <Container>
                            <Row>
                                <Col className="field-results-title">
                                    <span>Kết quả</span>
                                </Col>
                                <Col className="field-results-filter">
                                    <DropdownButton
                                        alignRight
                                        title="Mới nhất"
                                        variant="success"
                                        className="field-results-filter"
                                    >
                                        <Dropdown.Item eventKey="1">
                                            Mới nhất
                                        </Dropdown.Item>
                                        <Dropdown.Item eventKey="2">
                                            Giá tăng dần
                                        </Dropdown.Item>
                                        <Dropdown.Item eventKey="3">
                                            Giá giảm dần
                                        </Dropdown.Item>
                                        <Dropdown.Item eventKey="2">
                                            Diện tích tăng dần
                                        </Dropdown.Item>
                                        <Dropdown.Item eventKey="3">
                                            Diện tích giảm dần
                                        </Dropdown.Item>
                                        <Dropdown.Item eventKey="2">
                                            Bán kính tăng dần
                                        </Dropdown.Item>
                                        <Dropdown.Item eventKey="3">
                                            Bán kính giảm dần
                                        </Dropdown.Item>
                                    </DropdownButton>
                                </Col>
                            </Row>
                            <hr className="hr-custom" />
                        </Container>
                        <Container>
                            <Field_Results_Item />
                            <Field_Results_Item />
                            <Field_Results_Item />
                            <Field_Results_Item />
                            <Field_Results_Item />
                            <Field_Results_Item />
                            <Field_Results_Item />
                            <Field_Results_Item />
                            <Field_Results_Item />
                            <Field_Results_Item />
                        </Container>
                    </div>
                </div>
            </div>
        );
    }
}
