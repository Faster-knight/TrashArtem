# Протестировать Артем

## Простетировать ApiRootController, UserController:

- /api/user/add POST запрос - должен добавлять пользователя в список UserService

### Принимает:

Headers:

1) Application-Header [String]: AppHeader

JSON
```json
{
  "id": 0, // Integer type
  "name": "name" // String type
}
```

### Возвращает:

1) Content-Type: application/json

Headers:

JSON
```json
{
  "data": true // Boolean type
}
```

- /api/user/delete POST запрос - должен удалить пользователя из
UserService

### Принимает:

Headers:

1) Application-Header [String]: AppHeader

JSON
```json
{
  "id": 0 // Integer type
}
```

### Возвращает:

1) Content-Type: application/json

Headers:

JSON
```json
{
  "data": true // Boolean type
}
```

- /api/check GET запрос - метод для проверки работы бекенд приложения
должен возвращать json

### Принимает

ничего

### Возвращает

JSON
```json
{
  "data": "Application work"
}
```

Headers:

1) Content-Type: application/json

- /api/user/all POST метод - получение списка всех пользователей

### Принимает

Headers:

1) Application-Header: AppHeader

JSON
```json
{
  // empity json
}
```

### Возвращает

Headers:

1) Content-Type: application/json

JSON
```json
{
  "data": {
    "0": {
      "name": "name",
      "contacts": {
        "contact1": "value"
        // more user id=0 contacts
      },
      "id": 0
    }
    // More users
  }
}
```

- /api/user/setName POST метод - установка имени пользователя по его id

### Принимает

Headers:

1) Application-Header: AppHeader

JSON
```json
{
  "id": 0, // Integer type
  "name": "name" // String type
}
```

### Возвращает

Headers:

1) Content-Type: application/json

JSON
```json
{
  "data": true // Boolean type
}
```

- /api/contact/add POST метод - добавить контакт пользователю по его id

### Принимает

Headers:

1) Application-Header: AppHeader

JSON
```json
{
  "id": 0, // Integer type
  "name": "name", // String type
  "value": "value" // String type
}
```

### Возвращает

Headers:

1) Content-Type: application/json

JSON
```json
{
  "data": true
}
```

- /api/contact/delete POST метод - удалить контакт пользователю по его id

тут в теле только айди юзера и имя контакта

- /api/contact/get POST метод - получить контакт пользователя по его id пользователя и имени

короче тут на входе в теле только айди юзера

Используй Postman пиши тесты DemoAppliationTest я запрятал еще пару скрытых ошибок связанные с User UserService