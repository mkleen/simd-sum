./mvnw clean
./mvnw -T 1C package -DskipTests=true
java -XX:-UseSuperWord --add-modules jdk.incubator.vector -jar target/benchmarks.jar
