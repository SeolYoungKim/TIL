# 각종 질의응답 기록소 
> 해당 기록소를 만든 이유 
> - 질문을 잘 하기위한 노력을 해보자
> - 질문을 잘 할 뿐만 아니라, 받은 답변을 내껄로 만들기 위해 노력 해보자


## 질문을 잘 하기 위한 참고 자료들 (지속 업데이트 예정)
- [XY Problem이란?](https://americanopeople.tistory.com/351)
- [질문 방법 정리](https://kimsy8979.notion.site/40366ffbc56c4d2d9061a40954e2ee76?pvs=4)


## 테스트 코드 작성 기준에 대한 질문
```text
- 질문
    - 실무에서 테스트 코드를 작성하실 때, 이건 작성해야지~ 이건 작성 안해야지~ 하는 성현님만의 기준이 있으실까요?
- 답변
    - 인수 테스트는 무조건 작성하려고 하는 편
        - 어떤 요청을 받고, 어떤 응답이 나오는지를 볼 수 있기 때문
        - 큰 범위의 테스트를 먼저 구현
    - 도메인 단위의 단위 테스트를 중요하게 생각함 → 대부분 작성
        - 도메인 테스트를 안 하다 보면, 다른 계층에 비즈니스 로직이 퍼져나가게 되고, 유지보수 측면에서 불편해질 수 있다.
        - 도메인 단위 테스트를 작성하지 않으면, 테스트 짜기가 어려워질 수 있다.
    - 서비스나 비즈니스 레이어의 테스트는 안 짜고 넘어가는 편
        - 플로우를 검증하려고 한다면 → 통합 테스트를!
```
