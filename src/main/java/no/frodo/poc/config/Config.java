package no.frodo.poc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class Config {

    @Value("${app.datasource.driverClassName}")
    String driverClassName;
    @Value("${app.datasource.url}")
    String url;
    @Value("${app.datasource.username}")
    String username;
    @Value("${app.datasource.password}")
    String password;

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DataSource dataSource = DataSourceBuilder
                .create()
                .username(username)
                .password(password)
                .url(url)
                .driverClassName(driverClassName)
                .build();
        return dataSource;
    }
}
