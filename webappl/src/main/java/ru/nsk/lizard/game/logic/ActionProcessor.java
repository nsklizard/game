package ru.nsk.lizard.game.logic;

import org.apache.log4j.Logger;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by dkim on 12.05.2015.
 */
public class ActionProcessor extends Thread{
    private static Logger log = Logger.getLogger(ActionProcessor.class);
    public ConcurrentLinkedQueue<FightJob> queue = new ConcurrentLinkedQueue<FightJob>();

    private static final ActionProcessor instance = new ActionProcessor();
    private ActionProcessor(){
    }
    public static ActionProcessor getInstance() {
        return instance;
    }

    @Override
    public void run() {
        log.info("Here we go -----------------------------> ");
        while(true) {
            FightJob job = null;
            while ((job = queue.poll()) != null) {
                job.execute();
            }

            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                log.error(e);
            }
        }
    }
}
