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

            if(message.type === 'INIT') {
                console.log(message);

                $game.append(' * ' + message.name + '의 카드 : ')

                for (const key of Object.keys(message.cards)) {
                    if(key === 'cards') {
                        for (const secondKey of Object.keys(message.cards[key])) {
                            $game.append('(' + message.cards[key][secondKey].name + '/' + message.cards[key][secondKey].suit + ')')
                        }
                    }
                }

                $game.append('<br>전체 카드의 합은 ' + message.cards.total + '입니다. <br><br>')
                                $('#btnContinue').css('visibility','visible');
            }

            if(message.type === 'BLACKJACK') {
                if(message.winner === 'TIE') {
                    $game.append(' * 양측 모두 블랙잭으로 무승부입니다.');
                } else {
                    $game.append(' * 블랙잭으로 ' + message.winner + '가 승리했습니다.');
                }

                $('#btnContinue').css('visibility','visible');
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

$('#btnContinue').on('click', function(evt) {
    evt.preventDefault();
    if (socket.readyState !== 1) return;

    $('#game_box').empty();
    socket.send('continue');
});