import React, { useState, useEffect, useMemo, useCallback } from 'react'
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
    const socket = new SockJS('/push_notif')
    const pushClient = Stomp.over(socket)
    // stompClient.debug = () => {}
    pushClient.connect({}, function () {
      pushClient.subscribe('/topic/messages', function (m) {
        const data = JSON.parse(m.body)
        console.log(data)
        addToast(data.user + ' just requested to buy ' + data.item + '!', {
          appearance: 'info',
        })
      })
    })
    return () => {
      pushClient.disconnect()
    }
  }, [addToast])
  return (
    <BrowserRouter basename={process.env.PUBLIC_URL}>
      <Nav username={username} updateUser={updateUsername} />
      <Router username={username} />
    </BrowserRouter>
  )
}

export default App
