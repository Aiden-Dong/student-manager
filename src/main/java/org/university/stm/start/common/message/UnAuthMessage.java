package org.university.stm.start.common.message;

/**
 * <pre>
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-02-28
 */
public class UnAuthMessage extends Message {
    private static final int UNAUTH_CODE = 400;
    public UnAuthMessage(String message) {
        super(Status.UNAUTH, UNAUTH_CODE, message);
    }
}
