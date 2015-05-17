package ru.nsk.lizard.game.logic;

import org.springframework.beans.factory.InitializingBean;


/**
 * Created by dmitr_000 on 17.05.2015.
 */
public class ActionProcessorLauncher implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        ActionProcessor.getInstance().start();

    }
}
