package rs.sbnz.book_recommender.config;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.conf.MultithreadEvaluationOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.sbnz.book_recommender.services.SystemGradeService;

import java.util.Date;

@Configuration
public class EventSessionConfig {
    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private SystemGradeService systemGradeService;

    @Bean
    public KieBase getBase(){
        KieBaseConfiguration kieBaseConfiguration = KieServices.Factory.get().newKieBaseConfiguration();
        kieBaseConfiguration.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kieContainer.newKieBase(kieBaseConfiguration);

        return kieBase;
    }

    @Bean
    public KieSession eventSession(){
        Date date = new Date();
        KieSession session = getBase().newKieSession();
        session.insert(systemGradeService);
        session.insert(date);
        return session;
    }

    @Bean
    public KieSession userAlarmsSession(){
        KieSession session = getBase().newKieSession();

        return session;
    }

}
