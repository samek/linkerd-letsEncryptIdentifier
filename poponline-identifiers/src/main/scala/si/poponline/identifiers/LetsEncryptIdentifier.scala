package si.poponline.identifiers

import com.twitter.finagle.buoyant.Dst
import com.twitter.finagle.http.Request
import com.twitter.finagle.{Dtab, Path}
import com.twitter.util.Future
import io.buoyant.router.RoutingFactory
import io.buoyant.router.RoutingFactory.{IdentifiedRequest, RequestIdentification, UnidentifiedRequest}


case class LetsEncryptIdentifier(
  prefix: Path,
  servicename: String,
  baseDtab: () => Dtab = () => Dtab.base
) extends RoutingFactory.Identifier[Request] {

  private[this] def suffix(req: Request): Path =
    Path.read(req.path)

  private[this] def mkPath(path: Path): Dst.Path =
    Dst.Path(prefix ++ path, baseDtab(), Dtab.local)


  private[this] val MoveOn =
    Future.value(new UnidentifiedRequest[Request]("MoveOn to next identifier"))

  def apply(req: Request): Future[RequestIdentification[Request]] = {
    
    if (req.path.startsWith("/.well-known/acme-challenge/")) {
      val path = Path.Utf8(servicename)
      val dst = Dst.Path(prefix ++ path, baseDtab(), Dtab.local)
      new IdentifiedRequest(dst, req)
      Future.value(new IdentifiedRequest(dst, req))
    } else {
      MoveOn
    }
  }
}
