import java.io.File
import java.net.InetSocketAddress

import play.sbt.PlayRunHook
import sbt._

object WebpackServer {
  def apply(base: File): PlayRunHook = {
    object WebpackServerScript extends PlayRunHook {

      var process: Option[Process] = None
      val isWin: Boolean = System.getProperty("os.name").toUpperCase().indexOf("WIN") >= 0

      override def afterStarted(add: InetSocketAddress): Unit = {
        process = if (isWin) {
          Option(Process(s"cmd /c npm run dev", base).run)
        } else {
          Option(Process(s"npm run dev", base).run)
        }
      }
      override def afterStopped(): Unit = {
        process.foreach(p => { p.destroy() })
        process = None
      }
    }

    WebpackServerScript
  }
}