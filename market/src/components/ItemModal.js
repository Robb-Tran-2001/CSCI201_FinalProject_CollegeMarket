import React, { useState, useEffect } from 'react'
import {
  Modal,
  Row,
  Col,
  Spinner,
  Carousel,
  Container,
  Button,
} from 'react-bootstrap'
import { ITEM_DETAIL_SERVICE_ADDRESS } from '../Paths'
import { BUY_ITEM_SERVICE_ADDRESS } from '../Paths'

export const ItemModal = ({ username, itemid, close }) => {
  const [loaded, setLoaded] = useState(false)
  const [item, setItem] = useState(null)
  const [buyAttempted, setBuyAttempted] = useState(false)
  const handleBuy = e => {
    e.stopPropagation()
    setBuyAttempted(true)
    // console.info('POST ' + BUY_ITEM_SERVICE_ADDRESS, {
    //   itemid: item.itemid,
    //   username: username,
    // })
    fetch(BUY_ITEM_SERVICE_ADDRESS, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        itemid: item.itemid,
        username: username,
      }),
    }).then(res => {
      if (res.status === 200) {
        alert('Successful. Waiting for seller approval now.')
      } else {
        alert('Item is not available.')
      }
    })
  }
  const carouselItems =
    item &&
    item.image.map((element, index) => (
      <Carousel.Item key={index}>
        <img className='d-block w-100' src={element} alt={index} />
      </Carousel.Item>
    ))
  const CloseButton = () => (
    <div className='modal-close-button' onClick={close}>
      <ion-icon style={{ fontSize: 64 }} name='close-outline'></ion-icon>
    </div>
  )

  useEffect(() => {
    if (itemid === -1) return
    // console.info('GET ' + ITEM_DETAIL_SERVICE_ADDRESS + itemid)
    fetch(ITEM_DETAIL_SERVICE_ADDRESS + itemid)
      .then(res => res.json())
      .then(
        res => {
          setItem(res)
          setLoaded(true)
          setBuyAttempted(res.buyerid !== '')
        },
        err => {
          alert('Loading failed', err)
          close()
        }
      )
  }, [itemid, close])
  return (
    <Modal show={itemid !== -1} onHide={close} animation={false} dialogAs='div'>
      <CloseButton />
      {loaded ? (
        <Container fluid>
          <Row>
            <Col md={8}>
              <Carousel
                interval={null}
                slide={false}
                className='carousel-container'
              >
                {carouselItems}
              </Carousel>
            </Col>
            <Col md={4}>
              <Container fluid className='desc-container'>
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
                {username ? (
                  <Button
                    className='buy-button'
                    disabled={buyAttempted}
                    onClick={handleBuy}
                  >
                    {buyAttempted ? 'Pending' : 'Buy now'}
                  </Button>
                ) : (
                  <Button disabled className='buy-button'>
                    Log in to buy
                  </Button>
                )}
              </Container>
            </Col>
          </Row>
        </Container>
      ) : (
        <Spinner
          animation='border'
          variant='primary'
          className='loading-spinner'
        />
      )}
    </Modal>
  )
}
