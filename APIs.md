# APIs needed for frontend app

## Items

### Item list

> GET http://placeholder.com/api/items

_Should only return available items_

**Format**

```json
[
  {
    "name": "placeholder",
    "price": 300,
    "picture": "url",
    "itemid": 1
  },
  {
    "name": "placeholder",
    "price": 300,
    "picture": "url",
    "itemid": 2
  },
  {
    "name": "placeholder",
    "price": 300,
    "picture": "url",
    "itemid": 3
  }
]
```

---

### Item detail

> GET http://placeholder.com/api/items/itemid

**_It is better to return a boolean than the actual buyerid_**

**Format**

```json
{
  "name": "name",
  "price": 300,
  "pictures": ["url1", "url2", "url3"],
  "description": "str",
  "itemid": "itemid",
  "seller": "seller name"
}
```

---

### Search

> GET http://placeholder.com/api/items?q=searchterms

**_Spaces will be converted to %20_**

**Format**

_Same as item list_

---

### Buy item

> POST http://placeholder.com/api/buy

#### Format

```json
{
  "username": "username",
  "itemid": "itemid"
}
```

---

### Sell item

> POST http://placeholder.com/api/sell

The body will be _multipart/formdata_

---

## Users

**_For now the email is the username on frontend_**

### User login

> POST http://placeholder/api/user/login

_Backend won't get the actual password but a SHA-256 encrypted username:password pair_

Return 200 and token if correct, 401 if input is incorrect

#### Format

```json
{
  "username": "username",
  "hash": "hash"
}
```

---

### User signup

> POST http://placeholder.com/api/user/signup

#### Format

```json
{
  "username": "username",
  "hash": "hash"
}
```

---

### Get user incoming requests

> GET http://placeholder.com/api/user/userid

**Format**

```json
[
  {
    "name": "placeholder",
    "price": 300,
    "buyer": "buyer",
    "itemid": 1
  },
  {
    "name": "placeholder",
    "price": 300,
    "buyer": "buyer",
    "itemid": 1
  },
  {
    "name": "placeholder",
    "price": 300,
    "buyer": "buyer",
    "itemid": 1
  },
  {
    "name": "placeholder",
    "price": 300,
    "buyer": "buyer",
    "itemid": 1
  }
]
```

---

### Change password

> POST http://p.com/api/user/password

```json
{
  "username": "username",
  "/hash": "hash"
}
```

---

### Approve purchase

> POST http://placeholder.com/api/user/approve

#### Format

```json
{
  "seller": "sellername",
  "buyer": "buyername"
  "itemid": "itemid"
}
```

---

### Push notifications

> ws://p:8080/push/{username}

**_Is username needed?_**
