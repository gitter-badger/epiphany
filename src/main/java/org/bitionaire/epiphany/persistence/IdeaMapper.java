package org.bitionaire.epiphany.persistence;

import org.bitionaire.epiphany.model.Idea;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IdeaMapper implements ResultSetMapper<Idea> {

    @Override
    public Idea map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new Idea(r.getString("name"));
    }
}
