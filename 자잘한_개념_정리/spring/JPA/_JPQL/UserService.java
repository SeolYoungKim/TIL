package 자잘한_개념_정리.spring.JPA._JPQL;

public class UserService {
    private final MoneyService moneyService;

    public UserService(MoneyService moneyService) {
        this.moneyService = moneyService;
    }

    // As-is
//    @Transactional
    public void 아주_복잡한_비즈니스_로직(String userId, Integer amount) {
        Integer totalAmount = moneyService.getTotalAmount(userId, amount);

        // 복잡한 로직들 수행
    }

    // To-be
//    @Transactional
    public void 변경후_아주_복잡한_비즈니스_로직(String userId, Integer amount) {
        moneyService.increaseAmount(userId, amount);
        Integer totalAmount = moneyService.getTotalAmount(userId);

        // 복잡한 로직들 수행
    }
}
