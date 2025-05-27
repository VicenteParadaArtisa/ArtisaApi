import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner; // Necesario si lo pones en el main
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean; // Necesario para @Bean

@SpringBootApplication
public class DemoApplication {

    @Value("${spring.data.mongodb.uri:NO_URI_FOUND}") // Inyecta la propiedad, si no existe usa "NO_URI_FOUND"
    private String mongoDbUri;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("----------------------------------------------------");
            System.out.println("MongoDB URI configurada: " + mongoDbUri);
            System.out.println("----------------------------------------------------");
        };
    }
}