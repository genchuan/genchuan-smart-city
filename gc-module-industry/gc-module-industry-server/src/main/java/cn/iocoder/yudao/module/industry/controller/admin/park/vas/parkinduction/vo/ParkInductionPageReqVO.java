package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkinduction.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 停车诱导配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkInductionPageReqVO extends PageParam {

    @Schema(description = "[诱导名称] 停车诱导名称", example = "芋艿")
    private String inductionName;

    @Schema(description = "[覆盖区域] JSON格式varchar经纬度范围数据")
    private String region;

    @Schema(description = "[关联车场ID列表] 如：1,2,3")
    private String relatedLotIds;

    @Schema(description = "[推送策略] 如：实时推送/定时推送/按需推送")
    private String pushStrategy;

    @Schema(description = "[状态] 如：0-禁用/1-启用", example = "2")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[备注] 停车诱导配置相关备注说明", example = "随便")
    private String remark;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
