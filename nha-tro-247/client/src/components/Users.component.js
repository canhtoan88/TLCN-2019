import React, { Component } from "react";
import { Container, Table, Form, Button, Row, Col } from "react-bootstrap";

export default class Users extends Component {
    constructor() {
        super();
        this.state = {
            users: [],
            radioOption: false
        };

        this.onRadioOptionChange = this.onRadioOptionChange.bind(this);
        this.onAddButtonClick = this.onAddButtonClick.bind(this);
        this.onUpdateButtonClick = this.onUpdateButtonClick.bind(this);
        this.onSubmitClick = this.onSubmitClick.bind(this);
        this.onDeleteButtonClick = this.onDeleteButtonClick.bind(this);
    }

    componentDidMount() {
        fetch("/api/users")
            .then(res => res.json())
            .then(users => this.setState({ users: users }));
    }

    onRadioOptionChange(event) {
        this.setState({
            radioOption: event.target.value.localeCompare("true") ? true : false
        });
        console.log(this.state.radioOption);
    }

    onAddButtonClick() {
        let idInput = document.getElementById("_id");
        let fullnameInput = document.getElementById("fullname");
        let ageInput = document.getElementById("age");
        let radioFemale = document.getElementById("female");
        let submitButton = document.getElementById("submit");
        idInput.value = "";
        fullnameInput.value = "";
        ageInput.value = 18;
        radioFemale.checked = true;
        submitButton.value = 0;

        this.setState({
            radioOption: false
        });

        fullnameInput.focus();
    }

    onUpdateButtonClick(event) {
        let id = event.target.value;

        fetch("/api/users/" + id)
            .then(res => res.json())
            .then(user => {
                let idInput = document.getElementById("_id");
                let fullnameInput = document.getElementById("fullname");
                let ageInput = document.getElementById("age");
                let radioMale = document.getElementById("male");
                let radioFemale = document.getElementById("female");
                let submitButton = document.getElementById("submit");
                idInput.value = user._id;
                fullnameInput.value = user.fullname;
                ageInput.value = user.age;
                if (user.gender) {
                    radioMale.checked = true;

                    this.setState({
                        radioOption: true
                    });
                } else {
                    radioFemale.checked = true;

                    this.setState({
                        radioOption: false
                    });
                }
                submitButton.value = 1;

                fullnameInput.focus();
            });
    }

    onSubmitClick(event) {
        let temp = event.target.value;

        // Add new user
        if (temp === "0") {
            let user = {
                fullname: document.getElementById("fullname").value,
                age: document.getElementById("age").value,
                gender: this.state.radioOption
            };

            fetch("/api/users", {
                method: "POST",
                body: JSON.stringify(user),
                headers: {
                    "Content-Type": "application/json"
                }
            })
                .then(res => res.json())
                .then(userSave => {
                    fetch("/api/users")
                        .then(res => res.json())
                        .then(users => this.setState({ users: users }));
                });

            this.onAddButtonClick();
        }

        // Update user
        if (temp === "1") {
            let id = document.getElementById("_id").value;

            fetch("/api/users/" + id)
                .then(res => res.json())
                .then(user => {
                    user.fullname = document.getElementById("fullname").value;
                    user.age = document.getElementById("age").value;
                    user.gender = this.state.radioOption;

                    fetch("/api/users/" + id, {
                        method: "PUT",
                        body: JSON.stringify(user),
                        headers: {
                            "Content-Type": "application/json"
                        }
                    })
                        .then(res => res.json())
                        .then(userUpdate => {
                            fetch("/api/users")
                                .then(res => res.json())
                                .then(users => this.setState({ users: users }));
                        });
                });
        }

        event.preventDefault();
    }

    onDeleteButtonClick(event) {
        let id = event.target.value;

        let check = window.confirm("Confirm delete user ? id: " + id);
        if (check) {
            fetch("/api/users/" + id, {
                method: "DELETE"
            })
                .then(res => res.json())
                .then(result => {
                    fetch("/api/users")
                        .then(res => res.json())
                        .then(users => this.setState({ users: users }));
                });

            event.preventDefault();
        }
    }

    render() {
        return (
            <div className="Users">
                <Container>
                    <h2>Users</h2>
                    <Container>
                        <Button onClick={this.onAddButtonClick}>Add</Button>
                    </Container>
                    <Container>
                        <Table striped bordered hover>
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Fullname</th>
                                    <th>Gender</th>
                                    <th>Age</th>
                                    <th>Update</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.state.users.map((user, index) => (
                                    <tr key={index}>
                                        <td>{user._id}</td>
                                        <td>{user.fullname}</td>
                                        <td>
                                            {user.gender ? "true" : "false"}
                                        </td>
                                        <td>{user.age}</td>
                                        <td>
                                            <Button
                                                value={user._id}
                                                onClick={
                                                    this.onUpdateButtonClick
                                                }
                                            >
                                                Update
                                            </Button>
                                        </td>
                                        <td>
                                            <Button
                                                value={user._id}
                                                onClick={
                                                    this.onDeleteButtonClick
                                                }
                                            >
                                                Delete
                                            </Button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </Table>
                    </Container>
                    <hr />
                    <Container>
                        <Form>
                            <Form.Group as={Row}>
                                <Form.Label column sm={2}>
                                    #
                                </Form.Label>
                                <Col sm={10}>
                                    <Form.Control
                                        type="text"
                                        name="id"
                                        id="_id"
                                        readOnly={true}
                                    />
                                </Col>
                            </Form.Group>
                            <Form.Group as={Row}>
                                <Form.Label column sm={2}>
                                    Fullname
                                </Form.Label>
                                <Col sm={10}>
                                    <Form.Control
                                        type="text"
                                        placeholder="Enter fullname"
                                        name="fullname"
                                        id="fullname"
                                    />
                                </Col>
                            </Form.Group>
                            <fieldset>
                                <Form.Group as={Row}>
                                    <Form.Label as="legend" column sm={2}>
                                        Gender
                                    </Form.Label>
                                    <Col sm={10}>
                                        <Form.Check
                                            type="radio"
                                            label="Male"
                                            name="gender"
                                            id="male"
                                            value={true}
                                            onChange={this.onRadioOptionChange}
                                        />
                                        <Form.Check
                                            type="radio"
                                            label="Female"
                                            name="gender"
                                            id="female"
                                            value={false}
                                            onChange={this.onRadioOptionChange}
                                        />
                                    </Col>
                                </Form.Group>
                            </fieldset>
                            <Form.Group as={Row}>
                                <Form.Label column sm={2}>
                                    Age
                                </Form.Label>
                                <Col sm={10}>
                                    <Form.Control
                                        type="number"
                                        placeholder="Age"
                                        name="age"
                                        id="age"
                                        min={0}
                                        defaultValue={18}
                                    />
                                </Col>
                            </Form.Group>

                            <Button
                                variant="primary"
                                type="submit"
                                id="submit"
                                value={0}
                                onClick={this.onSubmitClick}
                            >
                                Submit
                            </Button>
                        </Form>
                    </Container>
                </Container>
            </div>
        );
    }
}
