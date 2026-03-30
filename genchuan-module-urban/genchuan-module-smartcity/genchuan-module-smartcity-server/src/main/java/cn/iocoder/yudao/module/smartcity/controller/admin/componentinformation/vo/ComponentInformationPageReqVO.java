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

    @Schema(description = "部件名称")
    private String componentName;

    @Schema(description = "部件类型")
    private String partType;

    @Schema(description = "所属区域")
    private String belongingRegion;

    @Schema(description = "部件状态")
    private String componentStatus;

}