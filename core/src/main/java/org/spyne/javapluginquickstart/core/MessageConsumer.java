package org.spyne.javapluginquickstart.core;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spyne.javapluginquickstart.spi.Plugin;
import org.spyne.javapluginquickstart.spi.task.PluginFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class MessageConsumer {

    private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class.getSimpleName());

    private Map<String, PluginFactory> pluginMap; // pluginMap --> <"Bar", BarFactoryImpl>

    public MessageConsumer(Map<String, PluginFactory> pluginMap){
        this.pluginMap = pluginMap;
    }

    public void pollMessages() {
        log.info("I am a Kakfa Consumer");

        String bootstrapServers = "127.0.0.1:9092";
        String groupId = "java-plugin-quickstart-application";
        String topic = "java-plugin-quickstart";

        // 1. Create consumer configs
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // ConsumerConfig.AUTO_OFFSET_RESET_CONFIG
        // none - if no previous offsets are found then don't even start
        // earliest - read from the very beginning of the topic
        // latest - read only from the 'now' of the topic

        // 2. Create Consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // 3. Subscribe consumer to our topic(s)
        consumer.subscribe(Collections.singletonList(topic));

        // 4. Poll for new data
        while (true) {
            log.info("Polling");
            // Poll kafka and get as many records as you can now, but if there are no records,
            // wait 100 milliseconds to get records. If by 100 milliseconds no records have been received,
            // go to the next line of code, records will be an empty collection.
            // So, .poll either returns right away with records or waits up until timeout to return some records.
            ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(1000));

            for (ConsumerRecord<String, String> record : records){
                if (record.key().equalsIgnoreCase("Foo")){
                    this.pluginMap.get("foo").build().doTask();
                }
                if (record.key().equalsIgnoreCase("Bar")) {
                    this.pluginMap.get("bar").build().doTask();
                }

                log.info("Key: " + record.key() + ", Value: " + record.value());
                log.info("Partition: " + record.partition() + ", Offset: " + record.offset());
            }
        }
    }
}
