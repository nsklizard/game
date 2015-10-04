package game

import akka.actor.Actor
import game.db.SkillsRepo
import spray.routing._

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class RestServiceActor extends Actor with RestService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}


// this trait defines our service behavior independently from the service actor
trait RestService extends HttpService{
  val myRoute={
    import game.RestAPI._
    import slick.driver.PostgresDriver.api._
    import scala.concurrent.ExecutionContext.Implicits.global






    path("addSkill"){
      post{
        val connectionUrl = "jdbc:postgresql://localhost/scala?user=postgres&password=root"
        val db = Database.forURL(connectionUrl, driver = "org.postgresql.Driver")
        entity(as[Types.Skill]){s=>
          complete{
            val skills: TableQuery[Tables.Skills] = TableQuery[Tables.Skills]
            db.run(skills+=s) onComplete {
              case Success(r) =>{
                "uploaded"
              }
            }
            "end"
          }
        }
      }
    }~path("getSkills"){
      get{
        val connectionUrl = "jdbc:postgresql://localhost/scala?user=postgres&password=root"
        val db = Database.forURL(connectionUrl, driver = "org.postgresql.Driver")

        val r = Await.result(db.run(SkillsRepo.fetchAll()),Duration.Inf)
        complete{
          r.toString
        }

      }
    }
  }
}
