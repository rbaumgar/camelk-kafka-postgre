// camel-k: dependency=camel:jdbc
// camel-k: dependency=mvn:io.quarkus:quarkus-jdbc-postgresql

import org.apache.camel.builder.RouteBuilder;

public class SaveCustomer extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("kafka:signups?brokers=my-cluster-kafka-bootstrap:9092")
            .unmarshal().json()
            .log("Saving new customer: ${body[firstname]} ${body[lastname]} with email: ${body[email]}")
            .setBody(simple("INSERT INTO customer (firstname, lastname, email) VALUES ('${body[firstname]}', '${body[lastname]}', '${body[email]}')"))
            .to("jdbc:default")
            .to("log:info");
    }
}