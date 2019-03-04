var socket = null;

$(document).ready( function() {
	connectSockJs();

    $('#btnSend').on('click', function(evt) {
        evt.preventDefault();
        if (socket.readyState !== 1) return;

        let msg = $('input#msg').val();
        socket.send(msg);
        $('input#msg').val("");
    });
});

function connectSockJs() {
    socket = new SockJS("/ws/chat");

    socket.onopen = function () {
        console.log('Info: connection opened.');

        socket.onmessage = function (event) {
            console.log(event.data + '\n');

            let $chat = $('div#chat_box');
            $chat.append('<li>' + event.data + '</li>')
        };

        socket.onclose = function (event) {
            console.log('Info: connection closed.');
        };

        socket.onerror = function (err) {
            console.log('Error:', err);
        };
    };
}