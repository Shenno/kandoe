package be.kdg.kandoe.frontend.config;

import be.kdg.kandoe.backend.config.BackendContextConfig;
import be.kdg.kandoe.frontend.config.security.WebSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {BackendContextConfig.class, WebSecurityConfig.class})
public class RootContextConfig {

}
