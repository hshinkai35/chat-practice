$ ->
  ws = new WebSocket $("body").data("ws-url")

  ws.onmessage = (event) ->
    message = JSON.parse event.data
    console.table(message)
    console.log(message.user)
    console.log(message.chat)
    li = document.createElement('li')
    li.textContent = message.chat
    $("#chat").append(li)



  $("#addsymbolform").submit (event) ->
    event.preventDefault()
    # send the message to watch the stock
    ws.send(JSON.stringify({user: $("#username").val(), chat: $("#addchat").val()}))
    # reset the form
    $("#username").val("")
    $("#addchat").val("")