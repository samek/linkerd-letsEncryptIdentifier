# LetsEncrypt Identifier

LetsEncrypt identifier routes all traffic which comes from letsencrypt to a service defined at
 servicename in the config. 
 Curent build is of 0.9.1!

## Building

This plugin is built with sbt.  Run sbt from the plugins directory.

```
./sbt poponlineIdentifiers/assembly
```

This will produce the plugin jar at
`poponline-identifiers/target/scala-2.11/poponline-identifiers-assembly-0.1.jar`.

## Installing

To install this plugin with linkerd, simply move the plugin jar into linkerd's
plugin directory (`$L5D_HOME/plugins`).  Then add a classifier block to the
router in your linkerd config:

```
routers:
- protocol: http
  label: incoming
  
  identifier:
    - kind: si.poponline.LetsEncryptIdentifier
      servicename: letsEncryptService
    - kind: io.l5d.header.token
  interpreter:
    kind: io.l5d.namerd
    dst: /$/inet/127.0.0.1/4100
  servers:
  - port: 4140
    ip: 0.0.0.0
telemetry:
- kind: io.l5d.prometheus

```
