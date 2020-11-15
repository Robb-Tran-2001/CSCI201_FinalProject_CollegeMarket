import React from 'react'
import Nav from './components/Nav'
import './App.scss'
import { Catalog } from './components/Catalog'
import { RecoilRoot } from 'recoil'
function App() {
  return (
    <RecoilRoot>
      <Nav />
      <Catalog />
    </RecoilRoot>
  )
}

export default App
