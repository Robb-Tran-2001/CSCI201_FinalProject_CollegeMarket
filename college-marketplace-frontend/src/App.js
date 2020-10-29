import React from 'react'
import Nav from './components/Nav'
import './App.scss'
import { Catalog } from './components/Catalog'
import { RecoilRoot } from 'recoil'
import { BrowserRouter } from 'react-router-dom'
import { Router } from './components/Router'
function App() {
  return (
    <RecoilRoot>
      <BrowserRouter>
        <Nav />
        <Router />
      </BrowserRouter>
    </RecoilRoot>
  )
}

export default App
