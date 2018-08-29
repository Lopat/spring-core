package com.andreitop.xml.config;

import com.andreitop.xml.mount.Mount;
import com.andreitop.xml.mount.Tiger;
import com.andreitop.xml.mount.Wolf;
import com.andreitop.xml.unit.Human;
import com.andreitop.xml.unit.Orc;
import com.andreitop.xml.unit.Troll;
import com.andreitop.xml.unit.Unit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.text.SimpleDateFormat;
import java.util.*;

@Configuration
@PropertySource("classpath:config/heroes.properties")
public class AppConfig {

    //<editor-fold desc="Heroes Context Rebuild">
    @Bean(name = "knight")
    public Unit knight() {
        return new Human(shadowTiger(), "thunderFury", "soulBlade");
    }

    @Bean(name = "trall")
    Unit trall() {
        Orc trall = new Orc(frostWolf());
        trall.setWeapon("furryAxe");
        trall.setColorCode(9);
        return trall;
    }
    //</editor-fold>


    //<editor-fold desc="Mount Context Rebuild">
    @Bean(name = "shadowTiger")
    public Mount shadowTiger() {
        return new Tiger();
    }


    @Bean(name = "frostWolf")
    Mount frostWolf() {
        return new Wolf();
    }
    //</editor-fold>

    //<editor-fold desc="Advanced Context Rebuild">

    @Bean(name = "dateFormatter")
    SimpleDateFormat dateFormatter() {
        return new SimpleDateFormat("dd-mm-yyyy");
    }

//    @Bean
//    PropertyPlaceholderConfigurer propConfig() {
//        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
//        configurer.setLocation(new ClassPathResource("config/heroes.properties"));
//        return configurer;
//    }

    @Bean(name = "trollMountMap")
    Map <String, Mount> trollMountMap() {
        Map <String, Mount> map = new HashMap <>();
        map.put("m1", frostWolf());
        map.put("m2", shadowTiger());
        return map;
    }

    @Bean
    Set <Mount> trollMountSet() {
        Set <Mount> trollMountSet = new LinkedHashSet <>();
        trollMountSet.add(shadowTiger());
        trollMountSet.add(frostWolf());
        return trollMountSet;
    }

    @Value("${character.created}")
    public Date created;


    @Bean(name = "zulJin")
    public Unit zulJin() {
        Troll zulJin = new Troll();
        zulJin.setColorCode(java.util.concurrent.ThreadLocalRandom.current()
                                                                  .nextInt(2, 9));
        zulJin.setCreationDate(created);
        zulJin.setListOfMounts(Arrays.asList(Troll.DEFAULT_MOUNT, null, shadowTiger()));
        zulJin.setSetOfMounts(trollMountSet());
        zulJin.setMapOfMounts(trollMountMap());
        return zulJin;
    }
    //</editor-fold>

}
