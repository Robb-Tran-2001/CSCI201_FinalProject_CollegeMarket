# APIs needed for frontend app

## Items

### Item list

#### Approach

**1 Server-side**

> GET http://placeholder.com/api/items?page=2

Client request items with page number and items per page (default to 20?)
Server returns the set amount of items

**2 Client-side**

> GET http://placeholder.com/api/items

Client request all items at once
Server returns all the items in one list

#### Format

```
{
    "count": 20,
    "data": [
        {
            "name": "placeholder",
            "price": 300,
            "picture": "url",
            "itemid": 1,
        },
        {
            "name": "placeholder",
            "price": 300,
            "picture": "url",
            "itemid": 2,
        },
        {
            "name": "placeholder",
            "price": 300,
            "picture": "url",
            "itemid": 3,
        },
    ]
}

```

---

### Item detail

#### Approach

> GET http://placeholder.com/api/items/itemid

**Format**

```
{
    "name": "name",
    "price": 300,
    "pictures": [
        "url1",
        "url2",
        "url3"
    ],
    "description": "str",
    "itemid": "itemid"
}
```

---

### Search

#### Approach

> GET http://placeholder.com/api/items?q=searchterms

#### Format

_Same as item list_

---

### Buy item

#### Approach

_**Note:** Need some ways to authenticate the user_

> POST http://placeholder.com/api/items/itemid

#### Format

```
{
    "userid": "userid",
    "user_token": "token"
}
```

---

### Sell item

### Approach

_**Note:** Need some ways to authenticate the user_

_**Note:** Need to figure out how to upload photos_

> POST http://placeholder.com/api/items/sell

```
{
    "name": "name",
    "price": 300,
    "description": "str",
    "itemid": "itemid"
}
```

---

## Users

### User login

#### Approach

_**Note:** I don't know if this is secure_

> POST http://placeholder/api/user/login

#### Format

```
{
    "username": "username",
    "password": "password",
}
```

```
{
    "token": "token"
}
```

---

### User signup

#### Approach

> POST http://placeholder.com/api/user/signup

#### Format

```
{
    "username": "username",
    "password": "password",
    "school": "school",
    "age": 30
}
```

---

### Get user profile

#### Approach

> GET http://placeholder.com/api/user/userid
