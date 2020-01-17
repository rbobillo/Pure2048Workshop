import cats.effect.{ExitCode, IO, IOApp}

object Main extends IOApp {

  def run(av: List[String]): IO[ExitCode] =
    for {
      _ <- Gui.initPanelAndWaitForKeyPress(500, 500)
    } yield ExitCode.Success // ugly, should not be done that early

}
