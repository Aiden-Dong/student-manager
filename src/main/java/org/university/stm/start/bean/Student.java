package org.university.stm.start.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.util.Date;

/**
 * <pre>
 * st_id | long | 学号 | 区别学生的唯一性标志 | 主键, 唯一
 * st_name | string | 名称 | 学生姓名 | 非空
 * st_start_time | long | 入学时间 | 记录在校时间 | 非空
 * cls_id | long | 班级 | 关联班级信息 | 非空
 * st_city | string | 城市 | 学生所在地 | 非空
 * st_sex | string | 性别 | 学生性别信息 | 非空
 * st_political | string | 政治面貌 |群众，共青团员， 党员 | 非空
 * </pre>
 *
 * @author : ting.chen
 * @date : 2020-02-25
 */
public class Student {
    @JsonProperty("stId")
    private long stId;
    @JsonProperty("stName")
    private String stName;
    @JsonProperty("stStartTime")
    private long stStartTime;
    @JsonProperty("clsId")
    private long clsId;
    @JsonProperty("stCity")
    private String stCity;
    @JsonProperty("stSex")
    private String stSex;
    @JsonProperty("stPolitical")
    private String stPolitical;

    public Student(
            @JsonProperty("stId") long stId,
            @JsonProperty("stName") String stName,
            @JsonProperty("stStartTime") long stStartTime,
            @JsonProperty("clsId") long clsId,
            @JsonProperty("stCity") String stCity,
            @JsonProperty("stSex") String stSex,
            @JsonProperty("stPolitical") String stPolitical) {
        if(stId == 0){
            stId = (clsId / 10000) * 10000 + DateTime.now().getMillis() % 10000;;
        }
        this.stId = stId;
        this.stName = stName;
        this.stStartTime = stStartTime;
        this.clsId = clsId;
        this.stCity = stCity;
        this.stSex = stSex;
        this.stPolitical = stPolitical;
    }


    public long getStId() {
        return stId;

    }

    public void setStId(long stId) {
        this.stId = stId;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public long getStStartTime() {
        return stStartTime;
    }

    public void setStStartTime(long stStartTime) {
        this.stStartTime = stStartTime;
    }

    public long getClsId() {
        return clsId;
    }

    public void setClsId(long clsId) {
        this.clsId = clsId;
    }

    public String getStCity() {
        return stCity;
    }

    public void setStCity(String stCity) {
        this.stCity = stCity;
    }

    public String getStSex() {
        return stSex;
    }

    public void setStSex(String stSex) {
        this.stSex = stSex;
    }

    public String getStPolitical() {
        return stPolitical;
    }

    public void setStPolitical(String stPolitical) {
        this.stPolitical = stPolitical;
    }

    @Override
    public String toString() {
        return String.format("{%d, %s, %d, %s, %s, %s, %s}",
                this.stId,
                this.stName,
                this.clsId,
                ISODateTimeFormat.date().print(this.stStartTime),
                this.stCity,
                this.stSex,
                this.stPolitical);
    }
}
