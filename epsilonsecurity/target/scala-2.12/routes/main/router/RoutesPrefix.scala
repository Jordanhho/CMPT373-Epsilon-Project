
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/alexpopov/Documents/School/373-Epsilon/epsilonsecurity/conf/routes
// @DATE:Thu Sep 14 20:59:31 PDT 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
