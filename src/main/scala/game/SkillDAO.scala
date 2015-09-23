package game


import slick.driver.PostgresDriver.api._
import slick.jdbc.meta.MTable

import scala.util.{Success, Failure}

/**
 * Created by lizard on 23.09.2015.
 */
class SkillDAO {
  val connectionUrl = "jdbc:postgresql://localhost/scala?user=postgres&password=root"
  val db = Database.forURL(connectionUrl, driver = "org.postgresql.Driver")

  val skills: TableQuery[Tables.Skills] = TableQuery[Tables.Skills]

  def create(skill: Types.Skill) = {
//    db.run(
//      skills+=skill
//    ) onComplete {
//      case Failure( t) => // logging
//      case Success(res) => res
//    }
  }
}
