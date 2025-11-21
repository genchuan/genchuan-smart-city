package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxweight.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 指标权重管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvalIdxWeightPageReqVO extends PageParam {

    @Schema(description = "权重ID")
    private String idxWeightId;

    @Schema(description = "所属指标体系ID")
    private String idxSystemId;

    @Schema(description = "所属指标体系名称")
    private String idxSystemName;

    @Schema(description = "权重类型")
    private String weightType;

    @Schema(description = "关联ID")
    private String relateId;

    @Schema(description = "关联名称")
    private String relateName;

    @Schema(description = "权重值(%)")
    private BigDecimal weightValue;

    @Schema(description = "权重说明")
    private String weightDesc;

    @Schema(description = "创建人(业务)")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)")
    private LocalDateTime createTimeBiz;

    @Schema(description = "更新人(业务)")
    private String updateUserBiz;

    @Schema(description = "更新时间(业务)")
    private LocalDateTime updateTimeBiz;

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