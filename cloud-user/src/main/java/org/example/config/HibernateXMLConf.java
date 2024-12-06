package org.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateXMLConf {
    //jpa默认通过名称entityManagerFactory搜索sessionFactory
    @Bean(name = "entityManagerFactory")
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("org.example.model");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean("dataSource")
    public DataSource dataSource() {
        DruidDataSource source = new DruidDataSource();
        source.setUrl("jdbc:mysql://localhost:3306/sys?useUnicode=true&characterEncoding=UTF-8");
        source.setUsername("root");
        source.setPassword("bigdata");
        source.setMinIdle(1);
        source.setInitialSize(1);
        source.setMaxActive(10);
        return source;
    }

    @Bean("hibernateTransaction")
    public PlatformTransactionManager hibernateTransactionManager(DataSource dataSource) {
        //HibernateTransactionManager事务管理器
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory(dataSource).getObject());
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "none");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        hibernateProperties.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        //hibernateProperties.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringJtaSessionContext");
        //hibernateProperties.setProperty("hibernate.transaction.coordinator_class", "jta");
        return hibernateProperties;
    }

}
