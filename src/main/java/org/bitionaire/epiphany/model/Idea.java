package org.bitionaire.epiphany.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bitionaire.epiphany.resources.IdeasResource;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;

import java.io.Serializable;
import java.net.URI;

@ToString @EqualsAndHashCode
public class Idea implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter private final String name;

    @InjectLink(
            resource = IdeasResource.class,
            style = InjectLink.Style.ABSOLUTE,
            method = "get",
            bindings = @Binding(name = "name", value = "${instance.name}"),
            rel = "self"
    )
    @Getter private URI self;

    @JsonCreator
    public Idea(@JsonProperty("name") final String name) {
        this.name = name;
    }

}
