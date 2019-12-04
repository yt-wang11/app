package com.example.facapp.common;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

/**
 * Created by jiangtao
 * Hibernate自动建表采用小写
 */
public class PhysicalNamingStrategy extends SpringPhysicalNamingStrategy {
    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        String tableName = super.toPhysicalTableName(name, jdbcEnvironment).getText();
        return Identifier.toIdentifier(tableName.toLowerCase());
    }
}
