import React, { useState } from 'react'
import { Navbar, Form, FormControl, Button, Col } from 'react-bootstrap'
import { Link, useHistory } from 'react-router-dom'
import { Login } from './Login'
import { SEARCH_SERVICE_ADDRESS } from '../Paths'

const Nav = ({ username, updateUser }) => {
  const [show, setShow] = useState(false)
  const [searchInput, setSearchInput] = useState('')
  const history = useHistory()
  const handleClickUser = () => {
    username ? history.push('/user') : setShow(true)
  }
  const handleSearch = (e) => {
    e.preventDefault()
    e.stopPropagation()
    if (searchInput.trim() === '') return
    console.info(
      'GET ' +
        SEARCH_SERVICE_ADDRESS +
        '?q=' +
        searchInput.trim().replace(' ', '%20')
    )
    fetch(
      SEARCH_SERVICE_ADDRESS + '?q=' + searchInput.trim().replace(' ', '%20')
    )
      .then((res) => res.json())
      .then((res) => {
        if (res.length === 0) {
          alert('No results found!')
          return
        }
        history.push('/', res)
      })
  }
  const handleClose = () => setShow(false)

  return (
    <>
      <Navbar bg="primary" position="static" variant="dark" expand>
        <Col md={3}>
          <Navbar.Brand as={Link} to="/" className="mr-auto">
            USC Marketplace
          </Navbar.Brand>
        </Col>
        <Col md={6}>
          <Form inline className="" onSubmit={handleSearch}>
            <FormControl
              type="text"
              style={{ width: '90%', marginRight: '-5px' }}
              placeholder="Search"
              onChange={(e) => setSearchInput(e.target.value)}
            />
            <Button
              variant="primary"
              type="submit"
              style={{ width: '10%', marginLeft: '-5px', borderRadius: '0px' }}
            >
              <ion-icon name="search-outline"></ion-icon>
            </Button>
          </Form>
        </Col>
        <Col md={3} style={{ textAlign: 'right' }}>
          <Navbar.Text onClick={handleClickUser} style={{ cursor: 'pointer' }}>
            {username || 'Log in'}
          </Navbar.Text>
        </Col>
      </Navbar>
      <Login show={show} handleClose={handleClose} updateUser={updateUser} />
    </>
  )
}

export default Nav
