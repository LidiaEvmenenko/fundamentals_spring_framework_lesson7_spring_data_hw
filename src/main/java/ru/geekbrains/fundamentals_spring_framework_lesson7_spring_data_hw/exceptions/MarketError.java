package ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class MarketError {
    private String message;
    private Date date;

    public MarketError(String message) {
        this.message = message;
        this.date = new Date();
    }
}
