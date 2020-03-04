package org.university.stm.start.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

/**
 * <pre>
 *      cls_id | long | 班号 | 区别班级的唯一性标志 | 主键，唯一
 *      cls_name | string | 班级名称 | 可读性的班级标识 | 唯一， 非空
 *      cls_create_time | long | 成立时间 | 标识班级生存时间 | 非空
 *      cls_manager | string | 负责人 | 班主任信息 | 非空
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-02-25
 */
public class ClassOrganize {
    @JsonProperty("clsId")
    private long clsId; // {createTime[yyyy]timestamp[dd]timestamp[%4]}:
    @JsonProperty("clsName")
    private String clsName;
    @JsonProperty("clsCreateTime")
    private long clsCreateTime;
    @JsonProperty("clsManager")
    private String clsManager;

    public ClassOrganize(@JsonProperty("clsId") long clsId,
                         @JsonProperty("clsName") String clsName,
                         @JsonProperty("clsCreateTime") long clsCreateTime,
                         @JsonProperty("clsManager") String clsManager) {
        if(clsId == 0){
            DateTime crtTime = new DateTime(clsCreateTime);
            DateTime nowTime = DateTime.now();
            clsId = crtTime.getYear() * 1000000 + nowTime.getMillis() % 1000000;
        }
        this.clsId = clsId;
        this.clsName = clsName;
        this.clsCreateTime = clsCreateTime;
        this.clsManager = clsManager;
    }

    public long getClsId() {
        return clsId;
    }

    public void setClsId(long clsId) {
        this.clsId = clsId;
    }

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public long getClsCreateTime() {
        return clsCreateTime;
    }

    public void setClsCreateTime(long clsCreateTime) {
        this.clsCreateTime = clsCreateTime;
    }

    public String getClsManager() {
        return clsManager;
    }

    public void setClsManager(String clsManager) {
        this.clsManager = clsManager;
    }

    @Override
    public String toString() {
        return String.format("{%d, %s, %s, %s}",
                this.clsId,
                this.clsName,
                ISODateTimeFormat.date().print(this.clsCreateTime),
                this.clsManager);
    }
}
