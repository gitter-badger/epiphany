package org.bitionaire.epiphany;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class EpiphanyConfiguration extends Configuration {

    @Valid @NotNull
    @JsonProperty("database")
    @Getter private DataSourceFactory database = new DataSourceFactory();

}
