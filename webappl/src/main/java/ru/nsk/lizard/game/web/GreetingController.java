package ru.nsk.lizard.game.web;

import ru.nsk.lizard.game.db.dao.GameMapDAO;
import ru.nsk.lizard.game.db.dao.UserDAO;
import ru.nsk.lizard.game.db.entities.Creature;
import ru.nsk.lizard.game.db.entities.Creatureconfig;
import ru.nsk.lizard.game.db.entities.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsk.lizard.game.db.dao.TestDAO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@RestController
public class GreetingController {
    @Autowired
    TestDAO testDAO;

    @Autowired
    GameMapDAO gameMapDAO;

    @Autowired
    UserDAO userDAO;

    @RequestMapping("/greeting")
    @Transactional
    public String greeting(@RequestParam(value="name"/*, defaultValue="World"*/) String name) {
        Test t = new Test();
        t.setValue(name);
        testDAO.update(t);

//        Set<Creature> creatureSet = userDAO.find(1L).getCreatures();
//        for (Creature creature : creatureSet){
//            List<Creatureconfig> creatureConfigs = creature.getCreatureConfigs();
//        }

        return name;
    }


}