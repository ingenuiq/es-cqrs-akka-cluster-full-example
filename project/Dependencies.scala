import sbt._

object Dependencies {

  private[this] object Versions {
    val akka:                     String = "2.5.26"
    val akkaHttp:                 String = "10.1.10"
    val akkaHttpCors:             String = "0.4.1"
    val akkaPersistenceInmemory:  String = "2.5.15.2"
    val akkaPersistenceCassandra: String = "0.100"
    val akkaKryoVersion:          String = "1.0.0"
    val enumeratum:               String = "1.5.13"
    val logbackClassic:           String = "1.2.3"
    val playJson:                 String = "2.7.4"
    val pureConfig:               String = "0.12.1"
    val scalaLogging:             String = "3.9.2"
    val scalaTest:                String = "3.0.8"
    val akkaInMemory:             String = "2.5.1.1"
    val avro4s:                   String = "3.0.2"
    val slick:                    String = "3.3.2"
    val postgres:                 String = "42.2.8"
    val h2:                       String = "1.4.200"
    val classutil:                String = "1.5.1"
    val mockitoCore:              String = "3.1.0"
    val commonsCodec:             String = "1.13"
    val slf4jVersion:             String = "1.7.26"
    val akkaManagementVersion:    String = "1.0.3"
  }

  val all: Seq[ModuleID] = ProductionDependencies.values ++ TestDependencies.values

  private[this] object ProductionDependencies {

    val values
      : Seq[ModuleID] = akka ++ avro4s ++ playJson ++ pureConfig ++ logging ++ query ++ postgres ++ enumeratum ++ kamon ++ commonCodec

    private lazy val akka: Seq[ModuleID] = Seq(
      "com.typesafe.akka"             %% "akka-actor"                   % Versions.akka,
      "com.typesafe.akka"             %% "akka-cluster"                 % Versions.akka,
      "com.typesafe.akka"             %% "akka-cluster-sharding"        % Versions.akka,
      "com.typesafe.akka"             %% "akka-persistence-query"       % Versions.akka,
      "com.typesafe.akka"             %% "akka-http-core"               % Versions.akkaHttp,
      "com.typesafe.akka"             %% "akka-persistence-cassandra"   % Versions.akkaPersistenceCassandra,
      "ch.megard"                     %% "akka-http-cors"               % Versions.akkaHttpCors,
      "com.lightbend.akka.management" %% "akka-management"              % Versions.akkaManagementVersion,
      "com.lightbend.akka.management" %% "akka-management-cluster-http" % Versions.akkaManagementVersion,
      "com.typesafe.akka"             %% "akka-discovery"               % Versions.akka,
      "com.github.TanUkkii007"        %% "akka-cluster-custom-downing"  % "0.0.13",
      "io.altoo"                      %% "akka-kryo-serialization"      % Versions.akkaKryoVersion
    )

    private lazy val avro4s: Seq[ModuleID] = Seq("com.sksamuel.avro4s" %% "avro4s-core" % Versions.avro4s)

    private lazy val logging: Seq[ModuleID] = Seq(
      "ch.qos.logback"             % "logback-classic" % Versions.logbackClassic,
      "com.typesafe.scala-logging" %% "scala-logging"  % Versions.scalaLogging,
      "org.codehaus.janino"        % "janino"          % "3.1.0",
      "com.typesafe.akka"          %% "akka-slf4j"     % Versions.akka
    )

    private lazy val pureConfig: Seq[ModuleID] = Seq("com.github.pureconfig" %% "pureconfig" % Versions.pureConfig)
    private lazy val query: Seq[ModuleID] =
      Seq("com.typesafe.slick" %% "slick" % Versions.slick, "com.typesafe.slick" %% "slick-hikaricp" % Versions.slick)

    private lazy val enumeratum: Seq[ModuleID] = Seq("com.beachape" %% "enumeratum" % Versions.enumeratum)

    private lazy val postgres: Seq[ModuleID] = Seq("org.postgresql" % "postgresql" % Versions.postgres)

    private lazy val playJson: Seq[ModuleID] = Seq("com.typesafe.play" %% "play-json" % Versions.playJson)

    private lazy val kamon: Seq[ModuleID] = Seq("io.kamon" %% "kamon-bundle" % "2.0.3", "io.kamon" %% "kamon-apm-reporter" % "2.0.0")

    private lazy val commonCodec: Seq[ModuleID] = Seq("commons-codec" % "commons-codec" % Versions.commonsCodec)
  }

  private[this] object TestDependencies {

    private val TestAndITs = "test;it"

    lazy val values: Seq[ModuleID] =
      (akkaTest ++ scalaTest ++ otherDepsTest ++ embeddedCassandra ++ h2 ++ slickTest).map(_ % TestAndITs)

    private lazy val akkaTest: Seq[ModuleID] = Seq(
      "com.typesafe.akka"   %% "akka-testkit"              % Versions.akka,
      "com.typesafe.akka"   %% "akka-stream-testkit"       % Versions.akka,
      "com.typesafe.akka"   %% "akka-http-testkit"         % Versions.akkaHttp,
      "com.github.dnvriend" %% "akka-persistence-inmemory" % Versions.akkaPersistenceInmemory
    )

    private lazy val scalaTest: Seq[ModuleID] = Seq("org.scalatest" %% "scalatest" % Versions.scalaTest)

    private lazy val embeddedCassandra: Seq[ModuleID] = Seq(
      "com.typesafe.akka" %% "akka-persistence-cassandra-launcher" % Versions.akkaPersistenceCassandra
    )

    private lazy val h2: Seq[ModuleID] = Seq("com.h2database" % "h2" % Versions.h2)

    private lazy val otherDepsTest: Seq[ModuleID] =
      Seq("org.mockito" % "mockito-core" % Versions.mockitoCore, "org.clapper" %% "classutil" % Versions.classutil)

    private lazy val slickTest: Seq[ModuleID] = Seq("com.typesafe.slick" %% "slick-testkit" % Versions.slick)

  }
}
