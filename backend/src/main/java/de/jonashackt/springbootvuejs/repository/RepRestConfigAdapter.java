package de.jonashackt.springbootvuejs.repository;


import de.jonashackt.springbootvuejs.domain.AppStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RepRestConfigAdapter implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(AppStatus.class);
    }

}
