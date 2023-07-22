package de.pogozhev.keygeneratorservice.repositories.impl;

import de.pogozhev.keygeneratorservice.repositories.KeyRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Repository
public class KeyGeneratorDao implements KeyRepository {
    private final JdbcTemplate jdbcTemplate;

    public KeyGeneratorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(String key) {
        jdbcTemplate.update("INSERT INTO unique_keys (unique_key) VALUES (?)",
                ps -> ps.setString(1, key)
        );
    }

    @Override
    public void saveAll(Set<String> keys) {
        jdbcTemplate.batchUpdate("INSERT INTO unique_keys (unique_key) VALUES (?)",
                keys,
                100,
                ((ps, argument) -> ps.setString(1, argument))
        );
    }

    @Override
    public List<String> getAlreadyStoredKeys(Set<String> keys) {
        String inSql = String.join(",", Collections.nCopies(keys.size(), "?"));

        return jdbcTemplate.query(String.format("SELECT * FROM unique_keys WHERE unique_key IN (%s)", inSql),
                keys.toArray(),
                ((ps, argument) -> ps.getString("unique_key")));
    }
}
