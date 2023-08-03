import org.apache.camel.builder.RouteBuilder;

public class SendEmail extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("kafka:signups?brokers=my-cluster-kafka-bootstrap:9092")
            .unmarshal().json()
            .log("Sending message to new customer: ${body[firstname]}\n")
            .log("To:${body[email]}\nSubject: Welcome to NewCompany\n" +
                "Hello ${body[firstname]}, we are so glad you joined us here at NewCompany");
            
    }
}