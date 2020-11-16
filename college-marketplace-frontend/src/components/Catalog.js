import React, { useState, useEffect } from 'react'
import { Container, Row, Col, Spinner, Button } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import { ALL_ITEMS_SERVICE_ADDRESS } from '../Paths'
import ItemCard from './ItemCard'
import { ItemModal } from './ItemModal'

export const Catalog = ({ searchitems }) => {
  const [modalItemID, setModalItemID] = useState(-1)
  const [items, setItems] = useState(searchitems || [])
  const [page, setPage] = useState(1)
  const IsAuthorized = sessionStorage.getItem('username') !== null
  const handleClick = (itemid) => {
    setModalItemID(itemid)
  }

  useEffect(() => {
    if (searchitems) return
    console.info('GET ' + ALL_ITEMS_SERVICE_ADDRESS)
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
    items &&
    items.slice((page - 1) * 20, page * 20).map((item) => (
      <Col key={page + '.' + item.itemid}>
        <ItemCard item={item} handleClick={handleClick} />
      </Col>
    ))
  return (
    <>
      <Container fluid="lg" className="my-4">
        {IsAuthorized ? (
          <Button as={Link} to={'/create'}>
            Create new listing
          </Button>
        ) : (
          ''
        )}
        {items.length !== 0 ? (
          <>
            <Row xs={2} md={4}>
              {itemCards}
            </Row>
            {page !== 1 ? (
              <Button
                onClick={() => handlePageChange(page - 1)}
                className="mr-3"
              >
                Previous
              </Button>
            ) : (
              ''
            )}
            {page !== Math.ceil(items.length / 20) ? (
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
