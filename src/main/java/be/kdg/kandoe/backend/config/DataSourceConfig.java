package be.kdg.kandoe.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * Responsible for creating the H2 Datasource for a Server Mode In-Memory Database
 * using ;DB_CLOSE_DELAY=-1 options to keep data for life-time of JVM process.
 */
@Configuration
public class DataSourceConfig
{
    @Value("classpath:/be/kdg/kandoe/backend/datasources/user.sql")
    private Resource user;

    @Value("classpath:/be/kdg/kandoe/backend/datasources/theme.sql")
    private Resource theme;

    @Value("classpath:/be/kdg/kandoe/backend/datasources/organisation.sql")
    private Resource organisation;

    @Value("classpath:/be/kdg/kandoe/backend/datasources/card.sql")
    private Resource card;

    @Value("classpath:/be/kdg/kandoe/backend/datasources/themeorg.sql")
    private Resource themeorg;

    @Value("classpath:/be/kdg/kandoe/backend/datasources/session.sql")
    private Resource session;

    @Value("classpath:/be/kdg/kandoe/backend/datasources/usersession.sql")
    private Resource usersession;

    @Value("classpath:/be/kdg/kandoe/backend/datasources/cardsession.sql")
    private Resource cardsession;

    // name of bean is superfluous in this case since method name is the same as bean name
    @Bean(name = "datasource")
    public DriverManagerDataSource datasource()
    {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");

        // datasource in-memory
        dataSource.setUrl("jdbc:h2:mem:kandoe;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");

        // datasource remote tcp server
        // in this case:also start H2 server from commandline with: java -cp h2*.jar org.h2.tools.Server
        // dataSource.setUrl("jdbc:h2:tcp://127.0.0.1:9092/mem:kandoe;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");

        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource)
    {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(user);
        populator.addScript(organisation);
        populator.addScript(theme);
        populator.addScript(card);
        populator.addScript(themeorg);
        populator.addScript(session);
        populator.addScript(usersession);
        populator.addScript(cardsession);
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(populator);
        return initializer;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
