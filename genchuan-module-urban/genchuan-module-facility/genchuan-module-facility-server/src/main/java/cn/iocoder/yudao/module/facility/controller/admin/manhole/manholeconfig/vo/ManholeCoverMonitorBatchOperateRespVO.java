package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholeconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "井盖监测 批量启动/停止 响应参数")
public class ManholeCoverMonitorBatchOperateRespVO {

    @Schema(description = "操作成功数量")
    private Integer successCount;

    @Schema(description = "操作失败数量")
    private Integer failCount;

    @Schema(description = "操作失败的井盖ID列表")
    private List<String> failCovers;

    @Schema(description = "租户ID")
    private String tenantId;
}