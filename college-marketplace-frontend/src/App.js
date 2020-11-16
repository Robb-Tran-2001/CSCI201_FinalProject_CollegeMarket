import React, { useEffect } from 'react'
import Nav from './components/Nav'
import './App.scss'
import { useRecoilState, useSetRecoilState } from 'recoil'
import { BrowserRouter } from 'react-router-dom'
import { Router } from './components/Router'
import { useToasts } from 'react-toast-notifications'
import { isAuthorizedState } from './recoil/atoms'
function App() {
  const setAuthorized = useSetRecoilState(isAuthorizedState)
  const { addToast } = useToasts()
  useEffect(() => {
    if (sessionStorage.getItem('username') !== null) {
      setAuthorized(true)
    }
  }, [])
  useEffect(() => {
    if (sessionStorage.getItem('username') === null) {
      return
    }
    console.log('Connecting to push')
    const ws = new WebSocket(
      'ws://localhost:8080/push_notification_v1/push/' +
        sessionStorage.getItem('username')
    )
    ws.onmessage = (event) => {
      addToast(JSON.parse(event.data.msg), { appearance: 'info' })
    }
    return () => {
      ws.close()
    }
  }, [sessionStorage.getItem('username')])
  return (
    <BrowserRouter basename={process.env.PUBLIC_URL}>
      <Nav />
      <Router />
    </BrowserRouter>
  )
}

export default App
