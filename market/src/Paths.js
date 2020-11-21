const prefix =
  process.env.NODE_ENV === 'development' ? 'http://127.0.0.1:3001' : 'api'
// 'http://127.0.0.1:8080/api'

export const ALL_ITEMS_SERVICE_ADDRESS = prefix + '/items'

export const ITEM_DETAIL_SERVICE_ADDRESS = prefix + '/items/' // itemid

export const SEARCH_SERVICE_ADDRESS = prefix + '/search' // ?q={search terms}

export const BUY_ITEM_SERVICE_ADDRESS = prefix + '/buy'

export const CREATE_ITEM_SERVICE_ADDRESS = prefix + '/sell'

export const LOGIN_SERVICE_ADDRESS = prefix + '/user/login'

export const SIGNUP_SERVICE_ADDRESS = prefix + '/user/signup'

export const USER_INFO_SERVICE_ADDRESS = prefix + '/user/' // username

export const USER_PASSWORD_SERVICE_ADDRESS = prefix + '/user/password'

export const USER_APPROVE_PURCHASE_SERVICE_ADDRESS = prefix + '/user/approve'

export const WEBSOCKET_ADDRESS =
  'ws://' + window.location.host + process.env.PUBLIC_URL + '/api/push/'
