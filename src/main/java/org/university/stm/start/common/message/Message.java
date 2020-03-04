package org.university.stm.start.common.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <pre>
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-02-28
 */
public abstract class Message {
    private Status status;
    private int code;
    private Object body;

    public Message(Status status, int code, Object body){
        this.status = status;
        this.code = code;
        this.body = body;
    }

    @JsonProperty("status")
    public String getStatus(){
        return this.status.getStatus();
    }

    @JsonProperty("code")
    public int getCode(){
        return this.code;
    }

    @JsonProperty("body")
    public Object getBody (){
        return this.body;
    }

    protected enum Status{
        SUCCESS("success"),
        ERROR("error"),
        UNAUTH("unAuth");

        private String status;

        private Status(String status){
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        @Override
        public String toString(){
            return status;
        }
    }
}
