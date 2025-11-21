package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalrulecat.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 规则分类管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalRuleCatPageReqVO extends PageParam {

    @Schema(description = "规则分类ID")
    private String ruleCatId;

    @Schema(description = "规则分类名称")
    private String ruleCatName;

    @Schema(description = "分类编码")
    private String ruleCatCode;

    @Schema(description = "适用指标体系ID")
    private String idxSystemId;

    @Schema(description = "适用指标体系名称")
    private String idxSystemName;

    @Schema(description = "分类描述")
    private String catDesc;

    @Schema(description = "启用状态")
    private String enableStatus;

    @Schema(description = "创建人")
    private String createUser;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人")
    private String updateUser;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间(系统)")
    private LocalDateTime createTimeSys;

    @Schema(description = "更新时间(系统)")
    private LocalDateTime updateTimeSys;

}