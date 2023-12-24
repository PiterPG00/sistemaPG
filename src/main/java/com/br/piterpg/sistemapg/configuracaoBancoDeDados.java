package com.br.piterpg.sistemapg;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class configuracaoBancoDeDados {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver"); //declara as configurações de acesso
//        dataSource.setUrl("jdbc:postgresql://ec2-44-215-22-37.compute-1.amazonaws.com:5432/d7uh4n3n1emtbh");
//        dataSource.setUsername("xybkekxiceztwz");//usuario
//        dataSource.setPassword("cc7d2d40994bb3aa88d6c4484ea79809724d50cb5b21d4625d1217f0d23e5ec6");//senha
        dataSource.setUrl("jdbc:postgresql://localhost:5432/sistemapg");
        dataSource.setUsername("postgres");//usuario
        dataSource.setPassword("postgres");//senha
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.POSTGRESQL); //driver do banco
        adapter.setShowSql(true); //mostrar no console o sql, é interessante
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
        adapter.setPrepareConnection(true);
        return adapter;
    }
}