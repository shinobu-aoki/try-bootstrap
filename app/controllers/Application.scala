package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import java.util.{Calendar, Date, Random}
import models.User

object Application extends Controller {
  
  val userForm = Form(
    mapping(
      "id" -> longNumber,
      "name" -> nonEmptyText,
      "birth" -> date("yyyy/MM/dd")
    )(User.apply)(User.unapply)
  )
  
  def index = Action {
    Redirect(routes.Application.list)
  }
  
  def list = Action {
    val r = new Random(System.nanoTime())
    val cal = Calendar.getInstance()
    cal.set(1990, 0, 1, 0, 0, 0)
    val users = (0 until 20) map { i: Int =>
      cal.set(Calendar.MONTH, r.nextInt(12))
      cal.set(Calendar.DATE, r.nextInt(28) + 1)
      cal.set(Calendar.YEAR, 1970 + r.nextInt(30))
      User(1001 + i, "Name-" + i, cal.getTime())
    }
    Ok(views.html.list(users.toList))
  }
  
  def form = Action {
    Ok(views.html.form(userForm))
  }
  
  def nobsForm = Action {
    Ok(views.html.nobs_form(userForm))
  }
  
}