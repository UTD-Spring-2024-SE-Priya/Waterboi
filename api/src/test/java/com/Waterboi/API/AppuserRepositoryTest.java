package com.Waterboi.API;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import com.Waterboi.API.repository.AppuserRepository;
import com.Waterboi.API.entity.Appuser;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AppuserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppuserRepository appuserRepository;

    @Test
    void findByUsernameIgnoreCaseGoodPath() {
        Appuser appuser = new Appuser("username", "password");
        entityManager.persist(appuser);
        entityManager.flush();

        Appuser found = appuserRepository.findByUsernameIgnoreCase(appuser.getUsername()).orElseThrow();
        assertThat(found.getUsername()).isEqualTo(appuser.getUsername());
    }
}
