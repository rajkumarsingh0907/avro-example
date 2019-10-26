Learn more about kafka schema registry - https://docs.confluent.io/current/schema-registry/schema_registry_tutorial.html

Sample Customer avro schema is present in src/main/resources/avro/customer.avsc

Dependency added for avro in pom.xml file is 

		<dependency>
			<groupId>org.apache.avro</groupId>
			<artifactId>avro</artifactId>
			<version>${avro.version}</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.apache.kafka/kafka -->
		<dependency>
			<groupId>org.apache.kafka</groupId>
			<artifactId>kafka-clients</artifactId>
			<version>${kafka.version}</version>
		</dependency>

		<dependency> 
			<groupId>io.confluent</groupId>
			<artifactId>kafka-avro-serializer</artifactId>
			<version>${confluent.version}</version>
		</dependency>
