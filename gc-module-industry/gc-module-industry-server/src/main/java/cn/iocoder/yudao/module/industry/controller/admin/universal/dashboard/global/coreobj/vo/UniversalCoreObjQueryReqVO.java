package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coreobj.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 通用大屏核心对象分布视图 查询 Request VO")
@Data
public class UniversalCoreObjQueryReqVO {

    @Schema(description = "查询地区-省市县三级shortCode码，6位", example = "110000")
    private String regionShortCode;

    @Schema(description = "设施类型-小类ID", example = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
    private String minorId;

    @Schema(description = "设施状态，如完好/破损/丢失/废弃", example = "完好")
    private String compStatus;

    @Schema(description = "模糊查询设施名称", example = "电力井盖")
    private String compName;
}
