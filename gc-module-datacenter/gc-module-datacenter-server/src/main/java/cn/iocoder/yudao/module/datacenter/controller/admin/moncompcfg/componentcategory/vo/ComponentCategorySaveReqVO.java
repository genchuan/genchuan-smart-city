package cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.componentcategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 监测部件分类新增/修改 Request VO")
@Data
public class ComponentCategorySaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20709")
    private Long id;

    @Schema(description = "分类ID，UUID", example = "7280")
    private String categoryId;

    @Schema(description = "父类ID，0表示大类", example = "22250")
    private String parentId;

    @Schema(description = "层级：1-大类/2-中类/3-小类")
    private String level;

    @Schema(description = "分类代码：01-99")
    private String code;

    @Schema(description = "分类名称", example = "赵六")
    private String name;

    @Schema(description = "分类说明", example = "随便")
    private String description;

    @Schema(description = "状态：1-启用/0-禁用", example = "2")
    private String status;

    @Schema(description = "创建人ID")
    private String createUser;

    @Schema(description = "更新人ID")
    private String updateUser;

    @Schema(description = "扩展字段1")
    private String ext1;

    @Schema(description = "扩展字段2")
    private String ext2;

    @Schema(description = "扩展字段3")
    private String ext3;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}