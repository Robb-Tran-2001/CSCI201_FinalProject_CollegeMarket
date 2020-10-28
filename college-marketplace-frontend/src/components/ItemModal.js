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
    setItem({
      name: 'test',
      price: 300,
      description:
        'testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest',
      image: [
        'https://images.unsplash.com/photo-1593642632823-8f785ba67e45?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1189&q=80',
        'https://www.usc.edu/wp-content/uploads/sites/2/2020/03/COVID_Feature_02.jpg',
        'https://www.usc.edu/wp-content/uploads/sites/2/2020/10/Scott-Fraser-480-240x150.jpg',
      ],
      seller: 'Felix',
    })
    setLoaded(true)
    // fetch('url' + itemid)
    //   .then((res) => res.json())
    //   .then(
    //     (item) => {
    //       setItem(item)
    //       setLoaded(true)
    //     },
    //     (err) => {
    //       alert('Loading failed', err)
    //       close()
    //     }
    //   )
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
          style={{ margin: 'auto auto' }}
        />
      )}
    </Modal>
  )
}
