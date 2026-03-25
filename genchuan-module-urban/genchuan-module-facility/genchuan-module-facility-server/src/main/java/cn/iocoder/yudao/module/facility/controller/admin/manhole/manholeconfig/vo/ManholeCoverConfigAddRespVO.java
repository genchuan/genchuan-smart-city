package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "窨井盖监测配置新增 Response VO")
public class ManholeCoverConfigAddRespVO {

    @Schema(description = "配置主键ID", example = "cfg-202503-00189")
    private String configId;

    @Schema(description = "窨井盖唯一ID", example = "c1d2e3f4-g5h6-7890-cdef-0123456789ab")
    private String coverId;

    @Schema(description = "配置状态 0-禁用 1-启用", example = "0")
    private Integer configStatus;

    @Schema(description = "配置数据区块链存证哈希", example = "0x456def789ghi012jkl345mno678pqr901abc")
    private String chainHash;

    @Schema(description = "租户ID", example = "1")
    private String tenantId;
}
