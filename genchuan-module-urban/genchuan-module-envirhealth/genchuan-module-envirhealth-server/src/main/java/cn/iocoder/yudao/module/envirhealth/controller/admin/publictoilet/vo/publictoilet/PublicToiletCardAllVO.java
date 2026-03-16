package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/10 17:08
 */
@Data
public class PublicToiletCardAllVO {

    /**
     * 总公厕数
     */
    @Schema(description = "总计划数", example = "8")
    private Integer totalToiletCount;

    /**
     * 正常运营数
     */
    @Schema(description = "正常运营数", example = "8")
    private Integer normalOperationCount;

    /**
     * 保洁达标数（保洁达标率≥100% 或 按业务定义的达标阈值，此处假设≥95%为达标）
     */
    @Schema(description = "保洁达标数", example = "8")
    private Integer cleaningQualifiedCount;

    /**
     * 无投诉数（无未办结投诉的公厕数量）
     */
    @Schema(description = "无投诉数", example = "8")
    private Integer noComplaintCount;
}