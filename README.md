# SOS-Bicho
A simple spring boot app for CF deployment

## Building
`./mvnw clean package` 

## Running
`./mvnw spring-boot:run`

or

`java -jar target/sos-bicho-0.0.1-SNAPSHOT.jar`

Open app on http://localhost:8080

## Deploying
`cf push -p target/sos-bicho-0.0.1-SNAPSHOT.jar`


<kbd>![sos-bicho](https://i.imgur.com/cUrqOAk.png)</kbd>
