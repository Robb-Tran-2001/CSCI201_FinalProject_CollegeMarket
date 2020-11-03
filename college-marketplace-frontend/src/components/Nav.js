import React, { useState } from 'react'
import { Navbar, Form, FormControl, Button, Col } from 'react-bootstrap'
import { Link, useHistory } from 'react-router-dom'
import { useRecoilValue } from 'recoil'
import { isAuthorizedState, usernameState } from '../recoil/atoms'
import { Login } from './Login'

const Nav = () => {
  const [show, setShow] = useState(false)
  const history = useHistory()
  const handleClickUser = () => {
    if (!IsAuthorized) {
      setShow(true)
    } else {
      history.push('/user')
    }
  }
  const handleClose = () => setShow(false)
  const IsAuthorized = useRecoilValue(isAuthorizedState)
  const Username = useRecoilValue(usernameState)

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
          <Navbar.Text onClick={handleClickUser} style={{ cursor: 'pointer' }}>
            {!IsAuthorized ? 'Log in' : Username}
          </Navbar.Text>
        </Col>
      </Navbar>
      <Login show={show} handleClose={handleClose} />
    </>
  )
}

export default Nav
