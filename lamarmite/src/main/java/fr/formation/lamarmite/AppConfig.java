package fr.formation.lamarmite;

import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ImportResource("classpath:*-context.xml")
@ComponentScan(basePackages = { "fr.formation.lamarmite.repositories",
	"fr.formation.lamarmite.services",
	"fr.formation.lamarmite.components" })
@EnableJpaRepositories("fr.formation.lamarmite.repositories")
@EnableTransactionManagement
public class AppConfig {
    // Empty class
}
