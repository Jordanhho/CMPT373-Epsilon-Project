(function() {

    $(function() {
        var ws;
        ws = new WebSocket($("body").data("ws-url"));
        ws.onmessage = function(event) {
            print("Received: " + event);
        };
        return $("#addsymbolform").submit(function(event) {
              event.preventDefault();
              ws.send(JSON.stringify({
                symbol: $("#addsymboltext").val()
              }));
              ws.send("ping");
              return $("#addsymboltext").val("");
            });

    })

}).call(this);