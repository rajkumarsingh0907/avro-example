package com.avro.examples;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import com.example.Customer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class KafkaAvroProducerExample {

	public static void main(String[] args) {

		Properties properties = new Properties();
		// normal producer
		properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
		properties.setProperty("acks", "all");
		properties.setProperty("retries", "10");
		// avro part
		properties.setProperty("key.serializer", StringSerializer.class.getName());
		properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
		properties.setProperty("schema.registry.url", "http://127.0.0.1:8081");

		Producer<String, Customer> producer = new KafkaProducer<>(properties);

		String topic = "demo";

		// copied from avro examples
		Customer customer = Customer.newBuilder()
							.setAge(21)
							.setAutomatedEmail(false)
							.setFirstName("John")
							.setLastName("Doe")
							.setHeight(178f)
							.setWeight(75f).build();

		for (int i = 0; i <= 10; i++) {
			ProducerRecord<String, Customer> producerRecord = new ProducerRecord<>(topic, customer);

			System.out.println(customer);
			producer.send(producerRecord, new Callback() {
				@Override
				public void onCompletion(RecordMetadata metadata, Exception exception) {
					if (exception == null) {
						System.out.println(metadata);
					} else {
						exception.printStackTrace();
					}
				}
			});
		}

		producer.flush();
		producer.close();

	}

}
