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
