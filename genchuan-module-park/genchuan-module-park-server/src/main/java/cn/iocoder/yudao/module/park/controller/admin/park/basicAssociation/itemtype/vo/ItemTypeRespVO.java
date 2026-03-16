package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.itemtype.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理事项类别 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ItemTypeRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28168")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "上级事项类别ID", example = "18680")
    @ExcelProperty("上级事项类别ID")
    private Long parentTypeId;

    @Schema(description = "唯一事项编码")
    @ExcelProperty("唯一事项编码")
    private String itemCode;

    @Schema(description = "事项名称", example = "赵六")
    @ExcelProperty("事项名称")
    private String itemName;

    @Schema(description = "所属业务域")
    @ExcelProperty("所属业务域")
    private String bizDomain;

    @Schema(description = "处理流程配置")
    @ExcelProperty("处理流程配置")
    private String processConfig;

    @Schema(description = "可处理角色ID列表")
    @ExcelProperty("可处理角色ID列表")
    private String handleRoleIds;

    @Schema(description = "状态：启用/停用", example = "2")
    @ExcelProperty("状态：启用/停用")
    private String itemStatus;

    @Schema(description = "业务备注", example = "你猜")
    @ExcelProperty("业务备注")
    private String itemRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
