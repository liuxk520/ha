package cn.liuxiaokang.heroesapi;

import cn.liuxiaokang.heroesapi.domain.Hero;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HeroesApiApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void add_update_find_delete_hero() {
        Hero hero = new Hero();
        hero.setName("Jackr2");
        //add hero
        hero = testRestTemplate.postForObject("/api/heroes", hero, Hero.class);
        assertThat(hero.getId()).isNotNull();
        //update hero
        hero.setName("jacky");
        HttpEntity<Hero> requestEntity = new HttpEntity<>(hero);
        hero = testRestTemplate.exchange("/api/heroes", HttpMethod.PUT, requestEntity, Hero.class).getBody();
        assertThat(hero.getName()).isEqualTo("jacky");
        //find hero by name
        Map<String, String> map = new HashMap<>();
        map.put("name", "m");
        List<Hero> heroes = testRestTemplate.getForObject("/api/heroes/?name={name}", List.class, map);
        assertThat(heroes.size()).isEqualTo(2);
        //get hero by id
        hero = testRestTemplate.getForObject("/api/heroes/" + hero.getId(), Hero.class);
        assertThat(hero.getName()).isEqualTo("jacky");
        //delete hero successful
        ResponseEntity<String> response = testRestTemplate.exchange("/api/heroes/" + hero.getId(), HttpMethod.DELETE, null, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        //delete hero
        response = testRestTemplate.exchange("/api/heroes/9999", HttpMethod.DELETE, null, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

}
