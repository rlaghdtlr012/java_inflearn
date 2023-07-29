package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy") // 같은 빈 타입이 있을 때, 이를 구분하기 위해 구분자를 하나 추가해주는 것(빈 이름 바꿔주는 거x)
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; // 1000원 할인 고정
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
