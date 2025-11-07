package cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.deviceattrrule.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备属性规则配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceAttrRulePageReqVO extends PageParam {

    @Schema(description = "属性规则ID")
    private String attrRuleId;

    @Schema(description = "设备分类ID")
    private String deviceCatId;

    @Schema(description = "设备分类名称")
    private String deviceCatName;

    @Schema(description = "属性名称")
    private String attrName;

    @Schema(description = "属性代码")
    private String attrCode;

    @Schema(description = "数据类型")
    private String dataType;

    @Schema(description = "字段长度")
    private Integer fieldLength;

    @Schema(description = "是否必选")
    private String isRequired;

    @Schema(description = "计量单位")
    private String unit;

    @Schema(description = "值域范围")
    private String valueRange;

    @Schema(description = "默认值")
    private String defaultValue;

    @Schema(description = "属性说明")
    private String attrDesc;

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

    @Schema(description = "系统创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}