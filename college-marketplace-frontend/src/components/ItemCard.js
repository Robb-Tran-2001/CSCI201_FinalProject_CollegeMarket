import React, { useState } from 'react'
import Card from 'react-bootstrap/Card'
import Button from 'react-bootstrap/Button'
import { useRecoilValue } from 'recoil'
import { isAuthorizedState } from '../recoil/atoms'
import { buyItem } from './UserActions'

const ItemCard = ({ item, handleClick }) => {
  const [hover, setHover] = useState(false)
  const IsAuthorized = useRecoilValue(isAuthorizedState)
  const [buyAttempted, setBuyAttempted] = useState(false)
  const handleBuy = (e) => {
    e.stopPropagation()
    setBuyAttempted(true)
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
              >
                Buy
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