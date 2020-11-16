const prefix =
  process.env.NODE_ENV === 'development' ? 'http://127.0.0.1:3001' : 'api'

export const ALL_ITEMS_SERVICE_ADDRESS = prefix + '/items'

export const ITEM_DETAIL_SERVICE_ADDRESS = prefix + '/item/' // itemid

export const LOGIN_SERVICE_ADDRESS = prefix + '/login'

export const SIGNUP_SERVICE_ADDRESS = prefix + '/signup'

export const SEARCH_SERVICE_ADDRESS = prefix + '/search' // ?q={search terms}

export const USER_INFO_SERVICE_ADDRESS = prefix + '/user/' // username

export const CREATE_ITEM_SERVICE_ADDRESS = prefix + '/create'

export const WEBSOCKET_ADDRESS =
  'ws://' +
  window.location.host +
  process.env.PUBLIC_URL +
  '/api/push/' +
  sessionStorage.getItem('token')
