import scalariform.formatter.preferences._

name := "Imperative2048"

version := "0.0.1"

assemblyJarName in assembly := s"${name.value}-${version.value}.jar"

scalaVersion := "2.13.1"

scalacOptions ++= Seq(
  "-deprecation"
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.8"  % "test"
)

//================================================================================
// Scalariform configurations
//================================================================================
scalariformPreferences := scalariformPreferences.value
  .setPreference(AlignArguments, true)
  .setPreference(AlignParameters, true) // seems to be buggy
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 80)
  .setPreference(AllowParamGroupsOnNewlines, true)
  .setPreference(DanglingCloseParenthesis, Prevent)
  .setPreference(DoubleIndentConstructorArguments, true)
  .setPreference(DoubleIndentMethodDeclaration, true)
  .setPreference(FirstParameterOnNewline, Preserve)
  .setPreference(FirstArgumentOnNewline, Preserve)
  .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, true)
  .setPreference(PreserveSpaceBeforeArguments, true)
  .setPreference(SingleCasePatternOnNewline, false)
  .setPreference(SpaceBeforeContextColon, true)
