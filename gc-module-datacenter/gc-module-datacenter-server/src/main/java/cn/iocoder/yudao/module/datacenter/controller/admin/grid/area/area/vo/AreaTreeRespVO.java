package cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.area.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Schema(description = "管理后台 - 统一行政区划配置表（树形结构）树节点 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
public class AreaTreeRespVO extends AreaRespVO {

    @Schema(description = "子节点列表")
    private List<AreaTreeRespVO> children;

    @Schema(description = "是否有子节点")
    public Boolean getHasChildren() {
        return children != null && !children.isEmpty();
    }

}