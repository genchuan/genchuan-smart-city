// ResourceDistrQueryReqVO.java
package cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.resourcedistribution.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 文旅资源分布查询 Request VO")
@Data
public class CultureResDistriQueryReqVO {

    @Schema(description = "资源类型编码(关联sys_app_scene_minor的minor_code)", example = "SCENE_001")
    private String minorCode;

    @Schema(description = "运营状态(启用/停用)", example = "1")
    private Integer sceneStatus;

    @Schema(description = "行政区域编码(关联sys_area的region_code)", example = "330100")
    private String regionCode;
}