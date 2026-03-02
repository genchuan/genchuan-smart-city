package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.column.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/2 11:43
 */
@Data
@Schema(description = "异常处置平均时长对比-柱状图VO")
public class GarbageAbnormalColumnHandleTimeVO {

    @Schema(description = "维度名称（如：区域/责任人/异常类型）", requiredMode = Schema.RequiredMode.REQUIRED, example = "朝阳区")
    private String name;

    @Schema(description = "平均处置时长（单位：小时）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2.5")
    private Double avgHandleHours;

}