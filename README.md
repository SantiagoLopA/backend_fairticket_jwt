Nombre completo: Santiago Lopez Angel
    Puerto de Hash: PasswordProtector
    Base Path: /api/gateway
    Claim JWT: claim_role
    Propiedades YAML:
    token.jwt.secret
    token.jwt.expiration

COMANDOS CURL 

    registro
        curl -X POST http://localhost:6001/api/gateway/register \
        -H "Content-Type: application/json" \
        -d '{
        "name": "Santiago",
        "email": "santiago@gmail.com",
        "password": "123456",
        "role": "ORGANIZER"
        }'

    login
        curl -X POST http://localhost:6001/api/gateway/login \
        -H "Content-Type: application/json" \
        -d '{
        "email": "santiago@gmail.com",
        "password": "123456"
        }'

    token access
        curl -X GET http://localhost:6001/api/users \
        -H "Authorization: Bearer JWT_TOKEN"
        
    access den 403
        curl -X POST http://localhost:6001/api/events \
        -H "Authorization: Bearer JWT_TOKEN" \
        -H "Content-Type: application/json" \
        -d '{
        "name": "Evento prueba"
        }'