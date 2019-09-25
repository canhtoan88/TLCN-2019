import React, { Component } from "react";
//import ReactDOM from 'react-dom'
import PropTypes from 'prop-types';
import {Map, /*InfoWindow,*/ Marker, GoogleApiWrapper} from 'google-maps-react';

import './Maps.css'

// function getLocation() {
//     if (navigator.geolocation) {
//         navigator.geolocation.getCurrentPosition(pos => {
//             currentPosition.lat = pos.coords.latitude,
//             currentPosition.lng = pos.coords.longitude
//         });
//     } else {
//         alert("Geolocation is not supported by this browser.");
//     }
// };

export class MapContainer extends Component {

    constructor(props) {
        super(props);
        const {lat, lng} = this.props.initialCenter;
        this.state = {
            currentPosition: {
                lat: lat,
                lng: lng
            }
        }
        this.getLocation = this.getLocation.bind(this);
    }

    getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(pos => {
                this.setState(prevState => ({
                    currentPosition: {
                        lat: pos.coords.latitude,
                        lng: pos.coords.longitude
                    }
                }))
            });
        } else {
            alert("Geolocation is not supported by this browser.");
        }
    }

    UNSAFE_componentWillMount() {
        this.setState({
            currentPosition: {
                lat: 10.772967,
                lng: 106.698077
            }
        })
        //this.getLocation();
    }

    componentDidMount() {
        if (this.props.centerAroundCurrentLocation) {
            if (navigator && navigator.geolocation) {
                navigator.geolocation.getCurrentPosition((pos) => {
                    const coords = pos.coords;
                    this.setState({
                        currentLocation: {
                            lat: coords.latitude,
                            lng: coords.longitude
                        }
                    })
                })
            }
        }
        this.loadMap();
    }

    componentDidUpdate(prevProps, prevState) {
    if (prevProps.google !== this.props.google) {
            this.loadMap();
        }
        if (prevState.currentLocation !== this.state.currentLocation) {
            this.recenterMap();
        }
    }

    recenterMap() {
        const map = this.map;
        const curr = this.state.currentLocation;

        const google = this.props.google;
        const maps = google.maps;

        if (map) {
            let center = new maps.LatLng(curr.lat, curr.lng)
            map.panTo(center)
        }
    }

    loadMap() {
        if (this.props && this.props.google) {
            // google is available
            const {google} = this.props;
            const maps = google.maps;

            let zoom = 14;
            let lat = this.state.currentPosition.lat;
            let lng = this.state.currentPosition.lng;
            const center = new maps.LatLng(lat, lng);
            const mapConfig = Object.assign({}, {
                center: center,
                zoom: zoom
            })
            this.map = new maps.Map(document.querySelector('.App-body-right'), mapConfig);
        }
    }

    render() {
        return (
            <div className="map">
                <Map
                    google={this.props.google}
                    zoom={14}
                    initialCenter={this.state.currentPosition}>
                    <Marker
                        onClick={this.onMarkerClick}
                        name={'Current location'}/>
                </Map>
            </div>
    );
  }
}

MapContainer.propTypes = {
    google: PropTypes.object,
    zoom: PropTypes.number,
    initialCenter: PropTypes.object,
    centerAroundCurrentLocation: PropTypes.bool
}
MapContainer.defaultProps = {
    zoom: 13,
    // Ben Thanh market, by default
    initialCenter: {
    lat: 10.772967,
    lng: 106.698077
    },
    centerAroundCurrentLocation: true
}

const LoadingContainer = (props) => (
  <div className="loadingMaps">Đang tải bản đồ!</div>
)

export default GoogleApiWrapper({
    apiKey: ('AIzaSyD9mzilMG4xBbeqNYQpBzLysB2YMxWVNfs'),
    language: 'vi-vn',
    LoadingContainer: LoadingContainer
})(MapContainer)
