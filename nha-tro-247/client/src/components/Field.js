import React, { Component } from "react";
import { Form, Row, Col, DropdownButton, Dropdown } from "react-bootstrap";

import "./Field.css";
import Field_Results_Item from "./Field_Results_Item";
import Footer from "./Footer";
import { onPlaceAutocomplete, onSearchAddress } from './Maps'

const prices = [
    { min: 0, max: 2000000 },
    { min: 2000000, max: 3000000 },
    { min: 3000000, max: 4000000 },
    { min: 4000000, max: 6000000 },
    { min: 6000000, max: 1000000 },
    { min: 10000000, max: 999000000 }
];
const spaces = [
    { min: 0, max: 20 },
    { min: 20, max: 30 },
    { min: 30, max: 40 },
    { min: 40, max: 60 },
    { min: 60, max: 100 },
    { min: 100, max: 999 }
];
const distances = [2, 5, 10, 15, 20, 30];


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
    constructor() {
        super();
        this.state = {
            cities: [],
            districts: [],
            searchFilter: "",
            cityFilter: {},
            districtFilter: {},
            priceFilter: {},
            spaceFilter: {},
            distanceFilter: 0
        };

        this.onSearchKeyUp = this.onSearchKeyUp.bind(this);
        this.onCitySelectChange = this.onCitySelectChange.bind(this);
        this.onDistrictSelectChange = this.onDistrictSelectChange.bind(this);
        this.onPriceSelectChange = this.onPriceSelectChange.bind(this);
        this.onSpaceSelectChange = this.onSpaceSelectChange.bind(this);
        this.onDistanceSelectChange = this.onDistanceSelectChange.bind(this);
    }

    componentDidMount() {
        // Get cities
        fetch("/api/cities")
            .then(res => res.json())
            .then(cities => this.setState({ 
                cities: cities 
            }));
    }

    componentDidUpdate() {
        const filterValue = {
            searchFilter: this.state.searchFilter,
            cityFilter: this.state.cityFilter,
            districtFilter: this.state.districtFilter,
            priceFilter: this.state.priceFilter,
            spaceFilter: this.state.spaceFilter,
            distanceFilter: this.state.distanceFilter
        }
        console.log(filterValue);
    }

    onSearchKeyUp(e) {
        if (e.keyCode === 13) {
            this.setState({
                searchFilter: e.target.value.trim(),
            });
        }
    }

    onCitySelectChange(e) {
        if (e.target.value) {

            // Get city by id
            fetch("/api/cities/" + e.target.value)
                .then(res => res.json())
                .then(city => {
                    this.setState({
                        cityFilter: city,
                        districtFilter: {}
                    });

                    // Get districts by city
                    fetch("/api/districts/cities/" + city._id)
                        .then(res => res.json())
                        .then(districts => {
                            this.setState({
                                districts: districts
                            });

                            // Refresh district select
                            document.getElementById(
                                "districts-select"
                            ).selectedIndex = 0;
                        });
                });
        }
    }

    onDistrictSelectChange(e) {
        if (e.target.value) {

            // Get district by id
            fetch("/api/districts/" + e.target.value)
                .then(res => res.json())
                .then(district => {
                    this.setState({
                        districtFilter: district
                    });
                });
        }
    }

    onPriceSelectChange(e) {
        this.setState({
            priceFilter: prices[e.target.value]
        })
    }

    onSpaceSelectChange(e) {
        this.setState({
            spaceFilter: spaces[e.target.value]
        })
    }

    onDistanceSelectChange(e) {
        this.setState({
            distanceFilter: distances[e.target.value]
        })
    }

    render() {
        return (
            <div className="field scroll square scrollbar-dusty-grass square thin">
                <div className="field-filter">
                    <Row>
                        <Col>
                            <Form.Group className="field-filter-form-group-search">
                                <img alt="" src="icons/search.svg"></img>
                                <div>
                                    <Form.Control
                                        type="text"
                                        placeholder="Tìm kiếm.."
                                        className="field-filter-form-input-search"
                                        onKeyUp={this.onSearchKeyUp}
                                    />
                                </div>
                            </Form.Group>
                        </Col>
                    </Row>
                    <Row>
                        <Col sm={6}>
                            <Form.Group>
                                <Form.Label className="field-filter-form-label">
                                    Tỉnh/Thành phố
                                </Form.Label>
                                <Form.Control
                                    as="select"
                                    onChange={this.onCitySelectChange}
                                >
                                    <option value="null">Tỉnh/Thành phố</option>
                                    {this.state.cities.map(city => (
                                        <option key={city._id} value={city._id}>
                                            {city.name}
                                        </option>
                                    ))}
                                </Form.Control>
                            </Form.Group>
                        </Col>
                        <Col sm={6}>
                            <Form.Group>
                                <Form.Label className="field-filter-form-label">
                                    Quận/Huyện
                                </Form.Label>
                                <Form.Control
                                    as="select"
                                    id="districts-select"
                                    onChange={this.onDistrictSelectChange}
                                >
                                    <option value="null">Quận/Huyện</option>
                                    {this.state.districts.length > 0 && 
                                        this.state.districts.map(district => (
                                            <option
                                                key={district._id}
                                                value={district._id}
                                            >
                                                {district.name}
                                            </option>
                                    ))}
                                </Form.Control>
                            </Form.Group>
                        </Col>
                    </Row>
                    <Row>
                        <Col sm={4}>
                            <label className="field-filter-form-label">
                                Giá thuê
                            </label>
                        </Col>
                        <Col sm={8}>
                            <Form.Group>
                                <Form.Control 
                                    as="select" 
                                    onChange={this.onPriceSelectChange}
                                >
                                    <option value={-1}>Tất cả</option>
                                    <option value={0}>&lt; 2 tr</option>
                                    <option value={1}>2 - 3 tr</option>
                                    <option value={2}>3 - 4 tr</option>
                                    <option value={3}>4 - 6 tr</option>
                                    <option value={4}>6 - 10 tr</option>
                                    <option value={5}>&gt; 10 tr</option>
                                </Form.Control>
                            </Form.Group>
                        </Col>
                    </Row>
                    <Row>
                        <Col sm={4}>
                            <label className="field-filter-form-label">
                                Diện tích
                            </label>
                        </Col>
                        <Col sm={8}>
                            <Form.Group>
                                <Form.Control 
                                    as="select"
                                    onChange={this.onSpaceSelectChange}
                                >
                                    <option value={-1}>Tất cả</option>
                                    <option value={0}>&lt; 20 m2</option>
                                    <option value={1}>20 - 30 m2</option>
                                    <option value={2}>30 - 40 m2</option>
                                    <option value={3}>40 - 60 m2</option>
                                    <option value={4}>60 - 100 m2</option>
                                    <option value={5}>&gt; 100 m2</option>
                                </Form.Control>
                            </Form.Group>
                        </Col>
                    </Row>
                    <Row>
                        <Col sm={4}>
                            <label className="field-filter-form-label">
                                Bán kính
                            </label>
                        </Col>
                        <Col sm={8}>
                            <Form.Group>
                                <Form.Control 
                                    as="select"
                                    onChange={this.onDistanceSelectChange}
                                >
                                    <option value={-1}>Tất cả</option>
                                    <option value={0}>2 km</option>
                                    <option value={1}>5 km</option>
                                    <option value={2}>10 km</option>
                                    <option value={3}>15 km</option>
                                    <option value={4}>20 km</option>
                                    <option value={5}>30 km</option>
                                </Form.Control>
                            </Form.Group>
                        </Col>
                    </Row>
                </div>
                <hr className="field-hr" />
                <div className="field-results">
                    <Row>
                        <Col className="field-results-title">
                            <span>Kết quả</span>
                        </Col>
                        <Col className="field-results-filter">
                            <DropdownButton
                                alignRight
                                title="Mới nhất"
                                variant="success"
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
                    <hr className="field-hr" />
                    <div className="field-results-list">
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
                    </div>
                </div>
                <hr className="field-hr" />
                <Footer />
            </div>
        );
    }
}
