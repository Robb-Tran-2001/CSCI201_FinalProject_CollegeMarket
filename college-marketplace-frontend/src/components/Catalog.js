import React, { useState, useEffect } from 'react'
import { Container, Row, Col } from 'react-bootstrap'
import ItemCard from './ItemCard'
import { ItemModal } from './ItemModal'

export const Catalog = () => {
  const [modalItemID, setModalItemID] = useState(-1)
  const [Items, setItems] = useState(null)
  const handleClick = (itemid) => {
    setModalItemID(itemid)
  }
  useEffect(() => {
    fetch('http://127.0.0.1:3001/items')
      .then((res) => res.json())
      .then((res) => setItems(res))
  }, [])
  const closeModal = () => {
    setModalItemID(-1)
  }

  const itemCards =
    Items &&
    Items.map((item) => (
      <Col>
        <ItemCard item={item} handleClick={handleClick} />
      </Col>
    ))
  return (
    <>
      <Container fluid="lg">
        <Row xs={2} md={4}>
          {itemCards}
        </Row>
      </Container>
      <ItemModal itemid={modalItemID} close={closeModal} />
    </>
  )
}
