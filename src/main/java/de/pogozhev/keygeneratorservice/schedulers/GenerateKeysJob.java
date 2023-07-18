package de.pogozhev.keygeneratorservice.schedulers;

import de.pogozhev.keygeneratorservice.repositories.KeyRepository;
import de.pogozhev.keygeneratorservice.services.KeyGeneratorService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class GenerateKeysJob {
    private final KeyGeneratorService keyGeneratorService;
    private final KeyRepository keyRepository;

    public GenerateKeysJob(KeyGeneratorService keyGeneratorService, KeyRepository keyRepository) {
        this.keyGeneratorService = keyGeneratorService;
        this.keyRepository = keyRepository;
    }


    @Scheduled(cron = "* */1 * * * *")
    public void execute() {
        Set<String> generatedKeys = new HashSet<>();
        while (generatedKeys.size() < 1_000) {
            String key = keyGeneratorService.generate();
            generatedKeys.add(key);
        }

        keyRepository.saveAll(generatedKeys);
    }
}
