package org.bitionaire.epiphany.persistence;

import org.bitionaire.epiphany.model.Idea;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(IdeaMapper.class)
public interface IdeasDAO {

    @SqlQuery("SELECT name FROM ideas WHERE name = :name")
    Idea find(@Bind("id") final String id);
}
