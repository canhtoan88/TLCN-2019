import React, { Component } from 'react'
import { Map, InfoWindow, Marker, GoogleApiWrapper } from "google-maps-react";

export class Maps extends Component {
    render() {
        return (
            <div className="maps">
                <Map google={this.props.google} zoom={15}></Map>
            </div>
        )
    }
}

export default GoogleApiWrapper({
    apiKey: "AIzaSyD9mzilMG4xBbeqNYQpBzLysB2YMxWVNfs"
})(Maps);