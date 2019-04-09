package cn.liuxiaokang.heroesapi.controller;

import cn.liuxiaokang.heroesapi.domain.Hero;
import cn.liuxiaokang.heroesapi.service.HeroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "${api.base-path}")
@Slf4j
public class HeroController {

    @Autowired
    private HeroService heroService;

    @GetMapping("/heroes/{id}")
    public Hero getHeroById(@PathVariable("id") Long id) {
        return heroService.getHeroById(id);
    }

    @GetMapping("/heroes")
    public List<Hero> getHeroes() {
        return heroService.getAllHeroes();
    }

    @GetMapping("/heroes/")
    public List<Hero> findHeroesByName(@RequestParam("name") String name) {
        return heroService.findHeroesByName(name);
    }

    @PostMapping("/heroes")
    public Hero addHero(@RequestBody Hero hero) {
        return heroService.saveHero(hero);
    }

    @PutMapping("/heroes")
    public Hero updateHero(@RequestBody Hero hero) {
        return heroService.saveHero(hero);
    }

    @DeleteMapping("/heroes/{id}")
    public void deleteHero(@PathVariable("id") Long id) {
        heroService.deleteHero(id);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, Object>> handlerDataAccessException(DataAccessException e) {
        log.error(e.getMessage(), e);
        Map<String, Object> body = new HashMap<>();
        body.put("message", e.getMessage());
        return ResponseEntity.badRequest().body(body);
    }
}
