1. Start application with `mvn spring-boot:run`
2. Call application with `curl "localhost:8080?premiumRoomsNumber=3&economyRoomsNumber=3"`

To change guest prices start application with:
`mvn spring-boot:run -Dspring-boot.run.arguments=--guest-price=50,100,200`