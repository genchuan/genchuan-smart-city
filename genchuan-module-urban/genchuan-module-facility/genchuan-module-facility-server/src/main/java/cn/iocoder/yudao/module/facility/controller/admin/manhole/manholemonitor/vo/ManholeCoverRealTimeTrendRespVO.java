package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo;

import lombok.Data;

// 响应VO - 主对象
@Data
public class ManholeCoverRealTimeTrendRespVO {
    private String coverId;
    private String coverName;
    private Integer indicatorType;
    private String indicatorTypeName;
    private TrendChartRespVO trendChart;
    private String tenantId;
}
