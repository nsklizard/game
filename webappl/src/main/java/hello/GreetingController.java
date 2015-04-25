package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsk.lizard.game.db.TestDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
public class GreetingController {
    @Autowired
    TestDAO testDAO;

    @RequestMapping("/greeting")
    @Transactional
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        Test t = new Test();
        t.setValue(name);
        testDAO.update(t);

        return name;
//        return new Greeting(counter.incrementAndGet(),
//                String.format(template, name));
    }
}