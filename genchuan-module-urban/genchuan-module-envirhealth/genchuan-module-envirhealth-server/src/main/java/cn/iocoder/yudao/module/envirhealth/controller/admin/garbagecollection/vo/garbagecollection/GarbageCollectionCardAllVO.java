package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 功能:收运计划全部统计 VO
 * 作者:SWE22008
 * 日期:2026/2/26 11:35
 */

@Schema(description = "环境卫生管理模块 - 收运计划全部统计 VO")
@Data
public class GarbageCollectionCardAllVO {

    @Schema(description = "总计划数", example = "8")
    private Long totalCount;

    @Schema(description = "待执行计划数", example = "1")
    private Long unexecutedCount;

    @Schema(description = "执行中计划数", example = "2")
    private Long executingCount;

    @Schema(description = "已完成计划数", example = "3")
    private Long completedCount;

    @Schema(description = "异常计划数", example = "4")
    private Long abnormalCount;
}