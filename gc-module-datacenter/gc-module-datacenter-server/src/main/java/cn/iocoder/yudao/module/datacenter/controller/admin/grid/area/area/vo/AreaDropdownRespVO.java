package cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.area.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "管理后台 - 行政区划下拉列表 Response VO")
@Data
public class AreaDropdownRespVO {

    @Schema(description = "乡镇列表（包含下属社区）")
    private List<TownWithCommunitiesVO> townList;

    @Schema(description = "乡镇项（包含社区）")
    @Data
    public static class TownWithCommunitiesVO {
        @Schema(description = "乡镇ID", requiredMode = Schema.RequiredMode.REQUIRED)
        private Long id;

        @Schema(description = "乡镇名称", requiredMode = Schema.RequiredMode.REQUIRED)
        private String name;

        @Schema(description = "完整行政区划代码（12位）", requiredMode = Schema.RequiredMode.REQUIRED)
        private String fullCode;

        @Schema(description = "社区列表")
        private List<CommunityVO> children;
    }

    @Schema(description = "社区项")
    @Data
    public static class CommunityVO {
        @Schema(description = "社区ID", requiredMode = Schema.RequiredMode.REQUIRED)
        private Long id;

        @Schema(description = "社区名称", requiredMode = Schema.RequiredMode.REQUIRED)
        private String name;

        @Schema(description = "完整行政区划代码（12位）", requiredMode = Schema.RequiredMode.REQUIRED)
        private String fullCode;
    }
}