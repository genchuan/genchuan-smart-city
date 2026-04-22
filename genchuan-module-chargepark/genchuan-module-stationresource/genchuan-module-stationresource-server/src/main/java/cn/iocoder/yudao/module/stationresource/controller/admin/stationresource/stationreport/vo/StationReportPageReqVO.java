package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 场站资源报表分页查询 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "场站资源报表分页查询")
public class StationReportPageReqVO extends PageParam {

    @Schema(description = "报表周期：日报/周报/月报/季报/半年报/年报/自定义报表")
    private String reportCycle;

    @Schema(description = "开始时间 yyyy-MM-dd HH:mm:ss")
    private String startTime;

    @Schema(description = "结束时间 yyyy-MM-dd HH:mm:ss")
    private String endTime;

    @Schema(description = "生成状态：生成中/已生成/生成失败")
    private String generateStatus;


}
