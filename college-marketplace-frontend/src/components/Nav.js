import React from 'react'
import { Navbar, Form, FormControl, Button, Row, Col } from 'react-bootstrap'
import { Link } from 'react-router-dom'

const Nav = () => {
  return (
    <Navbar
      bg="primary"
      position="static"
      variant="dark"
      justify-content-between
      expand
    >
      <Col md={3}>
        <Navbar.Brand as={Link} to="/" className="mr-auto">
          USC Marketplace
        </Navbar.Brand>
      </Col>
      <Col md={6}>
        <Form inline className="">
          <FormControl
            type="text"
            style={{ width: '90%' }}
            placeholder="Search"
          />
          <Button variant="light" type="button" style={{ width: '10%' }}>
            <ion-icon name="search-outline"></ion-icon>
          </Button>
        </Form>
      </Col>
      <Col md={3} style={{ textAlign: 'right' }}>
        <Navbar.Text>Log in</Navbar.Text>
      </Col>
    </Navbar>
  )
}

export default Nav
