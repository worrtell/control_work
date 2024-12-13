package listener;

import config.DataSourceConfiguration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.flywaydb.core.Flyway;
import repository.ScheduleRepository;
import repository.impl.ScheduleRepositoryImpl;
import repository.mapper.ScheduleRowMapper;
import service.ScheduleService;
import service.ScheduleServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;


@WebListener
public class ContextListener implements ServletContextListener {
    private static final String URL = "jdbc:postgresql://localhost:5432/test_jdbs";
    private static final String LOGIN = "wortel";
    private static final String PASSWORD = "1563";
    @Override
    public void contextInitialized(ServletContextEvent sce) {


        Flyway flyway = Flyway.configure().baselineOnMigrate(true).dataSource(URL, LOGIN, PASSWORD).load();

        // Start the migration
        flyway.migrate();
        System.out.println("done");

        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DataSourceConfiguration configuration =
                new DataSourceConfiguration(properties);

        ScheduleRepository userRepository =
                new ScheduleRepositoryImpl(properties, configuration.hikariDataSource(), new ScheduleRowMapper());

        ScheduleService ScheduleService = new ScheduleServiceImpl(userRepository);

        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("userService", ScheduleService);
    }
}
