package ru.nsk.lizard.game.logic;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by dmitr_000 on 17.05.2015.
 */
public class ActionProcessorLauncher implements InitializingBean {

    @Autowired
    ActionProcessor actionProcessor;

    @Override
    public void afterPropertiesSet() throws Exception {
        actionProcessor.start();

    }
}
