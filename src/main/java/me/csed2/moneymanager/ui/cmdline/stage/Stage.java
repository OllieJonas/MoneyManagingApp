package me.csed2.moneymanager.ui.cmdline.stage;

import lombok.Getter;
import me.csed2.moneymanager.exceptions.InvalidTypeException;
import me.csed2.moneymanager.ui.cmdline.InputProcessor;
import me.csed2.moneymanager.ui.cmdline.InputReader;
import me.csed2.moneymanager.utils.ClassUtils;
import me.csed2.moneymanager.utils.StringReaderFactory;

/**
 * This class contains the implementation of a Stage in the StageMenu {@link StageMenu}.
 *
 * This class works by specifying the Class type that you expect the user to input, followed by the text that will be
 * printed when on this stage. When on this stage, the InputProcessor {@link InputProcessor} will then print out the
 * text specified. After this, the InputReader {@link InputReader} will then wait for the reply from the user.
 *
 * From this, the StringReaderFactory {@link StringReaderFactory} and then use a utility will be used from
 * ClassUtils {@link ClassUtils} to then check if this is a valid input based on the Class specified in here.
 *
 * If this is unsuccessful, an InvalidTypeException {@link InvalidTypeException} will be thrown, where the StageMenu
 * will then print out the error message and call for the stage to be repeated. However, if successful, the StageMenu
 * will then assign the result of their input to this stage.
 *
 * From this, you can then get the result the user typed in by getting this stage from the List of stages then
 * getting the result from there, ensuring that you cast this properly.
 *
 * WARNING: If you wrongly cast the result, it will NOT show an error in your IDE but WILL throw a ClassCastException
 * at runtime. For example, if you specify for this to be an Integer, then you attempt to cast the result to a String
 * later on, it will throw a ClassCastException but won't show an error in the IDE.
 *
 * @param <T> The type of object you wish this Stage to hold.
 */
public class Stage<T> {

    @Getter
    private final Class<T> resultType;

    @Getter
    private final String[] text;

    @Getter
    private T result;


    private ExecutionPhase phase;

    public Stage(Class<T> resultType, String... text) {
        this.resultType = resultType;
        this.text = text;
    }

    public void setResult(Object result) {
        this.result = ClassUtils.getResultFromObject(result, resultType);
    }

    public void print() {
        for (String line : text) {
            System.out.println(line);
        }
    }

    public Stage<T> withExecutionPhase(ExecutionPhase phase) {
        phase.execute();
        return this;
    }

    public void executionPhase() {
        if (phase != null) {
            phase.execute();
        }
    }

    @FunctionalInterface
    public interface ExecutionPhase {
        void execute();
    }
}
