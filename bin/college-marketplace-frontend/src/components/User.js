import React, { useState, useEffect } from 'react'
import { Nav, Row, Col, ListGroup, Form, Button, Table } from 'react-bootstrap'
import { useRecoilValue } from 'recoil'
import { USER_INFO_SERVICE_ADDRESS } from '../Paths'
import { usernameState } from '../recoil/atoms'
import { ItemModal } from './ItemModal'

export const User = () => {
  const [tab, setTab] = useState('profile')
  const username = useRecoilValue(usernameState)
  const [email, setEmail] = useState('')
  const [orders, setOrders] = useState([])
  const [modalItemID, setModalItemID] = useState(-1)
  const handleClose = () => setModalItemID(-1)
  const handleClick = (itemid) => setModalItemID(itemid)
  useEffect(() => {
    fetch(USER_INFO_SERVICE_ADDRESS + sessionStorage.getItem('token'))
      .then((res) => res.json())
      .then((res) => {
        console.log(res)
        setEmail(res.profile.email)
        setOrders(res.orders)
      })
  }, [])
  const orderTable = (list) =>
    list.map((order) => (
      <tr
        key={order.itemid}
        onClick={() => handleClick(order.itemid)}
        style={{ cursor: 'pointer' }}
      >
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
            defaultActiveKey="profile"
            className="flex-column"
            onSelect={(key) => setTab(key)}
          >
            <Nav.Item>
              <Nav.Link eventKey="profile">Profile</Nav.Link>
            </Nav.Item>
            <Nav.Item>
              <Nav.Link eventKey="orders">My Orders</Nav.Link>
            </Nav.Item>
          </Nav>
        </Col>
        <Col md={{ span: 6 }} className="ml-5">
          {tab === 'profile' ? (
            <Form>
              <Form.Group controlId="email">
                <Form.Label>Email address</Form.Label>
                <Form.Control
                  type="email"
                  placeholder="Enter email"
                  defaultValue={email}
                />
              </Form.Group>
              <Form.Group controlId="name">
                <Form.Label>Name</Form.Label>
                <Form.Control
                  type="email"
                  placeholder="Enter your name"
                  defaultValue={username}
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
          ) : (
            <>
              <h3>Your purchases: </h3>
              <Table>
                <thead>
                  <th>Name</th>
                  <th>Price</th>
                  <th>Seller</th>
                  <th>Status</th>
                </thead>
                <tbody>{orderTable(orders.buy)}</tbody>
              </Table>
              <h3>Your listings: </h3>
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
      <ItemModal itemid={modalItemID} close={handleClose} />
    </>
  )
}
