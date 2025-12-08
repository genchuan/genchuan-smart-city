package cn.iocoder.yudao.module.datacenter.controller.admin.appscenecategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 应用场景分类配置树形结构 Response VO")
@Data
public class AppSceneCategoryTreeRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "应用场景分类代码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String sceneCatCode;

    @Schema(description = "应用场景分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String sceneCatName;

    @Schema(description = "分类层级：1-大类/2-中类/3-小类")
    private String catLevel;

    @Schema(description = "父级分类ID")
    private String parentCatId;

    @Schema(description = "父级分类名称")
    private String parentCatName;

    @Schema(description = "应用场景分类说明")
    private String sceneCatDesc;

    @Schema(description = "是否扩展类：0-标准类/1-扩展类")
    private String isExt;

    @Schema(description = "启用状态：1-启用/0-禁用")
    private String enableStatus;

    @Schema(description = "子节点列表")
    private List<AppSceneCategoryTreeRespVO> children;

    @Schema(description = "关联的菜单编号")
    private String menuIds;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;


}