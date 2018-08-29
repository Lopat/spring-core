package com.andreitop.xml.config;

import com.andreitop.xml.mount.Mount;
import com.andreitop.xml.mount.Tiger;
import com.andreitop.xml.mount.Wolf;
import com.andreitop.xml.unit.Human;
import com.andreitop.xml.unit.Orc;
import com.andreitop.xml.unit.Troll;
import com.andreitop.xml.unit.Unit;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.*;

@Configuration
public class AppConfig {

    //<editor-fold desc="Heroes Context Rebuild">
    @Bean(name = "knight")
    public Unit knight() {
        return new Human(shadowTiger(), "thunderFury", "soulBlade");
    }

    @Bean(name = "trall")
    Unit trall(){
        Orc trall = new Orc(frostWolf());
        trall.setWeapon("furryAxe");
        trall.setColorCode(9);
        return trall;
    }
    //</editor-fold>


    //<editor-fold desc="Mount Context Rebuild">
    @Bean(name = "shadowTiger")
    public Mount shadowTiger(){
        return new Tiger();
    }


    @Bean(name = "frostWolf")
    Mount frostWolf(){
        return new Wolf();
    }
    //</editor-fold>

    //<editor-fold desc="Advanced Context Rebuild">
    @Bean(name = "dateFormatter")
    SimpleDateFormat dateFormatter(){
//        <constructor-arg value="dd-mm-yyyy"/>
        // TODO: 28.08.2018
        return new SimpleDateFormat();
    }

    @Bean
    PropertyPlaceholderConfigurer propConfig(){
//        <property name="locations" value="classpath:config/heroes.properties"/>
        // TODO: 28.08.2018
        return new PropertyPlaceholderConfigurer();
    }

    @Bean(name = "trollMountMap")
    Map<String, Mount> trollMountMap(){
        Map<String, Mount> map = new HashMap <>();
        map.put("m1", frostWolf());
        map.put("m2", shadowTiger());
        return map;
    }

    @Bean
    Set<Mount> trollMountSet(){
        Set<Mount> trollMountSet = new LinkedHashSet<>();
        trollMountSet.add(shadowTiger());
        trollMountSet.add(frostWolf());
        return trollMountSet;
    }

    @Bean(name = "zulJin")
    public Unit zulJin(){
        Troll zulJin = new Troll();
        zulJin.setColorCode(java.util.concurrent.ThreadLocalRandom.current().nextInt(1, 10));
        zulJin.setCreationDate(new Date());
        zulJin.setListOfMounts(Arrays.asList(Troll.DEFAULT_MOUNT, null, shadowTiger()));
        zulJin.setSetOfMounts(trollMountSet());
        zulJin.setMapOfMounts(trollMountMap());
        return zulJin;
    }

    //</editor-fold>

}
