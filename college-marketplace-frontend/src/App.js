import React, { useEffect } from 'react'
import Nav from './components/Nav'
import './App.scss'
import { BrowserRouter } from 'react-router-dom'
import { Router } from './components/Router'
import { useToasts } from 'react-toast-notifications'
import { WEBSOCKET_ADDRESS } from './Paths'
function App() {
  const { addToast } = useToasts()
  const username = sessionStorage.getItem('username')
  useEffect(() => {
    if (username === null) {
      return
    }
    console.info('Connecting to push')
    console.info(WEBSOCKET_ADDRESS)
    const ws = new WebSocket(WEBSOCKET_ADDRESS)
    ws.onmessage = (event) => {
      const data = JSON.parse(event.data)
      const msg = data.buyer + 'just bought' + data.item + '!'
      addToast(msg, { appearance: 'info' })
    }
    return () => {
      ws.close()
    }
  }, [username])
  return (
    <BrowserRouter basename={process.env.PUBLIC_URL}>
      <Nav />
      <Router />
    </BrowserRouter>
  )
}

export default App
