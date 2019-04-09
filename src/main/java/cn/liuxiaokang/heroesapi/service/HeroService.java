package cn.liuxiaokang.heroesapi.service;

import cn.liuxiaokang.heroesapi.domain.Hero;
import cn.liuxiaokang.heroesapi.exception.HeroNotFoundException;
import cn.liuxiaokang.heroesapi.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HeroService {
    @Autowired
    private HeroRepository heroRepository;

    public Hero getHeroById(Long id) {
        return heroRepository.findById(id).orElseThrow(() -> new HeroNotFoundException(id));
    }

    public List<Hero> getAllHeroes() {
        return heroRepository.findAll();
    }

    public List<Hero> findHeroesByName(String name) {
        return heroRepository.findByName(name);
    }

    public Hero saveHero(Hero hero) {
        return heroRepository.save(hero);
    }

    public void deleteHero(Long id) {
        heroRepository.deleteById(id);
    }
}
