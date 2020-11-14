import { atom } from 'recoil'

export const currentItemState = atom({
  key: 'todoListState',
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
