package ru.nsk.lizard.game.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.nsk.lizard.game.common.GameConstants;
import ru.nsk.lizard.game.db.dao.GameMapDAO;

/**
 * Created by dmitr_000 on 19.05.2015.
 */
@Controller
public class GameMapController {

    @Autowired
    GameMapDAO gameMapDAO;

    @RequestMapping(value = "/showMap", method = RequestMethod.GET)
    public ModelAndView showMap(/*ModelMap modelMap*/){
        ModelAndView ret = new ModelAndView("map");
        ret.addObject(GameConstants.MAP_ATTR, gameMapDAO.getMap());
        return ret;
    }
}
