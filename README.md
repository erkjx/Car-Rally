# Car Rally
An application created for didactic purposes to improve skills in the field of Java, Spring-boot and relational databases.


I've used:
Java, Spring Boot, PostgreSql

## Description
An application addressed to the organizers of car rallies, which allows the registration of participants and their cars.
You can:
* register and login as user
* save your car.
* editind car date.
* delete car

In the future, the application will be expanded with such properties as: application status (accepted, considered, rejected) or the allocation of a parking space for a car.

Every user's data is stored in database, where are tables: users, roles, users_roles, car.
I used JWT for authorization user.

TODO:
* create tests
* validation for password
* more exception handlers
* data sorting

## Endpoints

### Auth

| Method | Url                      | Description | Example Valid Request Body | 
| ------ |--------------------------| ---------- | --------------------------- |
| POST   | /api/auth/register       | Sign up | [JSON](#register) |
| POST   | /api/auth/login          | Log in | [JSON](#login) |
| GET    | /api/auth/check/username | Check if username is available to register |[JSON](#checkusername) |
| GET    | /api/auth/check/email    | Check if email is available to register |[JSON](#checkemail) |


### Users

| Method | Url | Description | Example Valid Request Body |
|--------| --- | ----------- | ------------------------- |
| GET    | /api/users | get all users by admin | |
| GET    | /api/users/me/info | Get logged in user info | |
| GET    | /api/users/{username}/info | Get info about user by username | |
| GET    | /api/users/{username}/cars | Get cars created by user | |
| POST   | /api/users | Add user (Only for admin) | [JSON](#usercreate) |
| PUT    | /api/users/{username} | Update user (By admin) | [JSON](#userupdatebyadmin) |
| PUT    | /api/users/ | Update user (By logged in user) | [JSON](#userupdate) |
| PATCH  | /api/users/{username}/give/admin | Give admin role to user (only for admins) | |
| PATCH  | /api/users/{username}/take/admin | Take admin role from user (only for admins) | |
| PATCH  | /api/users/change/password | Update user password | [JSON](#userpasswordchange) |
| PATCH  | /api/users/change/email | Update user email | [JSON](#useremailchange) |
| DELETE | /api/users/ | Delete user (For logged in user)  | |
| DELETE | /api/users/{username} | Delete user (For admin) | |

### Cars

| Method | Url | Description                                        | Example Valid Request Body |
| ------ | --- |----------------------------------------------------| ------------------------- |
| GET    | /api/cars | Get all cars belong to log in user            | |
| GET    | /api/cars/{id} | Get cars belongs to log in user by car id | |
| POST   | /api/cars | Create new car                                | [JSON](#carcreate) |
| PUT    | /api/cars/{id} | Update car                                    | [JSON](#carupdate) |
| DELETE | /api/cars/{id} | Delete car                                    | |

##### <a id="register">Register -> /api/auth/register</a>
```json
{
  "username":"erkjx",
  "email":"erkjx@gmail.com",
  "password":"passwordErkjx10"
}
```
##### <a id="login">Log in : /api/auth/login</a>
```json
{
  "username": "erkjx",
  "password": "passwordErkjx10!"
}
```
##### Response Body
```json
{      "token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYXdpZDEyMyIsImlhdCI6MTY1MTAwMDczMSwiZXhwIjoxNjUxMDM2NzMxfQ.yLT8X2M6ub2q1Tc_Ifph_7Qs2tDzYLuQmzdCy0vXQO8UJv_wTHYqwqPebf7KSWB6RYerV8-ZqIv7s0EpoLMxWQ",
  "id": 1,
  "username": "erkjx",
  "email": "erkjx@gmail.com",
  "roles": [
      "ROLE_USER"
  ],
  "type": "Bearer"
}
```

##### <a id="checkusername">Check Username -> /api/auth/check/username</a>
```json
{
  "username": "randomusername"
}
```

##### <a id="checkemail">Check Email : /api/auth/check/email</a>
```json
{
  "email": "randomemail@onet.pl"
}
```

##### <a id="createuser">Create user : /api/users</a>
```json
{
  "email": "creteuser@gmail.com",
  "username": "creteusersname",
  "password": "creteuserspassword!"
}
```
##### <a id="userupdatebyadmin">Update user with username by admin : /api/users/{username}</a>
```json
{
  "email": "updateyserbyadmin@gmail.com",
  "username": "creteusersname",
  "password": "updateyserspasswordbyadmin"
}

```
##### <a id="userupdate">Update user : /api/users</a>
```json
{
  "email": "mychangedemail@gmail.com",
  "username": "myCHANGEDusername",
  "password": "myCHANGEDPassword"
}
```
##### <a id="changepassword">Change password : /api/users/change/password</a>
```json
{
  "password": "myCHANGEDPassword"
}
```

##### <a id="changeemail">Change Email : /api/users/change/email</a>
```json
{
  "email": "mychangedemail@wp.com"
}
```
##### <a id="carcreate">Create new car : /api/cars</a>
```json
{
    "brand":"Mercedes-Benz",
    "carmodel":"C-klasa",
    "productionyear": "2001"
}
```
##### <a id="carupdate">Update car : /api/cars/{id}</a>
```json
{
    "carmodel":"G-klasa",
    "productionyear": "2023"
}
```
