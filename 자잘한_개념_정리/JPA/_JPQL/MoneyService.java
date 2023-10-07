package 자잘한_개념_정리.JPA._JPQL;

public class MoneyService {
    private final UserMoneyRepository userMoneyRepository;

    public MoneyService(UserMoneyRepository userMoneyRepository) {
        this.userMoneyRepository = userMoneyRepository;
    }

    // As-is
    public Integer getTotalAmount(String userId, Integer amount) {
        UserMoney userMoney = userMoneyRepository.findByUserId(userId);
        userMoney.increaseAmount(amount);
        return userMoney.getTotalAmount();
    }

    // To-be
    public void increaseAmount(String userId, Integer amount) {
        UserMoney userMoney = userMoneyRepository.findByUserId(userId);
        userMoney.increaseAmount(amount);
    }

    public Integer getTotalAmount(String userId) {
        UserMoney userMoney = userMoneyRepository.findByUserId(userId);
        return userMoney.getTotalAmount();
    }
}
