import React, { useEffect } from 'react'
import Nav from './components/Nav'
import './App.scss'
import { useRecoilState, useSetRecoilState } from 'recoil'
import { BrowserRouter } from 'react-router-dom'
import { Router } from './components/Router'
import { useToasts } from 'react-toast-notifications'
import { isAuthorizedState, usernameState } from './recoil/atoms'
function App() {
  const setAuthorized = useSetRecoilState(isAuthorizedState)
  const [Username, setUsername] = useRecoilState(usernameState)
  const { addToast } = useToasts()

  if (sessionStorage.getItem('token')) {
    setAuthorized(true)
    setUsername(sessionStorage.getItem('username'))
  }
  useEffect(() => {
    if (Username === '') {
      return
    }
    const ws = new WebSocket(
      'ws://localhost:8080/push_notification_v1/push/' + Username
    )
    ws.onmessage = (event) => {
      addToast(JSON.parse(event.data.msg), { appearance: 'info' })
    }
    return () => {
      ws.close()
    }
  }, [Username])
  return (
    <BrowserRouter>
      <Nav />
      <Router />
    </BrowserRouter>
  )
}

export default App
