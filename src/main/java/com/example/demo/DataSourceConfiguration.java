package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.from")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Qualifier("write")
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.to")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public NamedParameterJdbcTemplate readJDBCTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate writeJDBCTemplate(@Qualifier("write") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }


}
