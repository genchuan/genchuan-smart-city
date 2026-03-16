package cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning;

import lombok.Data;

@Data
public class HourlyProgressDO {
    private Integer hourPoint;
    private Double avgProgress;
}