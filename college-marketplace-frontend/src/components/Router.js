import { Route } from 'react-router-dom'
import React from 'react'
import Switch from 'react-bootstrap/esm/Switch'
import { Catalog } from './Catalog'
import { User } from './User'
import { useRecoilValue } from 'recoil'
import { isAuthorizedState } from '../recoil/atoms'

export const Router = () => {
  const IsAuthorized = useRecoilValue(isAuthorizedState)
  return (
    <Switch>
      {IsAuthorized && (
        <Route path="/user">
          <User />
        </Route>
      )}
      <Route path="/" exact>
        <Catalog />
      </Route>
    </Switch>
  )
}
