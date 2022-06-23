package ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice//глобальный перехватчик, будет добавлен  во все контроллеры
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> catchResourceNotFoundException(ResourceNotFoundException e){
        return new ResponseEntity<>(new MarketError(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
