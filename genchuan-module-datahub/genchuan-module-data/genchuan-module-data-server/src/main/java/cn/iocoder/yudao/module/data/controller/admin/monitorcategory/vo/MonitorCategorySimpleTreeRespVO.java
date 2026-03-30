package cn.iocoder.yudao.module.data.controller.admin.monitorcategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 监测部件分类简化树节点 Response VO")
@Data
public class MonitorCategorySimpleTreeRespVO {

    @Schema(description = "分类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private String id;

    @Schema(description = "分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "服务器监测")
    private String label;

    @Schema(description = "子分类列表")
    private List<MonitorCategorySimpleTreeRespVO> children;

}