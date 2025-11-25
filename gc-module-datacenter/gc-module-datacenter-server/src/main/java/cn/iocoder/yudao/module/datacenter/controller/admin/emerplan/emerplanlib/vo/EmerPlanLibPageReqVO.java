package cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplanlib.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 预案库分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmerPlanLibPageReqVO extends PageParam {

    @Schema(description = "预案ID，唯一编码，UUID生成", example = "14139")
    private String planId;

    @Schema(description = "预案编号，系统自动生成，格式“PLAN+年份(4位)+6位流水号”")
    private String planNo;

    @Schema(description = "预案标准名称，如“XX市2025年城市内涝应急预案”", example = "李四")
    private String planName;

    @Schema(description = "分类ID，关联预案分类配置表", example = "16865")
    private String catId;

    @Schema(description = "分类名称，拼接“大类 - 中类 - 小类”名称", example = "芋艿")
    private String catName;

    @Schema(description = "适用区域代码，关联行政区划表")
    private String applyRegionCode;

    @Schema(description = "适用区域名称，与区域代码同步", example = "李四")
    private String applyRegionName;

    @Schema(description = "预案版本，如“V1.0”“V2.1”")
    private String planVersion;


    @Schema(description = "生效时间，格式YYYYMMDD，预案生效日期", example = "20251101")
    @JsonFormat(pattern = "yyyyMMdd")   // 仅当请求体以字符串"20251101"传入时需要
    private LocalDateTime[] effectiveTime;

    @Schema(description = "失效时间，格式YYYYMMDD，预案失效日期", example = "20251130")
    @JsonFormat(pattern = "yyyyMMdd")   // 同上
    private LocalDateTime[] expireTime;

    @Schema(description = "预案文档路径，PDF格式预案存储路径")
    private String planDocPath;

}