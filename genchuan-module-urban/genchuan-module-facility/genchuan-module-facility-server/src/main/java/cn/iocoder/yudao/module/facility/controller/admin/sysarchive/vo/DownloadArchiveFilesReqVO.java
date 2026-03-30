package cn.iocoder.yudao.module.facility.controller.admin.sysarchive.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "管理后台 - 工单全流程记录 Response VO")
public class DownloadArchiveFilesReqVO {
    @Schema(description = "[归档id]")
    private Long archiveId;
}
