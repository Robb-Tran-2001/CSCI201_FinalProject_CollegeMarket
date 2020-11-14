import React, { useState, useEffect } from 'react'
import { Container, Row, Col, Spinner, Button } from 'react-bootstrap'
import { useRecoilState, useRecoilValue } from 'recoil'
import { ALL_ITEMS_SERVICE_ADDRESS } from '../Paths'
import { currentItemState, usernameState } from '../recoil/atoms'
import ItemCard from './ItemCard'
import { ItemModal } from './ItemModal'

export const Catalog = () => {
  const [modalItemID, setModalItemID] = useState(-1)
  const [Items, setItems] = useRecoilState(currentItemState)
  const [page, setPage] = useState(1)
  const handleClick = (itemid) => {
    setModalItemID(itemid)
  }
  useEffect(() => {
    fetch(ALL_ITEMS_SERVICE_ADDRESS)
      .then((res) => res.json())
      .then((res) => setItems(res))
  }, [])

  const closeModal = () => {
    setModalItemID(-1)
  }

  const handlePageChange = (newPage) => {
    setPage(newPage)
    window.scrollTo(0, 0)
  }

  const itemCards =
    Items &&
    Items.slice((page - 1) * 20, page * 20).map((item) => (
      <Col key={page + '.' + item.itemid}>
        <ItemCard item={item} handleClick={handleClick} />
      </Col>
    ))
  return (
    <>
      <Container fluid="lg">
        {Items.length !== 0 ? (
          <>
            <Row xs={2} md={4}>
              {itemCards}
            </Row>
            {page !== 1 ? (
              <Button onClick={() => handlePageChange(page - 1)}>
                Previous
              </Button>
            ) : (
              ''
            )}
            {page !== Math.ceil(Items.length / 20) ? (
              <Button onClick={() => handlePageChange(page + 1)}>Next</Button>
            ) : (
              ''
            )}
          </>
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
