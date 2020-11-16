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
  if (req.body.username === 'a') {
    //res.send({ token: '1234' })
    res.send({ token: '1234', name: 'tommy trojan' })
  } else {
    res.sendStatus(401)
  }
})

server.post('/buy', (req, res) => {
  res.sendStatus(200)
})

server.post('/user/password', (req, res) => {
  console.log(req.body)
  res.sendStatus(200)
})
server.post('/user/approve', (req, res) => {
  console.log(req.body)
  res.sendStatus(200)
})

server.post('/create', upload.array('picture', 5), (req, res) => {
  console.log(req.body)
  res.sendStatus(200)
})

server.get('/user/a', (req, res) => {
  const user = [
    {
      itemid: 22,
      name: 'test',
      price: 500,
      buyer: 'Felix Liao',
    },
    {
      itemid: 22,
      name: 'test',
      price: 500,
      buyer: 'Felix Liao',
    },
    {
      itemid: 22,
      name: 'test',
      price: 500,
      buyer: 'Felix Liao',
    },
    {
      itemid: 22,
      name: 'test',
      price: 500,
      buyer: 'Felix Liao',
    },
    {
      itemid: 22,
      name: 'test',
      price: 500,
      buyer: 'Felix Liao',
    },
  ]

  res.send(JSON.stringify(user))
})

server.use(function (err, req, res, next) {
  console.log('This is the invalid field ->', err.field)
  next(err)
})
server.get('/search', (req, res) => {
  res.send([
    {
      itemid: 21,
      name: 'test',
      price: 500,
      pic:
        'https://images.unsplash.com/photo-1593642632823-8f785ba67e45?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1189&q=80',
      buyerid: '',
    },
    {
      itemid: 22,
      name: 'test',
      price: 500,
      pic:
        'https://images.unsplash.com/photo-1593642632823-8f785ba67e45?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1189&q=80',
      buyerid: '',
    },
    {
      itemid: 23,
      name: 'test',
      price: 500,
      pic:
        'https://images.unsplash.com/photo-1593642632823-8f785ba67e45?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1189&q=80',
      buyerid: '',
    },
    {
      itemid: 24,
      name: 'test',
      price: 500,
      pic:
        'https://images.unsplash.com/photo-1593642632823-8f785ba67e45?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1189&q=80',
      buyerid: '',
    },
  ])
})
server.use(router)

server.listen(3001, () => {
  console.log('JSON Server is running')
})
