export const buyItem = (itemid) => {
  fetch(`http://localhost:3001/buy/${itemid}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ itemid: itemid }),
  })
}
