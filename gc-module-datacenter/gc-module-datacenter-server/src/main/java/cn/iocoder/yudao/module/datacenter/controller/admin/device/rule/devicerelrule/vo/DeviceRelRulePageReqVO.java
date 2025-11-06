package cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicerelrule.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备关联规则配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceRelRulePageReqVO extends PageParam {

    @Schema(description = "关联规则ID")
    private String relRuleId;

    @Schema(description = "设备分类ID")
    private String deviceCatId;

    @Schema(description = "设备分类名称")
    private String deviceCatName;

    @Schema(description = "关联对象类型")
    private String relObjType;

    @Schema(description = "关联对象ID")
    private String relObjId;

    @Schema(description = "关联对象名称")
    private String relObjName;

    @Schema(description = "关联必选标识")
    private String isRequired;

    @Schema(description = "关联校验规则")
    private String relCkRule;

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