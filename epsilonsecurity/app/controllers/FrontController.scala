package controllers

import java.io.File

import com.google.inject.Inject
import com.typesafe.config.{Config, ConfigFactory}
import lib.WebpackBuildFile
import play.Environment
import play.api.mvc._

class FrontController @Inject()(cc: ControllerComponents, env: Environment) extends AbstractController(cc) {
  val config: Config = ConfigFactory.parseFile(new File("conf/frontend.conf")).resolve()

<<<<<<< HEAD
  def index(path: String) = Action { request =>
=======
  def index(path: String) = Action {
//    new DummyDataBase
>>>>>>> a33e33382b80e7f13e134c1d843ca4323965dae4
    Ok(views.html.index.render(env, config.getInt("webpack.port"), WebpackBuildFile.jsBundle, WebpackBuildFile.cssBundle))
  }

}
