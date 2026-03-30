package cn.iocoder.yudao.module.waterdetection.controller.admin.projectbasicinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 工程基本信息管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProjectBasicInfoPageReqVO extends PageParam {

    @Schema(description = "工程编码")
    private String projectCode;

    @Schema(description = "工程名称")
    private String projectName;

    @Schema(description = "设计供水规模(吨/日)")
    private String designCapacity;

    @Schema(description = "工艺类型")
    private String processType;

    @Schema(description = "投产日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] commissioningDate;

    @Schema(description = "管理单位")
    private String managementUnit;

    @Schema(description = "工程状态")
    private String projectStatus;

    @Schema(description = "所属行政区")
    private String administrativeRegion;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}