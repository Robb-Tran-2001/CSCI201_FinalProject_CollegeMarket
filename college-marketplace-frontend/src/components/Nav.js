import React, { useState } from 'react'
import {
  Navbar,
  Form,
  FormControl,
  Button,
  Row,
  Col,
  Modal,
} from 'react-bootstrap'
import { Link } from 'react-router-dom'
import { SHA256 } from 'crypto-js'

const Nav = () => {
  const [show, setShow] = useState(false)
  const [login, setLogin] = useState(true)
  const [form, setForm] = useState({})
  const [formLoading, setFormLoading] = useState(false)
  const handleShow = () => setShow(true)
  const handleClose = () => setShow(false)
  const toggleLogin = () => setLogin(!login)
  const handleInput = (event) => {
    setForm({ ...form, [event.target.name]: event.target.value })
  }
  const handleSubmit = (event) => {
    event.preventDefault()
    event.stopPropagation()
    if (event.currentTarget.checkValidity() === true) {
      setFormLoading(true)
      const body = { email: form.email }
      const hashed = SHA256(form.email + ':' + form.password).toString()
      body.hash = hashed
      if (!login) {
        body.name = form.name
      }
      console.log(body)
      fetch(`http://localhost:3001/api/${login ? 'login' : 'signup'}`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(body),
      })
      // setFormLoading(false)
    }
  }

  return (
    <>
      <Navbar bg="primary" position="static" variant="dark" expand>
        <Col md={3}>
          <Navbar.Brand as={Link} to="/" className="mr-auto">
            USC Marketplace
          </Navbar.Brand>
        </Col>
        <Col md={6}>
          <Form inline className="">
            <FormControl
              type="text"
              style={{ width: '90%', marginRight: '-5px' }}
              placeholder="Search"
            />
            <Button
              variant="primary"
              type="button"
              style={{ width: '10%', marginLeft: '-5px', borderRadius: '0px' }}
            >
              <ion-icon name="search-outline"></ion-icon>
            </Button>
          </Form>
        </Col>
        <Col md={3} style={{ textAlign: 'right' }}>
          <Navbar.Text onClick={handleShow} style={{ cursor: 'pointer' }}>
            Log in
          </Navbar.Text>
        </Col>
      </Navbar>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header>{login ? 'Log in' : 'Sign up'}</Modal.Header>
        <Modal.Body>
          <Form onChange={handleInput} onSubmit={handleSubmit}>
            <Form.Group controlId="email">
              <Form.Label>Email address</Form.Label>
              <Form.Control
                required
                name="email"
                type="email"
                placeholder="Enter email"
              />
              <Form.Control.Feedback type="invalid">
                Please enter a valid email
              </Form.Control.Feedback>
            </Form.Group>
            {login ? (
              ''
            ) : (
              <Form.Group controlId="name">
                <Form.Label>Name</Form.Label>
                <Form.Control name="name" type="text" placeholder="Name" />
              </Form.Group>
            )}

            <Form.Group controlId="password">
              <Form.Label>Password</Form.Label>
              <Form.Control
                required
                name="password"
                type="password"
                placeholder="Password"
              />
            </Form.Group>
            <Form.Row style={{ alignItems: 'center' }}>
              <Button
                className="mr-auto ml-1"
                variant="primary"
                type="submit"
                disabled={formLoading}
              >
                {formLoading ? 'Processing...' : 'Submit'}
              </Button>
              <i
                className="mr-1"
                style={{ cursor: 'pointer' }}
                onClick={toggleLogin}
              >
                <u>{login ? "Don't have an account?" : 'Have an account?'}</u>
              </i>
            </Form.Row>
          </Form>
        </Modal.Body>
      </Modal>
    </>
  )
}

export default Nav
