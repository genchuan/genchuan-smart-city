package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.monitorparttype.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 监测部件类别 Response VO")
@Data
@ExcelIgnoreUnannotated
public class monitorPartTypeRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26564")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "上级监测类别ID", example = "3079")
    @ExcelProperty("上级监测类别ID")
    private Long parentTypeId;

    @Schema(description = "唯一类别编码")
    @ExcelProperty("唯一类别编码")
    private String typeCode;

    @Schema(description = "类别名称", example = "赵六")
    @ExcelProperty("类别名称")
    private String typeName;

    @Schema(description = "核心监测指标")
    @ExcelProperty("核心监测指标")
    private String monitorIndices;

    @Schema(description = "数据类型：状态型/数值型/事件型", example = "2")
    @ExcelProperty("数据类型：状态型/数值型/事件型")
    private String dataType;

    @Schema(description = "采集周期（秒）")
    @ExcelProperty("采集周期（秒）")
    private Integer collectionCycle;

    @Schema(description = "所属业务域：设备运维域/停车资源域")
    @ExcelProperty("所属业务域：设备运维域/停车资源域")
    private String bizDomain;

    @Schema(description = "状态：启用/停用", example = "1")
    @ExcelProperty("状态：启用/停用")
    private String typeStatus;

    @Schema(description = "业务备注", example = "你说的对")
    @ExcelProperty("业务备注")
    private String typeRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
