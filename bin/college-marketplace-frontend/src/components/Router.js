import { Route } from 'react-router-dom'
import React from 'react'
import Switch from 'react-bootstrap/esm/Switch'
import { Catalog } from './Catalog'
import { User } from './User'
import { useRecoilValue } from 'recoil'
import { isAuthorizedState } from '../recoil/atoms'
import { Sell } from './Sell'

export const Router = () => {
  const IsAuthorized = useRecoilValue(isAuthorizedState)
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
      <Route path="/" exact>
        <Catalog />
      </Route>
    </Switch>
  )
}
