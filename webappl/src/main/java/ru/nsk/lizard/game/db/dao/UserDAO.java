package ru.nsk.lizard.game.db.dao;

import org.springframework.stereotype.Component;
import ru.nsk.lizard.game.db.entities.User;

/**
 * Created by dkim on 12.05.2015.
 */
@Component
public class UserDAO extends GenericDaoImpl<User> {
    public UserDAO() {
        super(User.class);
    }
}