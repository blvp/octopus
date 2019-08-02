package octopus.async.cats

import scala.concurrent.Future
import _root_.cats.effect.IO
import monix.eval.Task
import monix.execution.Scheduler
import octopus.async.ToFuture

object ToFutureImplicits {

  implicit val catsIOToFuture: ToFuture[IO] = new ToFuture[IO] {
    def toFuture[A](value: IO[A]): Future[A] = value.unsafeToFuture()
  }

  implicit def monixTaskToFuture(implicit s: Scheduler): ToFuture[Task] = new ToFuture[Task] {
    def toFuture[A](value: Task[A]): Future[A] = value.runToFuture
  }
}
