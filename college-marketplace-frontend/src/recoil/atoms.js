import { atom } from 'recoil'

export const currentItemState = atom({
  key: 'currItemState',
  default: [],
})

export const isAuthorizedState = atom({
  key: 'isAuthorizedState',
  default: false,
})

export const usernameState = atom({
  key: 'usernameState',
  default: '',
})
