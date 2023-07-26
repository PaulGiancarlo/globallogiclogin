# Globallogiclogin
This is a global logic challenge

1.Follow this steps for buil:
```
./gradlew clean
./gradlew build
./gradlew bootRun
```
Default port:8080

2. Test:
   
  ./gradlew test
  
4. Try it with postman
Request:
```json
{
    "name": "Paul Diaz",
    "email": "paul@gmail.com",
    "password": "String",
    "phones": [
        {
            "number": 789456132,
            "citycode": 56,
            "contrycode": "CL"
        }
    ]
}
```

Response:
```json
{
    "id": "48c44ea5-4a05-4878-bfd5-d579579150f7",
    "created": 1689954621879,
    "lastLogin": 1689954621879,
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiI0OGM0NGVhNS00YTA1LTQ4NzgtYmZkNS1kNTc5NTc5MTUwZjciLCJpYXQiOjE2ODk5NTQ2MjEsImV4cCI6MTY4OTk1NDY0M30.5O7kl0xgR4-UrEmBRyKeOpLMj1NgvJxa_NQCP_qbGnE",
    "isActive": true
}
```
