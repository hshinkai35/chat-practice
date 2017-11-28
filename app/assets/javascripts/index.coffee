$ ->
  ws = new WebSocket $("body").data("ws-url")

  userId = Math.random().toString(36).slice(-8)

  ws.onmessage = (event) ->
    message = JSON.parse event.data

    imgman = $("body").data("img-man")
    imgwoman = $("body").data("img-woman")

    if userId != message.userId
      chat = """<div class="chat">
         <figure class="chat-img-left">
         <img src="#{imgman}")">
         <figcaption class="chat-img-description">
         #{message.user}
         </figcaption>
         </figure>
         <div class="chat-text-right">
         <p class="chat-text">
         #{message.chat}
         </p>
         </div>
         </div>"""


    else
      chat = """<div class="chat">
         <figure class="chat-img-right">
         <img src="#{imgwoman}")">
         <figcaption class="chat-img-description">
         #{message.user}
         </figcaption>
         </figure>
         <div class="chat-text-left">
         <p class="chat-text">
         #{message.chat}
         </p>
         </div>
         </div>"""


    $("#chat")
      .append(chat)

  $("#addsymbolform").submit (event) ->
    event.preventDefault()
    # send the message to watch the stock
    ws.send(JSON.stringify({userId: userId, user: $("#username").val(), chat: $("#addchat").val()}))
    # reset the form
    $("#username").val("")
    $("#addchat").val("")