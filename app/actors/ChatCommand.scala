package actors

/**
  * チャット用の命令クラス
  *
  */
sealed trait ChatCommand

/**
  * メッセージを受け取ったことを表すクラス
  */
case class ChatGetMassage(userId: String) extends ChatCommand