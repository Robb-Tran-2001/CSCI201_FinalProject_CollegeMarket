import React, { useState } from 'react'
import { Container, Row, Col } from 'react-bootstrap'
import ItemCard from './ItemCard'
import { ItemModal } from './ItemModal'

const items = [
  {
    name: 'veryveryveryveryveryveryveryverylongname',
    price: '300',
    pic:
      'https://www.usc.edu/wp-content/uploads/sites/2/2020/10/Scott-Fraser-480-240x150.jpg',
  },
  {
    name: 'placeholder',
    price: '300',
    pic:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQBxS4BTQlfMOxtK5KooaUIP7KzRrPijURodg&usqp=CAU',
  },
  {
    name: 'placeholder',
    price: '300',
    pic:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQBxS4BTQlfMOxtK5KooaUIP7KzRrPijURodg&usqp=CAU',
  },
  {
    name: 'placeholder',
    price: '300',
    pic:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQBxS4BTQlfMOxtK5KooaUIP7KzRrPijURodg&usqp=CAU',
  },
  {
    name: 'placeholder',
    price: '300',
    pic:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQBxS4BTQlfMOxtK5KooaUIP7KzRrPijURodg&usqp=CAU',
  },
  {
    name: 'placeholder',
    price: '300',
    pic:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQBxS4BTQlfMOxtK5KooaUIP7KzRrPijURodg&usqp=CAU',
  },
  {
    name: 'placeholder',
    price: '300',
    pic:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQBxS4BTQlfMOxtK5KooaUIP7KzRrPijURodg&usqp=CAU',
  },
  {
    name: 'placeholder',
    price: '300',
    pic:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQBxS4BTQlfMOxtK5KooaUIP7KzRrPijURodg&usqp=CAU',
  },
  {
    name: 'placeholder',
    price: '300',
    pic:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQBxS4BTQlfMOxtK5KooaUIP7KzRrPijURodg&usqp=CAU',
  },
  {
    name: 'placeholder',
    price: '300',
    pic:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQBxS4BTQlfMOxtK5KooaUIP7KzRrPijURodg&usqp=CAU',
  },
  {
    name: 'placeholder',
    price: '300',
    pic:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQBxS4BTQlfMOxtK5KooaUIP7KzRrPijURodg&usqp=CAU',
  },
  {
    name: 'placeholder',
    price: '300',
    pic:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQBxS4BTQlfMOxtK5KooaUIP7KzRrPijURodg&usqp=CAU',
  },
  {
    name: 'placeholder',
    price: '300',
    pic:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQBxS4BTQlfMOxtK5KooaUIP7KzRrPijURodg&usqp=CAU',
  },
  {
    name: 'placeholder',
    price: '300',
    pic:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQBxS4BTQlfMOxtK5KooaUIP7KzRrPijURodg&usqp=CAU',
  },
  {
    name: 'placeholder',
    price: '300',
    pic:
      'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQBxS4BTQlfMOxtK5KooaUIP7KzRrPijURodg&usqp=CAU',
  },
]

export const Catalog = () => {
  const [modalItemID, setModalItemID] = useState(-1)
  const handleClick = (itemid) => {
    setModalItemID(itemid)
  }

  const closeModal = () => {
    setModalItemID(-1)
  }

  const itemCards = items.map((item) => (
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
