package cn.iocoder.yudao.module.evaluate.controller.admin.patrolinspection.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 巡查巡检创建结果 Response VO")
@Data
public class PatrolInspectionCreateRespVO {

    @Schema(description = "巡查巡检ID", example = "1")
    private Long id;

    @Schema(description = "图片URL", example = "https://xxx.com/image.jpg")
    private String imageUrl;

}
