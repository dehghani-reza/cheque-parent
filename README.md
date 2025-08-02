# TEST CHEQUE

## JWT TOKEN
```bash
   curl -X POST http://localhost:8008/auth/login \
    -H "Content-Type: application/json" \
    -d '{
    "username": "teller1",
    "password": "123"
    }'
```

## JWT TOKEN + ISSUE CHEQUE:
```bash
   TOKEN=$(curl -X POST http://localhost:8008/auth/login \
    -H "Content-Type: application/json" \
    -d '{
    "username": "teller1",
    "password": "123"
    }')

   curl -X POST http://localhost:8008/api/cheques \
   -H "Content-Type: application/json" \
   -H "Authorization: Bearer $TOKEN" \
    -d '{
    "number": "DASDASDED/13121",
    "drawerId": "1",
    "amount": 1000,
    "issueDate": "2026-03-16",
    "payeeId": "1234567890"
    }'
```

## JWT TOKEN + PRESENT CHEQUE:
```bash
   TOKEN=$(curl -X POST http://localhost:8008/auth/login \
    -H "Content-Type: application/json" \
    -d '{
    "username": "teller1",
    "password": "123"
    }')

   curl -X POST http://localhost:8008/api/cheques/1/present \
   -H "Content-Type: application/json" \
   -H "Authorization: Bearer $TOKEN"
```

## Running steps
1. Optional: download maven wrapper which make project self mvn (i have already done and add to project)
```bash
  mvn -N io.takari:maven:wrapper
```
2. Build with mvn
```bash
  ./mvnw clean package
```
3. Build docker image
```bash
  docker build -t test-tata-service .
```
4. RUN the app
```bash
  docker run -p 8008:8008 test-tata-service
```
