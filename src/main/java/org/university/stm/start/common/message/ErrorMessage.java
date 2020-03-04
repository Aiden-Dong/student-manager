package org.university.stm.start.common.message;

/**
 * <pre>
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-02-28
 */
public class ErrorMessage extends Message {
    private static final int ERROR_CODE = 400;

    public ErrorMessage(String message) {
        super(Status.ERROR, ERROR_CODE, message);
    }
}
