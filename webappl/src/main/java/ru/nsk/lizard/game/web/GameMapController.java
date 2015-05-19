package ru.nsk.lizard.game.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dmitr_000 on 19.05.2015.
 */
@Controller
public class GameMapController {

    @RequestMapping(value = "/showMap", method = RequestMethod.GET)
    public ModelAndView showMap(ModelMap modelMap){
        modelMap.addAttribute("message", "Map generated");
        return new ModelAndView("map");
    }
}
