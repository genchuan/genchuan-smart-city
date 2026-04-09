package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.*;

import java.util.List;

@Schema(description = "管理后台 - 户外广告新增 Request VO")
@Data
public class OutdoorAdAddReqVO {
    @Schema(description = "广告名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "鼓楼区商业广场电子屏广告")
    @NotBlank(message = "广告名称不能为空")
    private String adName;

    @Schema(description = "广告类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "电子屏")
    @NotBlank(message = "广告类型不能为空")
    private String adType;

    @Schema(description = "广告尺寸", requiredMode = Schema.RequiredMode.REQUIRED, example = "8*4")
    @NotBlank(message = "广告尺寸不能为空")
    private String adSize;

    @Schema(description = "广告具体位置", requiredMode = Schema.RequiredMode.REQUIRED, example = "鼓楼区XX路商业广场1号楼外立面")
    @NotBlank(message = "广告位置不能为空")
    private String location;

    @Schema(description = "经度", requiredMode = Schema.RequiredMode.REQUIRED, example = "119.2988")
    @NotBlank(message = "经度不能为空")
    private String lng;

    @Schema(description = "纬度", requiredMode = Schema.RequiredMode.REQUIRED, example = "26.0853")
    @NotBlank(message = "纬度不能为空")
    private String lat;

    @Schema(description = "所属区域编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "350105")
    @NotBlank(message = "区域编码不能为空")
    private String areaCode;

    @Schema(description = "所属网格编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "GRID-350105001-001")
    @NotBlank(message = "网格编码不能为空")
    private String gridCode;

    @Schema(description = "附件文件ID列表", example = "[\"l2m3n4o5-p6q7-8901-lmno-234567890123\"]")
    private List<String> attachFileIds;

    @Schema(description = "备注", example = "商业广场主入口电子屏广告")
    private String remark;
}
