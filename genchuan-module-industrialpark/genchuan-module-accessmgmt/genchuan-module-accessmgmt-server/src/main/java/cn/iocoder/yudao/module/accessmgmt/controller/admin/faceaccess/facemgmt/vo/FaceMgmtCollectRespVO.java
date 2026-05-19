package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.facemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 人脸采集 Response VO")
@Data
public class FaceMgmtCollectRespVO {

    @Schema(description = "操作结果")
    private Boolean success;

    @Schema(description = "采集验证准确率")
    private BigDecimal verifyAccuracy;

}