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

Create an application.yaml file in path: "src/main/resources/application/yaml"

- endpoint needs to be in origin format, and also not having the bucket name in his URL

```yaml
do:
  space:
    key: ""
    secret: ""
    endpoint: ""
    region: ""
    bucket: ""

server:
  address: "0.0.0.0"

spring:
  servlet:
    multipart:
      max-file-size: 50MB
      max-request-size: 100MB

```

