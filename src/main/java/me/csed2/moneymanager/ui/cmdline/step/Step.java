package me.csed2.moneymanager.ui.cmdline.step;

import lombok.Getter;
import me.csed2.moneymanager.utils.ClassUtils;

public class Step<T> {

    @Getter
    private String text;

    private String[] multipleText;

    @Getter
    private T result;

    @Getter
    private Class<T> resultType;

    public Step(Class<T> resultType, String text) {
        this.resultType = resultType;
        this.text = text;
    }

    public Step(Class<T> resultType, String... text) {
        this.resultType = resultType;
        this.multipleText = text;
    }

    public void setResult(Object result) {
        this.result = ClassUtils.getResultFromObject(result, resultType);
    }

    public void print() {
        if (text != null) {
            System.out.println(text);
        } else if (multipleText != null) {
            for (String line : multipleText) {
                System.out.println(line);
            }
        }
    }
}
