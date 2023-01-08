package com.andrey.spring.database.entity;

import java.io.Serializable;

public interface BaseEntity<T extends Serializable>{//Этот интерфейс помагает на создании других
    // уровней,Service,Repository. Потому что мы можем итерировать одной единственной моделю.

    T getId();

    void setId(T id);
}
