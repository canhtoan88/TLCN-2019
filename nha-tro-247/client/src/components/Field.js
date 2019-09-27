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

import { onPlaceAutocomplete, onSearchAddress } from './Maps'

class SearchBar extends Component {
    constructor(props) {
        super(props);
        this.state = {
            city: '',
            query: ''
        }
        this.autocompleteInput = React.createRef();
        this.onChangeSearch = this.onChangeSearch.bind(this);
    }

    onChangeSearch(e) {
        //onChangeSearchAddress(e.target.value);
        if (e.which === 13 || e.which === 10) {
            onSearchAddress(e.target.value, formattedAddress => {
                // Set full address in search input
                this.autocompleteInput.current.value = formattedAddress;
            });
        } else {
            //this.handleScriptLoad();
            //onPlaceAutocomplete(this.autocompleteInput.current)
        }
    }

    componentDidMount() {
        setTimeout(() => {
            onPlaceAutocomplete(this.autocompleteInput.current, (addressObject) => {
                //this.props.onPlaceLoaded(addressObject);
                const address = addressObject.address_components;
                if (address){
                    this.setState({
                        city: address[0].long_name,
                        query: addressObject.formatted_address
                    })
                }
            })
        }, 2000);

    }

    render() {
        return (
            <Form.Control
                ref={this.autocompleteInput}
                id="autocomplete"
                onKeyPress={this.onChangeSearch}
                type="text"
                placeholder="Tìm kiếm.."
                className="input-search"
                defaultValue={this.state.query}
                />
        )
    }
}

export default class Fields extends Component {
    render() {
        return (
            <div className="field scroll square scrollbar-dusty-grass square thin">
                <div className="field-filter">
                    <Form>
                        <Container>
                            <Row>
                                <Col>
                                    <Form.Group className="form-group-custom">
                                        <SearchBar/>
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
                                            <option>&lt; 20 m&#178;</option>
                                            <option>20 - 30 m&sup2;</option>
                                            <option>30 - 40 m&sup2;</option>
                                            <option>40 - 60 m&sup2;</option>
                                            <option>60 - 100 m&sup2;</option>
                                            <option>&gt; 100 m&sup2;</option>
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
                                            <option>&lt; 20 m</option>
                                            <option>20 - 30 m</option>
                                            <option>30 - 40 m</option>
                                            <option>40 - 60 m</option>
                                            <option>60 - 100 m</option>
                                            <option>&gt; 100 m</option>
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
