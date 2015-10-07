package helloworld

import slick.driver.PostgresDriver.api._

/**
 * Created by lizard on 15.09.2015.
 */
class Users(tag: Tag) extends Table[(Int, String, Long)](tag, "users") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc) // This is the primary key column
  def name = column[String]("name")
  def age = column[Long]("age")
  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, name, age)
}