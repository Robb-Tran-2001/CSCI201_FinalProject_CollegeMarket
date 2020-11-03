import React from 'react'
import Nav from './components/Nav'
import './App.scss'
import { RecoilRoot, useSetRecoilState } from 'recoil'
import { BrowserRouter } from 'react-router-dom'
import { Router } from './components/Router'
import { isAuthorizedState, usernameState } from './recoil/atoms'
function App() {
  const setAuthorized = useSetRecoilState(isAuthorizedState)
  const setUsername = useSetRecoilState(usernameState)

  if (sessionStorage.getItem('token')) {
    setAuthorized(true)
    setUsername(sessionStorage.getItem('username'))
  }
  return (
    <BrowserRouter>
      <Nav />
      <Router />
    </BrowserRouter>
  )
}

export default App
