package de.pogozhev.keygeneratorservice.services;

import de.pogozhev.keygeneratorservice.KeyGeneratorServiceApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(classes = KeyGeneratorServiceApplication.class)
class KeyGeneratorServiceTest {
    @Autowired
    private KeyGeneratorService keyGeneratorService;
    @MockBean
    DataSource dataSource;

    @Test
    void testGenerateOnce() {
        String generate = keyGeneratorService.generate();
        Assertions.assertTrue(StringUtils.hasLength(generate));
    }

    @Test
    void testGenerateUnique() {
        String generate1 = keyGeneratorService.generate();
        Assertions.assertTrue(StringUtils.hasLength(generate1));
        String generate2 = keyGeneratorService.generate();
        Assertions.assertTrue(StringUtils.hasLength(generate2));
        Assertions.assertNotEquals(generate1, generate2);
    }
}
