import React, { useState, useEffect } from 'react'
import Nav from './components/Nav'
import './App.scss'
import { BrowserRouter } from 'react-router-dom'
import { Router } from './components/Router'
import { useToasts } from 'react-toast-notifications'
// import { WEBSOCKET_ADDRESS } from './Paths'
import SockJS from 'sockjs-client'
import Stomp from 'stompjs'

function App() {
  const { addToast } = useToasts()
  const [username, setUsername] = useState(null)
  useEffect(() => {
    const u = sessionStorage.getItem('username')
    if (u === username) return
    setUsername(u)
  }, [])
  const updateUsername = n => {
    sessionStorage.setItem('username', n)
    setUsername(n)
  }

  useEffect(() => {
    // if (!username) {
    //   return
    // }
    // console.info('Connecting to push')
    // console.info(WEBSOCKET_ADDRESS)

    var socket = new SockJS('/push_notif')
    const stompClient = Stomp.over(socket)
    stompClient.debug = () => {}
    stompClient.connect({}, function () {
      stompClient.subscribe('/topic/messages', function (m) {
        const data = JSON.parse(m.body)
        addToast(data.buyer + ' just bought ' + data.item + '!', {
          appearance: 'info',
        })
      })
    })
    return () => {
      stompClient.disconnect()
    }
  }, [addToast])
  return (
    <BrowserRouter basename={process.env.PUBLIC_URL}>
      <Nav username={username} updateUser={updateUsername} />
      <Router username={username} updateUser={updateUsername} />
    </BrowserRouter>
  )
}

export default App
