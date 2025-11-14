package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.data.basicmonidx.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 基础监测指标分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BasicMonIdxPageReqVO extends PageParam {

    @Schema(description = "指标ID")
    private String idxId;

    @Schema(description = "指标名称")
    private String idxName;

    @Schema(description = "指标编码")
    private String idxCode;

    @Schema(description = "指标单位")
    private String idxUnit;

    @Schema(description = "数据类型")
    private String dataType;

    @Schema(description = "数据长度")
    private String dataLength;

    @Schema(description = "关联点位类型")
    private String relPtType;

    @Schema(description = "预警阈值上限")
    private String warnThresholdMax;

    @Schema(description = "预警阈值下限")
    private String warnThresholdMin;

    @Schema(description = "指标说明")
    private String idxDesc;

    @Schema(description = "指标状态")
    private Boolean idxStatus;

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