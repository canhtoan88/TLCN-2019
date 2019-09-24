import React, { Component } from "react";
import { Button, Badge } from "react-bootstrap";

export default class Home extends Component {
    render() {
        return (
            <div className="Home">
                <Button variant="primary">
                    Profile <Badge variant="light">9</Badge>
                    <span className="sr-only">unread messages</span>
                </Button>
            </div>
        );
    }
}
