package cn.iocoder.yudao.module.accessmgmt.controller.admin.accessreport.cyclereport.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 通行周期报表分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AccessCycleReportPageReqVO extends PageParam {

    @Schema(description = "报表名称，支持模糊查询")
    private String reportName;

    @Schema(description = "周期类型（日报/周报/月报/季报/半年报/年报/自定义）")
    private String cycleType;

    @Schema(description = "创建开始时间，格式时间戳")
    private String startTime;

    @Schema(description = "创建结束时间，格式时间戳")
    private String endTime;

}
