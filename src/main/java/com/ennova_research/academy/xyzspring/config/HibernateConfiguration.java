package com.ennova_research.academy.xyzspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ennova_research.academy.xyzspring.dao.model.BoUser;
import com.ennova_research.academy.xyzspring.dao.model.Course;
import com.ennova_research.academy.xyzspring.dao.model.Partecipant;
import com.ennova_research.academy.xyzspring.dao.model.RegisteredUser;

/**
 * @author Alberto Ielpo
 */
@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {
    
	@Autowired
    private ApplicationContext context;
	
    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"));
        factoryBean.setAnnotatedClasses(BoUser.class);
        factoryBean.setAnnotatedClasses(Course.class);
        factoryBean.setAnnotatedClasses(Partecipant.class);
        factoryBean.setAnnotatedClasses(RegisteredUser.class);
        return factoryBean;
    }
    
    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
