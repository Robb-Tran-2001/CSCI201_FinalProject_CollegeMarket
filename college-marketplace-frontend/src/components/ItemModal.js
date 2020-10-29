import React, { useState, useEffect } from 'react'
import {
  Modal,
  Row,
  Col,
  Spinner,
  Carousel,
  Container,
  Image,
  Button,
} from 'react-bootstrap'
export const ItemModal = ({ itemid, close }) => {
  const [loaded, setLoaded] = useState(false)
  const [item, setItem] = useState(null)
  const carouselItems =
    item &&
    item.image.map((element, index) => (
      <Carousel.Item>
        <img className="d-block w-100" src={element} alt={index} />
      </Carousel.Item>
    ))
  const CloseButton = () => (
    <div className="modal-close-button" onClick={close}>
      <ion-icon style={{ fontSize: 64 }} name="close-outline"></ion-icon>
    </div>
  )

  useEffect(() => {
    if (itemid === -1) return
    //setLoaded(true)
    fetch('http://127.0.0.1:3001/item')
      .then((res) => res.json())
      .then(
        (res) => {
          setItem(res)
          setLoaded(true)
        },
        (err) => {
          alert('Loading failed', err)
          close()
        }
      )
  }, [itemid, close])
  return (
    <Modal show={itemid !== -1} onHide={close} animation={false} dialogAs="div">
      <CloseButton />
      {loaded ? (
        <Container fluid>
          <Row>
            <Col md={8}>
              <Carousel
                interval={null}
                slide={false}
                className="carousel-container"
              >
                {carouselItems}
              </Carousel>
            </Col>
            <Col md={4}>
              <Container fluid className="desc-container">
                <h2>
                  <b>{item.name}</b>
                </h2>
                <h4>${item.price}</h4>
                <br />
                <h5>
                  <b>Details</b>
                </h5>
                <p>{item.description}</p>
                <br />
                <h5>
                  <b>Seller</b>
                </h5>
                <p>
                  <b>{item.seller}</b>
                </p>
                <Button className="buy-button">Buy now</Button>
              </Container>
            </Col>
          </Row>
        </Container>
      ) : (
        <Spinner
          animation="border"
          variant="primary"
          className="loading-spinner"
        />
      )}
    </Modal>
  )
}
