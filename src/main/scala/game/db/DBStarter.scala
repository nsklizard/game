package game.db

import slick.driver.PostgresDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success

/**
 * Created by lizard on 02.10.2015.
 */
object DBStarter extends App{
  val connectionUrl = "jdbc:postgresql://localhost/scala?user=postgres&password=root"
  val db = Database.forURL(connectionUrl, driver = "org.postgresql.Driver")

  db.run(PlayersRepo.fetchAll()) onComplete {
    case Success(result) =>{
      println(result)

      db.run(PlayersRepo.save(Player("test2", "test2", "test2")))
    }
  }

  Thread.sleep(5000);
}
