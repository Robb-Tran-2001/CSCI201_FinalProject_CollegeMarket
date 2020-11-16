import { SHA256 } from 'crypto-js'
import React, { useState, useEffect } from 'react'
import { Nav, Row, Col, Form, Button, Table } from 'react-bootstrap'
import {
  USER_INFO_SERVICE_ADDRESS,
  USER_PASSWORD_SERVICE_ADDRESS,
  USER_APPROVE_PURCHASE_SERVICE_ADDRESS,
} from '../Paths'

export const User = ({ username }) => {
  const [tab, setTab] = useState('orders')
  const [orders, setOrders] = useState([])
  useEffect(() => {
    console.info('GET', USER_INFO_SERVICE_ADDRESS + username)
    fetch(USER_INFO_SERVICE_ADDRESS + username)
      .then((res) => res.json())
      .then((res) => {
        setOrders(res)
      })
  }, [])
  const handlePasswordChange = (e) => {
    e.preventDefault()
    e.stopPropagation()
    const form = new FormData(e.target)
    if (form.get('password').trim() === '') {
      alert('Please enter a valid password')
      return
    }
    console.info('POST', USER_PASSWORD_SERVICE_ADDRESS, {
      username: username,
      hash: SHA256(username + ':' + form.get('password')).toString(),
    })
    fetch(USER_PASSWORD_SERVICE_ADDRESS, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        username: username,
        hash: SHA256(username + ':' + form.get('password')).toString(),
      }),
    }).then((res) => {
      if (res.status === 200) {
        alert('Password changed successfully!')
      } else {
        alert('An error has occured! Please try again.')
      }
    })
  }
  const handleApprovePurchase = (itemid) => {
    console.info('POST', USER_APPROVE_PURCHASE_SERVICE_ADDRESS, {
      username: username,
      itemid: itemid,
    })
    fetch(USER_APPROVE_PURCHASE_SERVICE_ADDRESS, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        username: username,
        itemid: itemid,
      }),
    }).then((res) => {
      if (res.status === 200) {
        alert('Successful')
        const index = orders.findIndex((e) => e.itemid === itemid)
        setOrders([...orders.slice(0, index), ...orders.slice(index + 1)])
      } else {
        alert('An error has occured.')
      }
    })
  }

  const orderTable = (list) =>
    list &&
    list.map((order) => (
      <tr key={order.itemid}>
        <td>{order.name}</td>
        <td>${order.price}</td>
        <td>{order.buyer}</td>
        <td>
          <Button onClick={() => handleApprovePurchase(order.itemid)}>
            Approve
          </Button>
        </td>
      </tr>
    ))
  return (
    <>
      <Row className="mt-5">
        <Col md={{ span: 2, offset: 1 }}>
          <Nav
            variant="pills"
            defaultActiveKey="orders"
            className="flex-column"
            onSelect={(key) => setTab(key)}
          >
            <Nav.Item>
              <Nav.Link eventKey="orders">Requests</Nav.Link>
            </Nav.Item>
            <Nav.Item>
              <Nav.Link eventKey="password">Change password</Nav.Link>
            </Nav.Item>
          </Nav>
        </Col>
        <Col md={{ span: 6 }} className="ml-5">
          {tab === 'password' ? (
            <>
              <h3>Change password</h3>
              <Form onSubmit={handlePasswordChange}>
                <Form.Group controlId="name">
                  <Form.Label>Username</Form.Label>
                  <Form.Control
                    type="text"
                    placeholder="Enter your name"
                    defaultValue={username}
                    disabled
                  />
                </Form.Group>
                <Form.Group controlId="password">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    type="password"
                    placeholder="Password"
                    name="password"
                  />
                </Form.Group>
                <Button variant="primary" type="submit">
                  Save changes
                </Button>
              </Form>
            </>
          ) : (
            <>
              <h3>Incoming Requests</h3>
              <Table>
                <thead>
                  <th>Name</th>
                  <th>Price</th>
                  <th>Buyer</th>
                  <th>Status</th>
                </thead>
                <tbody>{orderTable(orders)}</tbody>
              </Table>
            </>
          )}
        </Col>
      </Row>
    </>
  )
}
