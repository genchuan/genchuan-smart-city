package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Schema(description = "管理后台 - 户外广告编辑 Request VO")
@Data
public class OutdoorAdEditReqVO {

    @Schema(description = "广告主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "k1l2m3n4-o5p6-7890-klmn-123456789012")
    @NotBlank(message = "广告ID不能为空")
    private String id;

    @Schema(description = "广告名称", example = "鼓楼区商业广场LED电子屏广告")
    private String adName;

    @Schema(description = "广告类型", example = "电子屏")
    private String adType;

    @Schema(description = "广告尺寸", example = "8*4")
    private String adSize;

    @Schema(description = "广告具体位置", example = "鼓楼区XX路商业广场1号楼外立面")
    private String location;

    @Schema(description = "经度", example = "119.2988")
    private String lng;

    @Schema(description = "纬度", example = "26.0853")
    private String lat;

    @Schema(description = "所属区域编码", example = "350105")
    private String areaCode;

    @Schema(description = "所属网格编码", example = "GRID-350105001-001")
    private String gridCode;

    @Schema(description = "实际尺寸", example = "8*4")
    private String actualSize;

    @Schema(description = "附件文件ID列表（覆盖原有附件）", example = "[\"uuid1\",\"uuid2\"]")
    private List<String> attachFileIds;

    @Schema(description = "备注", example = "商业广场主入口LED电子屏广告，尺寸与审批一致")
    private String remark;
}