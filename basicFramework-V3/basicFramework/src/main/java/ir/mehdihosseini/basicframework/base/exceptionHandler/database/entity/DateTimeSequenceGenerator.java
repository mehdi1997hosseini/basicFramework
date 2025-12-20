package ir.mehdihosseini.basicframework.base.exceptionHandler.database.entity;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeSequenceGenerator  implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) {

        BigDecimal seq = (BigDecimal) session
                .createNativeQuery("SELECT EVENT_SEQ.NEXTVAL FROM DUAL")
                .getSingleResult();

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));

        return timestamp + String.format("%03d", seq.longValue() % 1000);
    }
}