package me.csed2.moneymanager.ui.cmdline.stage;

import lombok.Getter;
import me.csed2.moneymanager.utils.ClassUtils;

public class Stage<T> {

    @Getter
    private String text;

    private String[] multipleText;

    @Getter
    private T result;

    @Getter
    private Class<T> resultType;

    public Stage(Class<T> resultType, String text) {
        this.resultType = resultType;
        this.text = text;
    }

    public Stage(Class<T> resultType, String... text) {
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
