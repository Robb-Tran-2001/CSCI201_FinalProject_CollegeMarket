import React, { useState } from 'react'
import { Form, Button, Row, Modal } from 'react-bootstrap'
import { SHA256 } from 'crypto-js'
import { useSetRecoilState } from 'recoil'
import { isAuthorizedState, usernameState } from '../recoil/atoms'

export const Login = ({ show, handleClose }) => {
  const setUsername = useSetRecoilState(usernameState)
  const setAuthorized = useSetRecoilState(isAuthorizedState)
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
      const body = { email: form.email }
      const hashed = SHA256(form.email + ':' + form.password).toString()
      body.hash = hashed
      if (!login) {
        body.name = form.name
      }
      fetch(`http://localhost:3001/${login ? 'login' : 'signup'}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(body),
      })
        .then((res) => res.json())
        .then((res) => {
          setUsername(res.name)
          setAuthorized(true)
          setFormLoading(false)
          handleClose()
        })
        .catch((err) => {
          alert('Invalid email/password!')
          setFormLoading(false)
        })
    }
  }
  return (
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
  )
}
