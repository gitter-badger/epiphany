package org.bitionaire.epiphany;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.bitionaire.epiphany.persistence.IdeasDAO;
import org.bitionaire.epiphany.resources.IdeasResource;
import org.flywaydb.core.Flyway;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.skife.jdbi.v2.DBI;


@Slf4j
public class EpiphanyApplication extends Application<EpiphanyConfiguration> {

    public static void main(final String... args) throws Exception {
        new EpiphanyApplication().run(args);
    }

    @Override
    public void run(final EpiphanyConfiguration configuration, final Environment environment) throws Exception {
        final DataSourceFactory database = configuration.getDatabase();
        this.executeDatabaseMigrations(database);

        // create DBI instance
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, database, "postgresql");

        // enable the linking feature of jersey
        environment.jersey().getResourceConfig().packages(getClass().getPackage().getName()).register(DeclarativeLinkingFeature.class);

        // register REST resources
        environment.jersey().register(new IdeasResource(jdbi.onDemand(IdeasDAO.class)));
    }

    private void executeDatabaseMigrations(final DataSourceFactory database) {
        final Flyway flyway = new Flyway();
        flyway.setDataSource(database.getUrl(), database.getUser(), database.getPassword());
        log.debug("execute database migrations");
        flyway.migrate();
        log.info("database migrations successfully executed");
    }
}
