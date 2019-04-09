package cn.liuxiaokang.heroesapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class HeroNotFoundException extends RuntimeException {

    public HeroNotFoundException(String message, Object ...args) {
        super(String.format(message,args));
    }

    public HeroNotFoundException(String name) {
        this("Could not find hero with name '%s'", name);
    }

    public HeroNotFoundException(Long id) {
        this("Could not find hero with id '%s'", id);
    }
}
