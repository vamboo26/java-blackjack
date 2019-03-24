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

            let $dealer = $('div#dealer_box');
            let $user = $('div#user_box');

            if(message.type === 'JOIN') {
                $chat.append('<li>' + message.userName + '님이 입장했습니다.</li>')
            }

            if(message.type === 'CHAT') {
                $chat.append('<li>' + message.userName + ' : ' + message.message + '</li>')
            }

            if(message.type === 'INIT') {
                if(message.name === 'DEALER') {
                    $dealer.empty();

                    $dealer.append(' * ' + message.name + '의 카드 : ')

                    for (const key of Object.keys(message.cards)) {
                        if(key === 'cards') {
                            for (const secondKey of Object.keys(message.cards[key])) {
                                $dealer.append('(' + message.cards[key][secondKey].name + '/' + message.cards[key][secondKey].suit + ')')
                            }
                        }
                    }

                    $dealer.append('<br>전체 카드의 합은 ' + message.cards.total + '입니다. <br><br>')
                } else {
                    $user.empty();

                    $user.append(' * ' + message.name + '의 카드 : ')

                    for (const key of Object.keys(message.cards)) {
                        if(key === 'cards') {
                            for (const secondKey of Object.keys(message.cards[key])) {
                                $user.append('(' + message.cards[key][secondKey].name + '/' + message.cards[key][secondKey].suit + ')')
                            }
                        }
                    }

                    $user.append('<br>전체 카드의 합은 ' + message.cards.total + '입니다. <br><br>')
                }
            }

            if(message.type === 'RESULT') {
                $('#a').css('visibility','hidden');
                $('#b').css('visibility','hidden');
                $('#c').css('visibility','hidden');
                $('#btnContinue').css('visibility','visible');

                if(message.winner === 'TIE') {
                    $game.append(' * 무승부입니다.');
                    return;
                }

                if(message.status === 'BLACKJACK') {
                    $game.append(' * 블랙잭으로 ' + message.winner + '가 승리했습니다.');
                }

                if(message.status === 'BURST') {
                    $game.append(' * 상대 버스트로 ' + message.winner + '가 승리했습니다.');
                }

                if(message.status === 'NORMAL') {
                    $game.append(' * 합산결과 ' + message.winner + '가 승리했습니다.');
                }
            }

            if(message.type === 'BETTING') {
                $('#a').css('visibility','visible');
                $('#b').css('visibility','visible');
                if(event.status === 'DOUBLE') {
                    $('#c').css('visibility','visible');
                }
            }

            if(message.type === 'USERTURN') {
                socket.send('USERTURN');
            }

            if(message.type === 'DEALERTURN') {
                socket.send('DEALERTURN');
                $('#a').css('visibility','hidden');
                $('#b').css('visibility','hidden');
                $('#c').css('visibility','hidden');
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

$('#btnStart').on('click', function(evt) {
    evt.preventDefault();
    if (socket.readyState !== 1) return;

    $('#btnStart').css('visibility','hidden');
    socket.send('START GAME');
});

$('#a').on('click', function(evt) {
    evt.preventDefault();
    if (socket.readyState !== 1) return;

    socket.send('BETTING:1');
});

$('#b').on('click', function(evt) {
    evt.preventDefault();
    if (socket.readyState !== 1) return;

    socket.send('BETTING:2');
});

$('#c').on('click', function(evt) {
    evt.preventDefault();
    if (socket.readyState !== 1) return;

    socket.send('BETTING:3');
});