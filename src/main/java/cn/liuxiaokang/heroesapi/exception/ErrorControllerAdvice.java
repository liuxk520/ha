package cn.liuxiaokang.heroesapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice(basePackages = {"cn.liuxiaokang.heroesapi.controller"})
@Slf4j
public class ErrorControllerAdvice {

    public ResponseEntity<ErrorMessage> handlerException(Exception e) {
        log.error(e.getMessage(), e);
        if (e instanceof DuplicateKeyException) {
            return handlerMessage("40001", e.getMessage());
        }

        return handlerMessage("40000", e.getMessage());
    }

    private ResponseEntity<ErrorMessage> handlerMessage(String code, String message) {
        return ResponseEntity.badRequest().body(new ErrorMessage(code, message));
    }
}
