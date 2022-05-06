package hello.itemservice.messages;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.beans.Transient;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MessageSourceTest {
    @Autowired
    MessageSource ms;

    @Test
    void helloTest() {
        String hello = ms.getMessage("hello", null, null);
        assertThat(hello).isEqualTo("hello");
    }

    @Test
    void noMessage() {
        assertThatThrownBy(() -> ms.getMessage("no-code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void defaultMessage() {
        String message = ms.getMessage("no-code", null, "default", null);
        assertThat(message).isEqualTo("default");
    }

    @Test
    void argumentMessage() {
        String message = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(message).isEqualTo("hello Spring");
    }

    @Test
    void localeTest() {
        String hello = ms.getMessage("hello", null, Locale.KOREA);
        assertThat(hello).isEqualTo("안녕");
    }
}
