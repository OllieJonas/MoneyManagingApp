package me.csed2.moneymanager.ui.cmdline.step;

import lombok.Getter;
import me.csed2.moneymanager.utils.ClassUtils;

public class Step<T> {

    @Getter
    private final String text;

    @Getter
    private T result;

    @Getter
    private Class<T> resultType;

    public Step(Class<T> resultType, String text) {
        this.resultType = resultType;
        this.text = text;
    }

    public void setResult(Object result) {
        this.result = ClassUtils.getResultFromObject(result, resultType);
    }

    public void print() {
        System.out.println(text);
    }
}
