package cn.iocoder.yudao.module.datacenter.controller.admin.sceneconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 场景分类树形结构 Response VO")
@Data
public class SceneConfigTreeRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "父级ID")
    private Long pid;

    @Schema(description = "场景名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "设备配置名称")
    private String deviceConfigName;

    @Schema(description = "资产配置名称")
    private String assetConfigName;

    @Schema(description = "流程配置名称")
    private String flowConfigName;

    @Schema(description = "备注")
    private String info;

    @Schema(description = "备用1")
    private String info1;

    @Schema(description = "备用2")
    private String info2;

    @Schema(description = "备用3")
    private String info3;

    @Schema(description = "备用4")
    private String info4;

    @Schema(description = "子节点列表")
    private List<SceneConfigTreeRespVO> children;
}
