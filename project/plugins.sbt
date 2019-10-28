addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.2.1")

addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "1.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.4.1")

addSbtPlugin("io.spray" % "sbt-revolver" % "0.9.1")

addSbtPlugin("com.julianpeeters" % "sbt-avrohugger" % "2.0.0-RC19")

addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.5.0")

addSbtPlugin("io.kamon" % "sbt-kanela-runner" % "2.0.2")

libraryDependencies += "org.apache.avro" % "avro-tools" % "1.8.2"
