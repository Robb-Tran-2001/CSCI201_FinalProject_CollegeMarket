const jsonServer = require('json-server')
const server = jsonServer.create()
const path = require('path')
const router = jsonServer.router(path.join(__dirname, '/db.json'))
const middlewares = jsonServer.defaults()
// const delay = requre('express-delay')
server.use(function (req, res, next) {
  setTimeout(next, 1000)
})
server.use(middlewares)
server.use(router)
server.listen(3001, () => {
  console.log('JSON Server is running')
})
