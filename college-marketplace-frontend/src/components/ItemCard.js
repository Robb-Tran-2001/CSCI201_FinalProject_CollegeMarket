import React, { useState } from 'react'
import Card from 'react-bootstrap/Card'
import Button from 'react-bootstrap/Button'
import { BUY_ITEM_SERVICE_ADDRESS } from '../Paths'
import { useHistory } from 'react-router'

const ItemCard = ({ item, handleClick }) => {
  const [hover, setHover] = useState(false)
  const history = useHistory()
  const IsAuthorized = sessionStorage.getItem('username') !== null
  const [buyAttempted, setBuyAttempted] = useState(false)
  const handleBuy = (e) => {
    e.stopPropagation()
    setBuyAttempted(true)
    fetch(BUY_ITEM_SERVICE_ADDRESS, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        itemid: item.itemid,
        username: sessionStorage.getItem('username'),
      }),
    }).then((res) => {
      if (res.status === 200) {
        alert('Successful. Waiting for seller approval now.')
      } else {
        alert('Item is not available.')
      }
      history.go(0)
    })
  }
  return (
    <Card
      style={{
        marginTop: 30,
        marginLeft: 15,
        marginRight: 15,
        cursor: 'pointer',
      }}
      onClick={() =>
        handleClick(process.env.NODE_ENV === 'production' ? item.itemid : '')
      }
      onMouseEnter={() => setHover(true)}
      onMouseLeave={() => setHover(false)}
    >
      <div className="square" style={{ backgroundImage: `url(${item.pic})` }}>
        {IsAuthorized ? (
          <div className="cardImageOverlay">
            {hover ? (
              <Button
                style={{
                  position: 'absolute',
                  top: 0,
                  right: 0,
                  borderRadius: 10,
                  marginTop: 5,
                  marginRight: 5,
                }}
                onClick={handleBuy}
                disabled={buyAttempted}
              >
                {buyAttempted ? 'Requested' : 'Buy'}
              </Button>
            ) : (
              ''
            )}
          </div>
        ) : (
          ''
        )}
      </div>
      <Card.Body>
        <Card.Title style={{ marginTop: 10 }}>${item.price}</Card.Title>
        <Card.Text style={{ marginBottom: 10 }}>{item.name}</Card.Text>
      </Card.Body>
    </Card>
  )
}

export default ItemCard
