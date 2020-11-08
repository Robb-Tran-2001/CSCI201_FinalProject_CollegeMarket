import React, { useState, useEffect } from 'react'
import { Modal, Button, Spinner } from 'react-bootstrap'
export const ItemModal = ({ itemid, close }) => {
  const [loaded, setLoaded] = useState(false)
  const [item, setItem] = useState(null)
  useEffect(() => {
    if (itemid === -1) return
    setItem({
      name: 'test',
      price: 300,
      description:
        'testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest',
      image: [
        'https://www.usc.edu/wp-content/uploads/sites/2/2020/10/TFM_AUT20_Telehealth-feat-480-240x150.jpg',
        'https://www.usc.edu/wp-content/uploads/sites/2/2020/03/COVID_Feature_02.jpg',
        'https://www.usc.edu/wp-content/uploads/sites/2/2020/10/Scott-Fraser-480-240x150.jpg',
      ],
      seller: 'Felix',
    })
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
      {loaded ? (
        <div></div>
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
