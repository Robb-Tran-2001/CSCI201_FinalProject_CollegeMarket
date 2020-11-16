import { Route, useLocation } from 'react-router-dom'
import React from 'react'
import Switch from 'react-bootstrap/esm/Switch'
import { Catalog } from './Catalog'
import { User } from './User'
import { Sell } from './Sell'

export const Router = () => {
  const IsAuthorized = sessionStorage.getItem('username')
  const location = useLocation()
  return (
    <Switch className="p-0">
      {IsAuthorized && (
        <Switch>
          <Route path="/user">
            <User />
          </Route>
          <Route path="/create">
            <Sell />
          </Route>
        </Switch>
      )}
      {location.state ? (
        <Catalog searchitems={location.state} />
      ) : (
        <Route path="/" exact>
          <Catalog />
        </Route>
      )}
    </Switch>
  )
}
