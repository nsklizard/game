name := "scala1"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++={
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "com.typesafe.slick"  %% "slick"          % "3.0.3",
    "org.slf4j"           % "slf4j-nop"       % "1.6.4",
    "com.zaxxer"          % "HikariCP-java6"  % "2.3.2",
    "org.postgresql"      % "postgresql"      % "9.4-1201-jdbc41",
    "com.typesafe"        %   "config"        % "1.3.0",
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-json"    % "1.3.2",
    "io.spray"            %%  "spray-testkit" % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.specs2"          %%  "specs2-core"   % "2.3.11" % "test",
    "io.strongtyped"      %% "active-slick"   % "0.3.1"
  )
}

Revolver.settings