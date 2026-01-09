package cn.iocoder.yudao.module.smartcity.controller.admin.componentinformation.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 部件信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ComponentInformationPageReqVO extends PageParam {

    @Schema(description = "部件编号")
    private String partNumber;

    @Schema(description = "部件名称", example = "李四")
    private String componentName;

    @Schema(description = "部件类型", example = "1")
    private String partType;

    @Schema(description = "使用寿命")
    private String serviceLife;

    @Schema(description = "关联事件记录")
    private String relatedEventRecords;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}