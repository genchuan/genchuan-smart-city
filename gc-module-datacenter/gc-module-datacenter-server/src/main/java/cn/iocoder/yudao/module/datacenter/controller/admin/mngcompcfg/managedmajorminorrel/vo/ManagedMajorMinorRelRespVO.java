package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedmajorminorrel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 管理部件大类小类关联 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ManagedMajorMinorRelRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7717")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "关联ID，唯一编码，采用UUID", example = "2827")
    @ExcelProperty("关联ID，唯一编码，采用UUID")
    private String relId;

    @Schema(description = "关联的大类ID", example = "26751")
    @ExcelProperty("关联的大类ID")
    private String majorId;

    @Schema(description = "关联的小类ID", example = "25666")
    @ExcelProperty("关联的小类ID")
    private String minorId;

    @Schema(description = "关联状态：0-无效/1-有效", example = "1")
    @ExcelProperty("关联状态：0-无效/1-有效")
    private String relStatus;

    @Schema(description = "关联时间，格式：yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("关联时间，格式：yyyy-MM-dd HH:mm:ss")
    private LocalDateTime relTime;

    @Schema(description = "解除关联时间，格式：yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("解除关联时间，格式：yyyy-MM-dd HH:mm:ss")
    private LocalDateTime unrelTime;

    @Schema(description = "操作人ID")
    @ExcelProperty("操作人ID")
    private String operateUser;

    @Schema(description = "备注信息", example = "你说的对")
    @ExcelProperty("备注信息")
    private String remark;

    @Schema(description = "扩展字段1")
    @ExcelProperty("扩展字段1")
    private String extField1;

    @Schema(description = "扩展字段2")
    @ExcelProperty("扩展字段2")
    private String extField2;

    @Schema(description = "扩展字段3")
    @ExcelProperty("扩展字段3")
    private String extField3;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}