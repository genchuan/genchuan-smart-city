package cn.iocoder.yudao.module.kitchen.dal.mysql.riskreport;

import lombok.Data;

@Data
public class EntViolationStatDO {
    private String entName;
    private Integer violationCount;
}
