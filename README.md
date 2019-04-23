# 블랙잭(카드게임) 구현
`branch : zingoworks`  
*페어 프로그래밍 종료 이후 프로젝트를 지속적으로 개선해나가기 위한 브랜치입니다.*

---

> OOP, TDD, Clean Code, Pair Coding에 주안점을 두고,  
콘솔 및 웹 기반으로 블랙잭 게임을 구현해본다.

![진행방법](https://github.com/zingoworks/java-blackjack/blob/master/src/main/resources/gitflow.jpeg)

## 게임 규칙

> User와 Dealer가 카드 한 장씩 받아 카드의 합이 21에 가까운 사람이 승리하는 게임

* 전제
   * Player는 User와 Dealer로 이루어짐
   * Console
        * User-Dealer 간 1:1 게임으로 상정
   * Web
        * 다수의 Room이 존재
        * 각 Room마다 최대 n명의 User 접속 가능
        * 각 User-Dealer 간 1:1 게임으로 상정 (동일 Room에서 다수의 1:1 게임이 진행)
        * 단, 모든 User의 순서가 끝나야 Dealer 순서가 진행

* 기본 규칙
    * 공통
        * 시작 시, User와 Dealer는 각각 카드 2장을 받음
        * 카드는 총 52장 (조커 제외)
        * 2부터 10까지는 숫자대로 점수를 세고 J,Q,K는 10점으로 계산
        * A는 1점 혹은 11점으로 계산하는데 유리한 방향으로 계산
        * 카드의 합이 21이거나 21에 가까우면 승리
        * 처음에 받은 2장의 카드로 21점이 되는 경우, 'Blackjack'이라고 하며 게임에 승리하고 배팅액의 1.5배를 획득
        * 카드의 합이 21을 초과하는 경우, 'Burst'라고 하며 패배
        * 만일 User와 Dealer의 점수가 같은 경우, 무승부
        * 둘 다 Burst인 경우, Dealer 승리
    * User
        * 시작 전, 배팅 필수
        * 카드의 합이 21 미만 인 경우, User 판단으로 Hit 혹은 Stand 선택
    * Dealer
        * 처음에 받은 2장의 카드 중, 1장은 비공개
        * User의 순서가 종료되면, 일정한 규칙에 따라 Hit 혹은 Stand 선택

* 게임 내 규칙
    * Hit
        * 카드를 한 장 더 뽑는 행위
        * User는 자기 카드의 합이 21 이하 인 경우, 원하는 만큼 Hit 가능
        * Dealer는 자기 카드의 합이 17 미만인 경우, Hit 반복
    * Stand
        * 카드를 더 뽑지 않고 순서를 종료하는 행위
        * Dealer는 자기 카드의 합이 17 이상 인 경우, Dealer는 Stand
    * Double Down
        * 2배로 배팅하는 행위 (기존 배팅액만큼 추가 배팅하는 것을 의미)
        * User는 자기 카드의 합이 21 이하 인 경우, 앞으로 단 1장의 카드만 뽑는 조건으로 Double Down 가능


## Authors

최동선 [moving-line](https://github.com/moving-line)   
Contact : movinglinecheck@gmail.com

노진산 [zingoworks](https://github.com/zingoworks)    
Contact : zingoworks@gmail.com
