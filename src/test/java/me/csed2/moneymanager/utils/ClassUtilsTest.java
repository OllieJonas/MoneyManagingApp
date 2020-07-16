package me.csed2.moneymanager.utils;

import static org.assertj.core.api.Assertions.*;

import me.csed2.moneymanager.exceptions.InvalidTypeException;
import org.junit.jupiter.api.Test;

public class ClassUtilsTest {

    @Test
    public void canCastCorrectDeterminesIfYouCanCast() {
        String subject = "Hello world";
        assertThat(ClassUtils.canCast(subject, String.class)).isTrue();
        assertThat(ClassUtils.canCast(subject, Integer.class)).isFalse();
    }

    @Test
    public void castObjectToCorrectClass() {
        Object subject = "Hello world";
        assertThat(ClassUtils.cast(subject, String.class).getClass()).isEqualTo(String.class);
        assertThatThrownBy(() -> ClassUtils.cast(subject, Integer.class))
                .isInstanceOf(InvalidTypeException.class)
                .hasMessageContainingAll("Integer", subject.toString());
    }
}
