## Setup maven
mvn archetype:generate -DgroupId=com.anatwine.shopping -DartifactId=shopping-cart -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

## Build
```
mvn package
```

## Run
```
java -jar target/shopping-cart-1.0-SNAPSHOT.jar Milk Bread Apple Soup Soup
```

## Notes
- If config is malformed, then the application will fail to start
- Unknown items are logged and ignored from calculation
