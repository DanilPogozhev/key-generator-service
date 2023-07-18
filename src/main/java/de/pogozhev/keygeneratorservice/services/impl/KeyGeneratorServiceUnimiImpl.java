package de.pogozhev.keygeneratorservice.services.impl;

import de.pogozhev.keygeneratorservice.services.KeyGeneratorService;
import it.unimi.dsi.util.XoRoShiRo128PlusRandom;
import org.springframework.stereotype.Service;

@Service
public class KeyGeneratorServiceUnimiImpl implements KeyGeneratorService {
    private static final XoRoShiRo128PlusRandom GENERATOR = new XoRoShiRo128PlusRandom();
    private static final String BASE_64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-.";

    @Override
    public String generate() {
        StringBuilder result = new StringBuilder();
        while (result.length() != 6) {
            result.append(BASE_64.charAt(GENERATOR.nextInt(BASE_64.length())));
        }

        return result.toString();
    }
}
