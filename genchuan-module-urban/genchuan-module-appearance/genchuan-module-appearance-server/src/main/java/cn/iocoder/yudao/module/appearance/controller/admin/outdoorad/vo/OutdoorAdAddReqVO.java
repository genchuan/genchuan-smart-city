package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Schema(description = "管理后台 - 户外广告 Add Request VO")
@ToString(callSuper = true)
public class OutdoorAdAddReqVO {

    @Schema(description = "广告名称")
    private String name;

    @Schema(description = "广告类型（立柱/墙面/灯箱/电子屏）")
    private String type;

    @Schema(description = "广告尺寸（长*宽，单位：米）")
    private String approvedSize;

    @Schema(description = "广告具体位置")
    private String location;

    @Schema(description = "广告位置经度")
    private String lng;

    @Schema(description = "广告位置纬度")
    private String lat;

    @Schema(description = "所属区域编码")
    private String areaCode;

    @Schema(description = "所属网格编码")
    private String gridCode;

    @Schema(description = "附件文件ID列表（UUID）")
    private List<String> attachFileIds;

    @Schema(description = "备注")
    private String remark;
}
