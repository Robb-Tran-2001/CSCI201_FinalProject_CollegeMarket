import { Route } from 'react-router-dom'
import React from 'react'
import Switch from 'react-bootstrap/esm/Switch'
import { Catalog } from './Catalog'

export const Router = () => {
  return (
    <Switch>
      <Route path="/">
        <Catalog />
      </Route>
    </Switch>
  )
}
