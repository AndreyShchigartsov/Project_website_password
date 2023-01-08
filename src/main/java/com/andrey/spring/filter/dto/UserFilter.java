package com.andrey.spring.filter.dto;

import javax.persistence.Column;
import java.time.LocalDate;

public record UserFilter(String login,
                         String email,
                         LocalDate birthday) {
}
