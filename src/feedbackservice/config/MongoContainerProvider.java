package feedbackservice.config;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.testcontainers.containers.MongoDBContainer;

import java.util.List;

@Component
public class MongoContainerProvider {

    private final MongoDBContainer container;

    public MongoContainerProvider(
            @Value("${mongodb.image}") String mongodbImage) {

        container = new MongoDBContainer(mongodbImage);

        container.withCreateContainerCmdModifier(
                cmd -> cmd.withName("feedback-service"));

        container.addEnv(
                "MONGO_INITDB_DATABASE",
                "feedback_db");

        container.setPortBindings(
                List.of("27017:27017"));

        System.out.println("Iniciando MongoDB...");

        container.start();

        System.out.println("MongoDB iniciado");
    }

    @PreDestroy
    public void tearDown() {
        container.stop();
    }
}