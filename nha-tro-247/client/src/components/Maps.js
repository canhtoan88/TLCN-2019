import React, { Component } from "react";
import {Map, InfoWindow, Marker, GoogleApiWrapper} from 'google-maps-react';

//import './Maps.css'

export class MapContainer extends Component {
    render() {
        const style = {
            width: '70%',
            height: '100%'
        }
        return (
            <div className="map">
                <Map
                    google={this.props.google}
                    zoom={14}
                    style={style}>
                </Map>
            </div>
    );
  }
}

export default GoogleApiWrapper({
  apiKey: ('AIzaSyD9mzilMG4xBbeqNYQpBzLysB2YMxWVNfs')
})(MapContainer)
