package connector;

import exception.CustomException;
import io.vertx.core.Vertx;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;
import io.vertx.kafka.client.producer.RecordMetadata;
import pojo.MqConfig;
import request.MicroServiceCommonRequest;

import java.util.HashMap;
import java.util.Map;

public class KafkaMqConnector implements IConnectMq {

    private KafkaProducer<String, String> producer;
    private MqConfig mqConfig;
    private Vertx vertx;

    public KafkaMqConnector(MqConfig mqConfig, Vertx vertx) {
        this.mqConfig = mqConfig;
        this.vertx = vertx;
    }

    @Override
    public IConnectMq initConnection() {
        Map<String, String> config = new HashMap<>();
        config.put("bootstrap.servers", mqConfig.getHost() + ":" + mqConfig.getPort());
        config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("group.id", mqConfig.getVirtualHost());
        config.put("auto.offset.reset", "earliest");
        config.put("enable.auto.commit", "false");
        producer = KafkaProducer.create(vertx, config);
        return this;
    }

    @Override
    public IConnectMq declareQueue() {
        return null;
    }

    @Override
    public IConnectMq publish(MicroServiceCommonRequest request) {
        KafkaProducerRecord<String, String> record =
                KafkaProducerRecord.create(request.getQueue(), request.getPayload());
        producer.write(record, result ->{
            System.out.println("Send with kafka mq " + record.value());
            if (result.succeeded()) {
                RecordMetadata recordMetadata = result.result();
                System.out.println("Message " + record.value() + " written on topic=" + recordMetadata.getTopic() +
                        ", partition=" + recordMetadata.getPartition() +
                        ", offset=" + recordMetadata.getOffset());
            } else {
                throw new CustomException(result.cause().getMessage());
            }
        });
        return this;
    }
}
