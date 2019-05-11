var socket = null;
var userName = $('.content').data('user-name');
let $chat = $('div#chat_box');
let $dealer = $('div#dealer_box');
let $user = $('div#user_box');
let $result = $('div#result_box');

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
    socket = new SockJS("/game");

    socket.onopen = function () {
        console.log('Info: connection opened.');

        socket.onmessage = function (event) {
            console.log(event.data + '\n');

            var message = JSON.parse(event.data);
            console.log(message);

            var type = JSON.parse(event.data).type;
            var data = JSON.parse(event.data).request;

            if(type === 'INIT') {
                showDealerCards(data.dealer);
                showUserCards(data.user);
            }

            if(type === 'CHAT') {
                printChat(data);
            }

            if(type === 'JOIN') {
                $chat.append('<li>' + data.userName + '님이 입장했습니다.</li>')
            }

            if(type === 'SELECT') {
                if(data === 1) {
                    $('#c').css('visibility','hidden');
                } else {
                    $('#c').css('visibility','visible');
                }
            }

            if(type === 'BETTING') {
                //Hit again
                if(data === 1) {
                    $('#c').css('visibility','hidden');
                } else {
                    hideAllButtons();
                    socket.send('DEALERTURN');
                }
            }

            if(type === 'DEALERTURN') {
                hideAllButtons();
                socket.send('DEALERTURN');
            }



            /**
             * data.type === JOIN
             * 조인메세지 출력 (chat)
             *
             * data.type === CHAT
             * 채팅메세지 출력 (chat)
             *
             * data.type === INIT
             * 베팅 칩, user의 잔여 칩 출력 (result)
             * 핸드 출력 (dealer 1장, user 2장)
             *
             * data.type === END
             * 핸드 출력 (dealer, user 모든 카드)
             * 결과, 승자, user의 잔여 칩 출력 (result)
             *
             * data.type === SELECT
             * 픽 출력 (result)
             * 카드 추가 시 핸드 출력 (dealer, user)
             * 버튼 숨기기/보이기
             *
             */

            // if(message.type === 'JOIN') {
            //     $chat.append('<li>' + message.userName + '님이 입장했습니다.</li>')
            // }
            //
            // if(message.type === 'CHAT') {
            //     $chat.append('<li>' + message.userName + ' : ' + message.message + '</li>')
            // }

            if(message.type === 'RESULT') {
                $('#a').css('visibility','hidden');
                $('#b').css('visibility','hidden');
                $('#c').css('visibility','hidden');

                if(message.winner === 'TIE') {
                    $result.append(' * 무승부입니다.');
                    return;
                }

                if(message.status === 'BLACKJACK') {
                    $result.append(' * 블랙잭으로 ' + message.winner + '가 승리했습니다.');
                }

                if(message.status === 'BURST') {
                    $result.append(' * 상대 버스트로 ' + message.winner + '가 승리했습니다.');
                }

                if(message.status === 'NORMAL') {
                    $result.append(' * 합산결과 ' + message.winner + '가 승리했습니다.');
                }
            }

            if(message.type === 'BETTING') {
                $('#a').css('visibility','visible');
                $('#b').css('visibility','visible');
                if(event.status === 'DOUBLE') {
                    $('#c').css('visibility','visible');
                }
            }

            // if(message.type === 'USERTURN') {
            //     socket.send('USERTURN');
            // }
            //
            // if(message.type === 'DEALERTURN') {
            //     hideAllButtons();
            //     socket.send('DEALERTURN');
            // }
        };

        socket.onclose = function (event) {
            console.log('Info: connection closed.');
        };

        socket.onerror = function (err) {
            console.log('Error:', err);
        };
    };
}

$('#btnStart').on('click', function(evt) {
    evt.preventDefault();
    if (socket.readyState !== 1) return;

    $('#a').css('visibility','visible');
    $('#b').css('visibility','visible');
    $result.empty();

    socket.send('START');
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

function hideAllButtons() {
    $('#a').css('visibility','hidden');
    $('#b').css('visibility','hidden');
    $('#c').css('visibility','hidden');
}

function showDealerCards(dealer) {
    $dealer.empty();
    $dealer.append(' * 딜러의 카드 : ');

    for (const key of Object.keys(dealer.cards)) {
        if(key === 'cards') {
            for (const secondKey of Object.keys(dealer.cards[key])) {
                $dealer.append('(' + dealer.cards[key][secondKey].name + '/' + dealer.cards[key][secondKey].suit + ')');
            }
        }
    }

    $dealer.append('<br>전체 카드의 합은 ' + dealer.cards.total + '입니다. <br><br>');
}

function showUserCards(user) {
    $user.empty();
    $user.append(' * ' + user.name + '의 카드 : ')

    for (const key of Object.keys(user.cards)) {
        if(key === 'cards') {
            for (const secondKey of Object.keys(user.cards[key])) {
                $user.append('(' + user.cards[key][secondKey].name + '/' + user.cards[key][secondKey].suit + ')')
            }
        }
    }

    $user.append('<br>전체 카드의 합은 ' + user.cards.total + '입니다. <br><br>')
}

function printChat(request) {
    $chat.append('<li>' + request.userName + ' : ' + request.message + '</li>')
}
