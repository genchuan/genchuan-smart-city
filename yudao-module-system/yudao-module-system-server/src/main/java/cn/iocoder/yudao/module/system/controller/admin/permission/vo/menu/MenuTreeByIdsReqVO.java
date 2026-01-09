package cn.iocoder.yudao.module.system.controller.admin.permission.vo.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 根据菜单编号列表获取菜单树形结构 Request VO")
@Data
public class MenuTreeByIdsReqVO {

    @Schema(description = "菜单编号列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "菜单编号列表不能为空")
    private List<Long> menuIds;

}
