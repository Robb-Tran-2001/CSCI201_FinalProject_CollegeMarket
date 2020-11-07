import React, { useState, useEffect } from 'react'
import { Container, Row, Col, Spinner } from 'react-bootstrap'
import { useRecoilState, useRecoilValue } from 'recoil'
import { currentItemState, usernameState } from '../recoil/atoms'
import ItemCard from './ItemCard'
import { ItemModal } from './ItemModal'

export const Catalog = () => {
  const [modalItemID, setModalItemID] = useState(-1)
  const [Items, setItems] = useRecoilState(currentItemState)
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
      <Col key={item.id}>
        <ItemCard item={item} handleClick={handleClick} />
      </Col>
    ))
  return (
    <>
      <Container fluid="lg">
        {Items.length !== 0 ? (
          <Row xs={2} md={4}>
            {itemCards}
          </Row>
        ) : (
          <Spinner
            animation="border"
            variant="primary"
            className="loading-spinner"
          />
        )}
      </Container>
      <ItemModal itemid={modalItemID} close={closeModal} />
    </>
  )
}
