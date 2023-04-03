# API

## Commands

To run the web server in a Docker container (doesnt support hot reload since it compiles the jar in the image build)

```
docker build -t eif-app/spring-boot-api .
docker run -p 8080:8080 eif-app/spring-boot-api
```

## Config

Add the following VM option in dev environment and possibly also in production

```
-Djsse.enableSNIExtension=false
```

Create an .env file in root of the project with following env vars

- endpoint needs to be in origin format, and also not having the bucket name in his URL

```
SPACE_KEY=""
SPACE_SECRET=""
SPACE_ENDPOINT=""
SPACE_REGION=""
SPACE_BUCKET=""

CROSS_ORIGIN_ENDPOINT="http://localhost:4200"
```

After that, to run the API with the current variables, run the following commands:

1. Export the env vars to the current environment (terminal) 
    ```
    export $(cat .env | xargs)
    ```
2. Run the project using Maven
    ```
    mvn spring-boot:run
    ```