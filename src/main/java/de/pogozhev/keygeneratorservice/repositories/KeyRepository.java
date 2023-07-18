package de.pogozhev.keygeneratorservice.repositories;

import java.util.Set;

public interface KeyRepository {
    void save(String key);

    void saveAll(Set<String> keys);
}
