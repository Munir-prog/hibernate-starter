package com.mprog;

import com.mprog.entity.User;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.*;
import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.*;

class HibernateRunnerTest {

    @Test
    void checkReflectionApi() throws SQLException, IllegalAccessException {
        var user = User.builder()
                .username("ivan@gmail.com")
                .firstName("Ivan")
                .lastname("Ivanov")
                .birthday(LocalDate.of(2001, 11, 6))
                .age(20)
                .build();

        String sql = """
                insert
                into
                %s
                (%s)
                values
                (%s)
                """;
        var tableName = ofNullable(user.getClass().getAnnotation(Table.class))
                .map(tableAnnotation -> tableAnnotation.schema() + "." + tableAnnotation.name())
                .orElse(user.getClass().getName());
        if (tableName.startsWith(".")){
            tableName = tableName.substring(1);
        }

        var declaredFields = user.getClass().getDeclaredFields();

        var columnNames = Arrays.stream(declaredFields)
                .map(field -> ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName()))
                .collect(joining(", "));

        var columnValues = Arrays.stream(declaredFields)
                .map(field -> "?")
                .collect(joining(", "));

        var sqlString = sql.formatted(tableName, columnNames, columnValues);
        System.out.println(sqlString);

        Connection connection = null;
        var preparedStatement = connection.prepareStatement(sqlString);

        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            preparedStatement.setObject(1, declaredField.get(user));
        }

    }
}