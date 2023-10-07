package 자잘한_개념_정리.JPA._JPQL;

public class UserMoney {
    private Integer totalAmount;

    public void increaseAmount(Integer amount) {
        totalAmount += amount;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }
}
