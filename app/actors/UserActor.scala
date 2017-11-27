package actors

import akka.actor.{Actor, ActorRef, Props}
import play.api.libs.json.{JsValue, Json}

/**
  * WebSocket通信を行うためのアクター
  *
  */
object UserActor {
  def prop(out: ActorRef) = Props(classOf[UserActor], out)
}

class UserActor(out: ActorRef) extends Actor {


  /**
    * クライアントからのWebSocket通信を受け取るメソッド<br>
    * 現時点で処理はなし
    */
  override def receive = {
    case chat: ChatCommand => chatProcess(chat)
    case js: JsValue => out.tell(js, out)
    case _ =>
  }

  /**
    *
    * @param chat
    */
  private def chatProcess(chat: ChatCommand) = {
    chat match {
      case _: ChatGetMassage => out ! Json.obj("a" -> "メッセージきたよ", "b" -> "メッセージきたよ")
      case _ =>
    }
  }

  /**
    * WebSocketセッションが切断された際に実行されるメソッド
    */
  override def postStop() = {
    print("stop")
  }
}