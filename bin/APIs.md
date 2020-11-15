# APIs needed for frontend app

## Items

### Item list

> GET http://placeholder.com/api/items

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
  "seller": "seller name",
  "buyer": "buyerid",
  "sold": true
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

> POST http://placeholder.com/api/items/itemid

or

> POST http://placeholder.com/api/buy

**_We can either use a separate endpoint such as /buy/ and put itemid & token in the request body, or use POST on the specific item with token as body_**

#### Format

```json
{
  "userid": "userid",
  "user_token": "token"
}
```

or

```json
{
  "userid": "userid",
  "user_token": "token",
  "itemid": "itemid"
}
```

---

### Sell item

> POST http://placeholder.com/api/items/sell

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
  "email": "email",
  "hash": "hash"
}
```

```json
{
  "token": "token",
  "name": "user's name"
}
```

---

### User signup

> POST http://placeholder.com/api/user/signup

#### Format

```json
{
  "email": "email",
  "hash": "hash",
  "name": "user's actual name"
}
```

---

### Get user profile

> GET http://placeholder.com/api/user/profile/userid

**_I think this needs to be authenticated too but who cares_**

**Format**

```json
{
  "email": "email",
  "name": "user's name"
}
```

---

### Get user orders

> GET http://placeholder.com/api/user/orders/userid

**Format**

```json
{
  "selling": [
    {
      "name": "placeholder",
      "price": 300,
      "buyer": "buyer",
      "itemid": 1,
      "sold": false
    },
    {
      "name": "placeholder",
      "price": 300,
      "buyer": "buyer",
      "itemid": 1,
      "sold": false
    },
    {
      "name": "placeholder",
      "price": 300,
      "buyer": "buyer",
      "itemid": 1,
      "sold": false
    }
  ],
  "buying": [
    {
      "name": "placeholder",
      "price": 300,
      "seller": "seller",
      "itemid": 1,
      "sold": false
    },
    {
      "name": "placeholder",
      "price": 300,
      "seller": "seller",
      "itemid": 1,
      "sold": false
    },
    {
      "name": "placeholder",
      "price": 300,
      "seller": "seller",
      "itemid": 1,
      "sold": false
    }
  ]
}
```

---

### Update user profile

> POST http://p.com/api/user/profile

**_Can contain 1-3 updates_**

```json
{
  "token": "token",
  "name/email/hash": "."
}
```

### Push notifications

> ws://p:8080/push/{username}

**_Is username needed?_**
