import React, { Component } from "react";
import PropTypes from 'prop-types';
import {Map, /*InfoWindow,*/ Marker, GoogleApiWrapper} from 'google-maps-react';

import './Maps.css'

let markers = [], currentDirection = null, currentCircle = null;

// No complete yet Autocomplete -- Not display autocomplete
export function onPlaceAutocomplete (input, cb) {
    const google = this.props.google;
    const maps = google.maps;

    if (!google) return;

    this.autocomplete = new maps.places.Autocomplete(input, {
        "types": ["cities"]
    });
    this.autocomplete.bindTo('bounds', this.map);

    //this.autocomplete.setFields(['address_component', 'formatted_address']); //'geometry', 'icon', 'name'

    // After Enter --> When selected a address
    this.autocomplete.addListener('place_changed', () => {
        const addressObject = this.autocomplete.getPlace();
        cb(addressObject)
    })
}

export function onSearchAddress (address, cb) {
    geocoding(address, results => {
        // Get Lat and Lng from entered location
        const { lat, lng } = results[0].geometry.location;

        // Clear current direction if existing
        if (currentDirection) {
            currentDirection.setMap(null)
            currentDirection = null;
        }
        // Test Direction
        this.getRedirectMap(new this.props.google.maps.LatLng(this.state.currentLocation.lat, this.state.currentLocation.lng), results[0].geometry.location);

        // Clear old marker of client's location
        this.marker.setMap(null)
        this.setState({
            currentLocation: {
                lat: lat(),
                lng: lng()
            }
        })

        // Clear current circle if existing
        if (currentCircle) {
            currentCircle.setMap(null);
            currentCircle = null;
        }
        // Test draw a circle on map from center position 
        this.drawCircleFromCenter(this.state.currentLocation, 1000)

        // Show near hostels
        const nearbyHostel = [
            new this.props.google.maps.LatLng(10.8613154, 106.75557779999997),
            new this.props.google.maps.LatLng(10.8513154, 106.76557779999997),
            new this.props.google.maps.LatLng(10.8413154, 106.74557779999997),
            new this.props.google.maps.LatLng(10.8433154, 106.75457779999997)
        ]
        this.showNearHostel(nearbyHostel);

        cb(results[0].formatted_address)
    })
}

// Get Address or geometry (latitude and longitude)
function geocoding(address, cb) {
    const google = this.props.google;
    const maps = google.maps;
    const geocoder = new maps.Geocoder();
    geocoder.geocode({'address': address}, (results, status) => {
        if (status === maps.GeocoderStatus.OK) {
            cb(results)
        } else {
            return alert( 'Geocode was not successful for the following reason: ' + status );
        }
    })
}

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

    UNSAFE_componentWillMount() {
        onPlaceAutocomplete = onPlaceAutocomplete.bind(this);
        onSearchAddress = onSearchAddress.bind(this);
        geocoding = geocoding.bind(this);
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
            //this.loadMap();
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
            map.panTo(center);

            // FitBounds --> góc nhìn hẹp, chỉ khu vực gần kề ở vị trí muốn đặt
            //this.bounds = new maps.LatLngBounds();
            //this.bounds.extend(curr);

            // Set Marker after get client's current location
            setTimeout(() => {
                this.marker = new maps.Marker({
                    map: map,
                    animation: maps.Animation.DROP,
                    position: center
                })
            }, 300)
            // Change marker's animation
            setTimeout(() => {
                this.marker.setAnimation(maps.Animation.BOUNCE);
            }, 5000)
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
            this.map = new maps.Map(document.querySelector('.app-body-right'), mapConfig);
        }
    }

    showNearHostel(nearbyHostel) {
        this.clearNearHostel();

        const {google} = this.props;
        const maps = google.maps;

        // Add some new markers
        for (let i = 0; i < nearbyHostel.length; i++) {
            setTimeout(() => {
                console.log();
                markers.push(new maps.Marker({
                    name: 'Your location!',
                    map: this.map,
                    animation: maps.Animation.DROP,
                    position: nearbyHostel[i],
                    icon: {
                        url: 'logo.svg',
                        anchor: new maps.Point(32,32),
                        scaledSize: new maps.Size(30,32)
                    }
                }))
            }, i*500)
        }
    }

    clearNearHostel() {
        markers.forEach(marker => {
            marker.setMap(null)
        })
        markers = [];
    }

    getRedirectMap(origin, destination) {
        const google = this.props.google;
        const maps = google.maps;

        const directionsService = new maps.DirectionsService();
        const directionsDisplay = new maps.DirectionsRenderer();
        const request = {
            origin: origin,
            destination: destination,
            travelMode: this.props.google.maps.TravelMode.DRIVING
        }


        directionsDisplay.setMap(this.map);
        directionsService.route(request, (results, status) => {
            if (status === 'OK') {
                directionsDisplay.setDirections(results);
                currentDirection = directionsDisplay;

                // Get other values
                const otherValues = results.routes[0].legs[0];
                const time = otherValues.duration.text;
                const total = otherValues.distance.text;
                const from = otherValues.start_address;
                const to = otherValues.end_address;
                console.log(`Đi từ ${from} đến ${to} dài ${total} trong ${time}`);
            }
        })
    }

    drawCircleFromCenter(center, radius) {
        currentCircle = new this.props.google.maps.Circle({
            strokeColor: '#888', // Màu viên
            strokeOpacity: 0.5, // Độ mờ viền
            strokeWeight: 1, // Độ mảnh của đường tròn
            fillColor: '#03A9F4', // Màu nền của đường tròn
            fillOpacity: 0.1, // Độ trong suốt của nền
            map: this.map, // Hiển thị trên map nào
            center: {lat: center.lat, lng: center.lng}, // tạo độ trung tâm
            radius: radius // bán kính
        })
    }

    render() {
        return (
            <div className="map">
                <Map
                    google={this.props.google}
                    zoom={14}
                    initialCenter={this.state.currentLocation}>
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
  <div className="loadingMaps">Đang tải bản đồ...</div>
)

export default GoogleApiWrapper({
    apiKey: ('AIzaSyD9mzilMG4xBbeqNYQpBzLysB2YMxWVNfs'),
    language: 'vi-vn',
    LoadingContainer: LoadingContainer
})(MapContainer)