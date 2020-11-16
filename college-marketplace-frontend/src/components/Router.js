import { Route, useLocation } from 'react-router-dom'
import React from 'react'
import Switch from 'react-bootstrap/esm/Switch'
import { Catalog } from './Catalog'
import { User } from './User'
import { Sell } from './Sell'

export const Router = ({ username }) => {
  const location = useLocation()
  return (
    <Switch className='p-0'>
      {username && (
        <Switch>
          <Route path='/user'>
            <User username={username} />
          </Route>
          <Route path='/create'>
            <Sell username={username} />
          </Route>
        </Switch>
      )}
      {location.state ? (
        <Catalog username={username} searchitems={location.state} />
      ) : (
        <Route path='/' exact>
          <Catalog username={username} />
        </Route>
      )}
    </Switch>
  )
}
