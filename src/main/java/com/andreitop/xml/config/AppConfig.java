package com.andreitop.xml.config;

import com.andreitop.xml.mount.Mount;
import com.andreitop.xml.mount.Tiger;
import com.andreitop.xml.mount.Wolf;
import com.andreitop.xml.unit.Human;
import com.andreitop.xml.unit.Orc;
import com.andreitop.xml.unit.Unit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //<editor-fold desc="Heroes Context Rebuild">
    @Bean(name = "knight")
    public Unit knight() {
        return new Human(shadowTiger(), "soulBlade", "thunderFury");
    }

    @Bean(name = "trall")
    Unit trall(){
        Orc trall = new Orc(frostWolf());
        trall.setWeapon("furryAxe");
        trall.setColorCode(9);
        return trall;
    }
    //</editor-fold>

    @Bean(name = "shadowTiger")
    public Mount shadowTiger(){
        return new Tiger();
    }


    @Bean Mount frostWolf(){
        return new Wolf();
    }
}
