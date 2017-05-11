package si.poponline.identifiers

import com.fasterxml.jackson.annotation.JsonIgnore
import com.twitter.finagle.http.Request
import com.twitter.finagle.{Dtab, Path}
import io.buoyant.linkerd.protocol.HttpIdentifierConfig
import io.buoyant.router.RoutingFactory.Identifier


class LetsEncryptIdentifierConfig extends HttpIdentifierConfig{
  /* This public member is populated by the json property of the same name. */
  var servicename: String = null

  @JsonIgnore
  override def newIdentifier(prefix: Path, baseDtab: () => Dtab): Identifier[Request] = {
    new LetsEncryptIdentifier(prefix, servicename, baseDtab)
  }
}
