const jsonServer = require('json-server')
const server = jsonServer.create()
const path = require('path')
const router = jsonServer.router(path.join(__dirname, '/db.json'))
const middlewares = jsonServer.defaults()
const multer = require('multer')
const upload = multer()
const bodyParser = require('body-parser')

server.use(bodyParser.json())
server.use(bodyParser.urlencoded({ extended: true }))

server.use(function (req, res, next) {
  setTimeout(next, 1000)
})
server.use(middlewares)

server.post('/login', (req, res) => {
  if (req.body.email === 'a@a') {
    //res.send({ token: '1234' })
    res.send({ token: '1234', name: 'tommy trojan' })
  } else {
    res.sendStatus(401)
  }
})

server.post('/create', upload.array('picture', 5), (req, res) => {
  console.log(req.body)
  res.sendStatus(200)
})

server.use(function (err, req, res, next) {
  console.log('This is the invalid field ->', err.field)
  next(err)
})
server.get('/search', (req, res) => {
  res.send([])
})
server.use(router)

server.listen(3001, () => {
  console.log('JSON Server is running')
})
