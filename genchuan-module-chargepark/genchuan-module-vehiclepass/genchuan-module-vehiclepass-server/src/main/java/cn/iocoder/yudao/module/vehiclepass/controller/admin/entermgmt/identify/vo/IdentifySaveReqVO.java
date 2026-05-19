package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.identify.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 车牌识别新增/修改 Request VO")
@Data
public class IdentifySaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11351")
    private Long id;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌不能为空")
    private String plateNo;

    @Schema(description = "车牌颜色：蓝牌/黄牌/绿牌/其他 关联字典：plate_identify_plate_color", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌颜色：蓝牌/黄牌/绿牌/其他 关联字典：plate_identify_plate_color不能为空")
    private String plateColor;

    @Schema(description = "置信度 识别置信度百分比", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "置信度 识别置信度百分比不能为空")
    private BigDecimal confidence;

    @Schema(description = "抓拍图片地址", example = "https://www.iocoder.cn")
    private String imageUrl;

    @Schema(description = "识别状态：识别成功/识别失败 关联字典：plate_identify_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "识别状态：识别成功/识别失败 关联字典：plate_identify_status不能为空")
    private String status;

    @Schema(description = "场站ID 关联场站表", requiredMode = Schema.RequiredMode.REQUIRED, example = "5512")
    @NotNull(message = "场站ID 关联场站表不能为空")
    private Long stationId;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "修正记录标记：0-未修正 1-已修正 2-已确认", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "修正记录标记：0-未修正 1-已修正 2-已确认不能为空")
    private Integer isCorrected;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}