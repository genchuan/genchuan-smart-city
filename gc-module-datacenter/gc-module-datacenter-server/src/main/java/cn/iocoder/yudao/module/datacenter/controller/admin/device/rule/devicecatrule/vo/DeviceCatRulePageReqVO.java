package cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicecatrule.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备分类规则配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceCatRulePageReqVO extends PageParam {

    @Schema(description = "分类规则ID")
    private String catRuleId;

    @Schema(description = "父类规则ID")
    private String parentCatRuleId;

    @Schema(description = "分类层级")
    private String catLevel;

    @Schema(description = "分类代码")
    private String catCode;

    @Schema(description = "分类名称")
    private String catName;

    @Schema(description = "分类说明")
    private String catDesc;

    @Schema(description = "启用状态")
    private String enableStatus;

    @Schema(description = "创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "更新时间")
    private LocalDateTime updateTimeSys;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}