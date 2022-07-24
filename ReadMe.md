# Bill Payment System

- RestApi's that receive and save a user information, save a billing information and query this information.
- Bill inquiry is made with bill Id and user number.
- 2 services that perform deletion operations with id information for the created customer record and bill record.
- An bill record is created and the record can be queried.
- A service that updates customer information.
- Services return a response of type ResponseEntity.

## Home Path
- GET http://localhost:8080/

## User Path Operations

- POST: http://localhost:8080/users  ==> User addition operation.
- GET:  http://localhost:8080/users  ==> List of all users.
- GET: http://localhost:8080/users/{{userId}}  ==> User query operation with user id.
- PUT http://localhost:8080/users/{{userId}}  ==> User update operation with user id.
- DELETE http://localhost:8080/users/{{userId}}  ==> User delete operation with user id.

## Bill Path Operations
- POST: http://localhost:8080/bills  ==> Bill addition operation.
- GET: http://localhost:8080/bills  ==> List of all bills.
- GET: http://localhost:8080/bills/{{billId}}  ==>  Bill query operation with bill id.
- GET: http://localhost:8080/bills/user/{{userid}}  ==> Bill query operation with user id.
- PUT: http://localhost:8080/bills/{{billId}}  ==> Bill update operation with bill id.
- DELETE: http://localhost:8080/bills/{{billId}}  ==>  Bill delete operation with bill id.
- DELETE: http://localhost:8080/bills/user/{{userid}}  ==>  Bill delete operation with user id.

## Payment Path Operations
- POST: http://localhost:8080/payments  ==> Payment addition operation.
- GET: http://localhost:8080/payments  ==> List of all payments.
- GET: http://localhost:8080/payments/{{paymentId}}  ==> Payment query operation with payment id.
- PUT: http://localhost:8080/payments/{{paymentId}}  ==> Payment update operation with payment id.
- DELETE: http://localhost:8080/payments/{{paymentId}}  ==> Payment delete operation with payment id.