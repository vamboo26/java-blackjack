package com.codesquad.blackjack.socket.socketSessionController;

import com.codesquad.blackjack.domain.Game;
import com.codesquad.blackjack.dto.BettingDto;
import com.codesquad.blackjack.dto.ResultDto;
import com.codesquad.blackjack.service.MessageService;
import com.codesquad.blackjack.socket.GameSession;
import com.codesquad.blackjack.socket.SocketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitTableController implements TableController {

    @Autowired
    private MessageService messageService;

    @Override
    public void handleTurn(GameSession gameSession, Game game) {
        game.initializeGame();
        game.init(100); //칩 선택하게 하고 싶은데 일단 고정으로 해보자
        //베팅칩, 유저잔여칩 보여주기

        if (game.isBlackjack()) {
            //둘다 블랙잭이면 무승부
            //아니면 블랙잭이 승자, 게임 끝

            //게임끝내고, 상금배분로직
            //종료결과, 승자, 잔여칩 보여주기
            //둘의 카드 다 보여주기
            //버튼 모두 숨기기
            messageService.sendToAll(new SocketRequest<>("INIT", game._toGameDto()), gameSession);
            messageService.sendToAll(new ResultDto("BLACKJACK", game.end(game.getBlackjackPrize())), gameSession);
//            userRepository.save(game.getUser());
            return;
        }

        //블랙잭 없으면
        //딜러 1장, 유저 2장 보여주기
        //버튼 열어준다(보유칩에 따라 더블다운까지)
        messageService.sendToAll(new SocketRequest<>("INIT", game._toGameDto()), gameSession);

        if(game.hasGamerEnoughChip(100)) {
            messageService.sendToAll(new BettingDto("DOUBLE"), gameSession);
        } else {
            messageService.sendToAll(new BettingDto("NONE_DOUBLE"), gameSession);
        }
        //여기까지했으면 버튼 잘 열어주고 끝났어
        //이제 버튼 누르길 기다려야함
    }

}
