import React, { useState } from 'react'
import { Form, Button, Modal } from 'react-bootstrap'
import { SHA256 } from 'crypto-js'
import { LOGIN_SERVICE_ADDRESS, SIGNUP_SERVICE_ADDRESS } from '../Paths'

export const Login = ({ show, handleClose, updateUser }) => {
  const [login, setLogin] = useState(true)
  const [form, setForm] = useState({})
  const [formLoading, setFormLoading] = useState(false)
  const toggleLogin = () => setLogin(!login)
  const handleInput = (event) => {
    setForm({ ...form, [event.target.name]: event.target.value })
  }
  const handleSubmit = (event) => {
    event.preventDefault()
    event.stopPropagation()
    if (event.currentTarget.checkValidity() === true) {
      setFormLoading(true)
      const body = { username: form.username }
      const hashed = SHA256(form.username + ':' + form.password).toString()
      body.hash = hashed
      // console.info(
      //   'POST',
      //   login ? LOGIN_SERVICE_ADDRESS : SIGNUP_SERVICE_ADDRESS,
      //   body
      // )
      fetch(login ? LOGIN_SERVICE_ADDRESS : SIGNUP_SERVICE_ADDRESS, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(body),
      }).then((res) => {
        if (res.status === 200) {
          updateUser(form.username)
          sessionStorage.setItem('username', form.username)
          setFormLoading(false)
          handleClose()
        } else {
          alert('Invalid email/password!')
          setFormLoading(false)
        }
      })
    }
  }
  return (
    <Modal show={show} onHide={handleClose}>
      <Modal.Header>{login ? 'Log in' : 'Sign up'}</Modal.Header>
      <Modal.Body>
        <Form onChange={handleInput} onSubmit={handleSubmit}>
          <Form.Group controlId="email">
            <Form.Label>Username</Form.Label>
            <Form.Control
              required
              name="username"
              type="text"
              placeholder="Username"
            />
          </Form.Group>
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
  )
}
