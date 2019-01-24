# perfect-hangman
Hangman bot that will always make the "perfect" guess based on the English dictionary.

## How to run:

### Docker (Hub)
```
docker run -t -i okinskas/perfect-hangman:latest
```

### Docker Compose (Hub)
```
$ docker-compose run perfect-hangman
```

## How to build and run locally:

Clone this repository, then do any of the following:

### Docker
```
$ docker build -t perfect-hangman .
$ docker run -t -i perfect-hangman
```

### Maven via JAR

```
$ mvn clean install
$ java -jar target/perfect-hangman-1.0-SNAPSHOT.jar
```

### Maven via Java
```
$ mvn clean install
$ java -cp target/classes com.okinskas.application.Application
```
