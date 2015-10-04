package game

/**
 * Created by lizard on 23.09.2015.
 */

import io.strongtyped.active.slick.{CrudActions, ActiveRecord, EntityActions}
import slick.ast.BaseTypedType
import slick.driver.PostgresDriver
import slick.driver.PostgresDriver.api._
import io.strongtyped.active.slick.Lens._
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by lizard on 15.09.2015.
 */

object Types {

  case class Skill(id: Option[Long],
                   name: String,
                   power: Long)

}


object Tables {

  class Skills(tag: Tag) extends Table[Types.Skill](tag, "skills") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def power = column[Long]("power")

    def * = (id.?, name, power) <>(Types.Skill.tupled, Types.Skill.unapply _)
  }


}
