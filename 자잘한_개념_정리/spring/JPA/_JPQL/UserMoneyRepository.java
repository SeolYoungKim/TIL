package 자잘한_개념_정리.spring.JPA._JPQL;

public interface UserMoneyRepository {
    UserMoney findByUserId(String userId);
}
