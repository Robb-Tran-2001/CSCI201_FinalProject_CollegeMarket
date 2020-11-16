import React, { useState, useEffect } from 'react'
import { Nav, Row, Col, ListGroup, Form, Button, Table } from 'react-bootstrap'
import { USER_INFO_SERVICE_ADDRESS } from '../Paths'
import { ItemModal } from './ItemModal'

export const User = () => {
  const [tab, setTab] = useState('password')
  const [orders, setOrders] = useState({ buy: [], sell: [] })
  const [modalItemID, setModalItemID] = useState(-1)
  const handleClose = () => setModalItemID(-1)
  useEffect(() => {
    fetch(USER_INFO_SERVICE_ADDRESS + sessionStorage.getItem('username'))
      .then((res) => res.json())
      .then((res) => {
        console.log(res)
        setOrders(res)
      })
  }, [])
  const orderTable = (list) =>
    list.map((order) => (
      <tr key={order.itemid}>
        <td>{order.name}</td>
        <td>${order.price}</td>
        <td>{order.seller}</td>
        <td>{order.status ? 'Complete' : 'Pending'}</td>
      </tr>
    ))
  return (
    <>
      <Row className="mt-5">
        <Col md={{ span: 2, offset: 1 }}>
          <Nav
            variant="pills"
            defaultActiveKey="password"
            className="flex-column"
            onSelect={(key) => setTab(key)}
          >
            <Nav.Item>
              <Nav.Link eventKey="password">Change password</Nav.Link>
            </Nav.Item>
            <Nav.Item>
              <Nav.Link eventKey="orders">My Orders</Nav.Link>
            </Nav.Item>
          </Nav>
        </Col>
        <Col md={{ span: 6 }} className="ml-5">
          {tab === 'password' ? (
            <>
              <h3>Change password</h3>
              <Form>
                <Form.Group controlId="name">
                  <Form.Label>Username</Form.Label>
                  <Form.Control
                    type="email"
                    placeholder="Enter your name"
                    defaultValue={sessionStorage.getItem('username')}
                    disabled
                  />
                </Form.Group>
                <Form.Group controlId="password">
                  <Form.Label>Password</Form.Label>
                  <Form.Control type="password" placeholder="Password" />
                </Form.Group>
                <Button variant="primary" type="submit">
                  Save changes
                </Button>
              </Form>
            </>
          ) : (
            <>
              <h3>Your Orders</h3>
              <h4>Purchases</h4>
              <Table>
                <thead>
                  <th>Name</th>
                  <th>Price</th>
                  <th>Seller</th>
                  <th>Status</th>
                </thead>
                <tbody>{orderTable(orders.buy)}</tbody>
              </Table>
              <h3>Listings</h3>
              <Table>
                <thead>
                  <th>Name</th>
                  <th>Price</th>
                  <th>Seller</th>
                  <th>Status</th>
                </thead>
                <tbody>{orderTable(orders.sell)}</tbody>
              </Table>
            </>
          )}
        </Col>
      </Row>
    </>
  )
}
