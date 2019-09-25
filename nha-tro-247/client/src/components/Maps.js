import React, { Component } from "react";
//import ReactDOM from 'react-dom'
import PropTypes from 'prop-types';
import {Map, /*InfoWindow,*/ Marker, GoogleApiWrapper} from 'google-maps-react';

import './Maps.css'

export class MapContainer extends Component {

    constructor(props) {
        super(props);
        const {lat, lng} = this.props.initialCenter;
        this.state = {
            currentLocation: {
                lat: lat,
                lng: lng
            }
        }
    }

    // getLocation() {
    //     if (navigator.geolocation) {
    //         navigator.geolocation.getCurrentPosition(pos => {
    //             this.setState(prevState => ({
    //                 currentLocation: {
    //                     lat: pos.coords.latitude,
    //                     lng: pos.coords.longitude
    //                 }
    //             }))
    //         });
    //     } else {
    //         alert("Geolocation is not supported by this browser.");
    //     }
    // }

    // UNSAFE_componentWillMount() {
    //     this.setState({
    //         currentLocation: {
    //             lat: 10.772967,
    //             lng: 106.698077
    //         }
    //     })
    //     //this.getLocation();
    // }

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
        //this.map.fitBounds(this.bounds);
    }

    recenterMap() {
        const map = this.map;
        const curr = this.state.currentLocation;

        const google = this.props.google;
        const maps = google.maps;

        if (map) {
            let center = new maps.LatLng(curr.lat, curr.lng)
            console.log(map);
            map.panTo(center);

            // FitBounds --> góc nhìn hẹp, chỉ khu vực gần kề ở vị trí muốn đặt
            this.bounds = new maps.LatLngBounds();
            this.bounds.extend(curr);

            // Set Marker after get client's current location
            this.marker = new maps.Marker({
                map: map,
                position: center
            })
        }
    }

    loadMap() {
        if (this.props && this.props.google) {
            // google is available
            const {google} = this.props;
            const maps = google.maps;


            let zoom = 14;
            let lat = this.state.currentLocation.lat;
            let lng = this.state.currentLocation.lng;
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
            <div className="loadingMaps">Đang tải bản đồ!</div>
            // <div className="map">
            //     <Map
            //         google={this.props.google}
            //         zoom={14}
            //         initialCenter={this.state.currentLocation}>
            //         <Marker
            //             onClick={this.onMarkerClick}
            //             name={'Current location'}/>
            //     </Map>
            // </div>
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
