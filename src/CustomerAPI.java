import org.apache.camel.builder.RouteBuilder;

public class CustomerAPI extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        
        rest("/")
            .post("/newCustomer")
                .consumes("application/json")
                .to("direct:sendToKafka");

        from("direct:sendToKafka")
                .log("${body}")
                .log("Sending message to kafka")
                .to("kafka:signups?brokers=my-cluster-kafka-bootstrap:9092");
        
    }
}