package si.poponline.identifiers

import io.buoyant.linkerd.IdentifierInitializer


class LetsEncryptIdentifierInitializer extends IdentifierInitializer {
  override def configId: String = "si.poponline.LetsEncryptIdentifier"

  override def configClass: Class[_] = return classOf[LetsEncryptIdentifierConfig]
}
