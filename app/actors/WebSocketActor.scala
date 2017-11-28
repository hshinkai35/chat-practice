package actors

import akka.actor.{Actor, ActorRef, Props}
import play.api.libs.json.{JsValue, Json}

/**
  * WebSocket通信を行うためのアクター
  *
  */
object WebSocketActor {
  def props(out: ActorRef) = Props(new WebSocketActor(out))
}

object Actors {
  var listActors = scala.collection.mutable.ArrayBuffer.empty[ActorRef]
}

class WebSocketActor(out: ActorRef) extends Actor {

  var actors = Actors;


  /**
    * クライアントからのWebSocket通信を受け取るメソッド
    */
  override def receive = {
    case js: JsValue => {
      actors.listActors.map(actor =>
        actor ! js
      )
    }
  }

  /**
    * WebSocketセッションが接続された際に実行されるメソッド
    */
  override def preStart(): Unit = {
    actors.listActors += out
    println("preStart")
  }

  /**
    * WebSocketセッションが切断された際に実行されるメソッド
    */
  override def postStop(): Unit = {
    actors.listActors -= out
    println("postStop")
  }

}