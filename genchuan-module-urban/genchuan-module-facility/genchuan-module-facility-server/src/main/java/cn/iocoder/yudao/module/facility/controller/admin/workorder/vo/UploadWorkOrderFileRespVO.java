package cn.iocoder.yudao.module.facility.controller.admin.workorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 上传工单资料 Response VO")
@Data
public class UploadWorkOrderFileRespVO {

    @Schema(description = "[附件ID] 附件记录ID", example = "1")
    private Long id;

    @Schema(description = "[工单ID] 关联的工单ID", example = "10001")
    private Long workOrderId;

    @Schema(description = "[文件名称]", example = "现场图片.jpg")
    private String fileName;

    @Schema(description = "[文件URL]", example = "https://minio.xxx.com/work-order/2026/03/xxx.jpg")
    private String fileUrl;

    @Schema(description = "[文件大小] 单位：字节", example = "204800")
    private Long fileSize;

    @Schema(description = "[文件类型]", example = "image/jpeg")
    private String fileType;

    @Schema(description = "[资料说明]", example = "现场检测图片")
    private String fileDesc;

}
