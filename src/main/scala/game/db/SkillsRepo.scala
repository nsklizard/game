package game.db

import io.strongtyped.active.slick.{CrudActions, ActiveRecord, EntityActions}
import io.strongtyped.active.slick.Lens._
import slick.ast.BaseTypedType
import slick.driver.PostgresDriver

/**
 * Created by lizard on 03.10.2015.
 */

case class Skill(name: String, fire: Long, water: Long, air: Long, earth: Long, ether: Long, id: Option[Long] = None)

object SkillsRepo extends EntityActions(PostgresDriver) {

  import jdbcProfile.api._

  val baseTypedType = implicitly[BaseTypedType[Id]] //

  type Entity = Skill
  type Id = Long
  type EntityTable = SkillTable

  val tableQuery = TableQuery[SkillTable] //

  def $id(table: SkillTable): Rep[Id] = table.id //

  val idLens = lens { skill: Skill => skill.id } { (skill, id) => skill.copy(id = id) }


  class SkillTable(tag: Tag) extends Table[Skill](tag, "skills") {

    def id = column[Id]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def fire = column[Long]("fire")

    def water = column[Long]("water")

    def air = column[Long]("air")

    def earth = column[Long]("earth")

    def ether = column[Long]("ether")

    def * = (name, fire, water, air, earth, ether, id.?) <>(Skill.tupled, Skill.unapply)
  }


  implicit class EntryExtensions(val entity: Skill) extends ActiveRecord[Skill] {
    override val repository: CrudActions = SkillsRepo
  }

}





