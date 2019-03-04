var socket = null;
var userName = $('.content').data('user-name');

$(document).ready( function() {
	connectSockJs();

    $('#btnSend').on('click', function(evt) {
        evt.preventDefault();
        if (socket.readyState !== 1) return;

        let msg = $('input#msg').val();
        socket.send(JSON.stringify({type: 'CHAT', userName: userName, message: msg}));
        $('input#msg').val("");
    });
});

function connectSockJs() {
    socket = new SockJS("/ws/chat");

    socket.onopen = function () {
        console.log('Info: connection opened.');

        socket.onmessage = function (event) {
            console.log(event.data + '\n');

            var message = JSON.parse(event.data);
            console.log(message);

            let $chat = $('div#chat_box');
            let $game = $('div#game_box');

            if(message.type === 'JOIN') {
                $chat.append('<li>' + message.userName + '님이 입장했습니다.</li>')
            }
            if(message.type === 'CHAT') {
                $chat.append('<li>' + message.userName + ' : ' + message.message + '</li>')
            }
            if(message.type === 'INIT_GAME_INFO') {
                console.log(message);
                $game.append('<li>' + message.name + '의 카드 </li>')
                $game.append(message.cards.cards + '(카드상세정보) </br>')
                $game.append('전체 카드의 합은 ' + message.cards.total + '입니다. </br>')
            }
        };

        socket.onclose = function (event) {
            console.log('Info: connection closed.');
        };

        socket.onerror = function (err) {
            console.log('Error:', err);
        };
    };
}