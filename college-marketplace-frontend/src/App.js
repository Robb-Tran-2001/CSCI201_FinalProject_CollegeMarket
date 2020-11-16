import React, { useEffect } from 'react'
import Nav from './components/Nav'
import './App.scss'
import { BrowserRouter } from 'react-router-dom'
import { Router } from './components/Router'
import { useToasts } from 'react-toast-notifications'
function App() {
  const { addToast } = useToasts()
  const username = sessionStorage.getItem('username')
  useEffect(() => {
    if (username === null) {
      return
    }
    console.log('Connecting to push')
    const ws = new WebSocket(
      'ws://localhost:8080/push_notification_v1/push/' + username
    )
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
