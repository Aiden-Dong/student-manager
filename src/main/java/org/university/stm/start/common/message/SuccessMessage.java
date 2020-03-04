package org.university.stm.start.common.message;

/**
 * <pre>
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-02-28
 */
public class SuccessMessage extends Message {
    private static final int SUCCESS_CODE = 200;
    public SuccessMessage(Object body) {
        super(Status.SUCCESS, SUCCESS_CODE, body);
    }
}
