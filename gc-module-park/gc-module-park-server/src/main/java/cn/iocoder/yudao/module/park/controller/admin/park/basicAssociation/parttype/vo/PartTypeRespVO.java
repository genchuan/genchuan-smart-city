package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.parttype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 管理部件类别 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PartTypeRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9865")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "上级部件类别ID", example = "22716")
    @ExcelProperty("上级部件类别ID")
    private Long parentTypeId;

    @Schema(description = "唯一类别编码")
    @ExcelProperty("唯一类别编码")
    private String typeCode;

    @Schema(description = "类别名称", example = "芋艿")
    @ExcelProperty("类别名称")
    private String typeName;

    @Schema(description = "类别描述")
    @ExcelProperty("类别描述")
    private String typeDesc;

    @Schema(description = "所属业务域：基础关联域/停车资源域/设备运维域")
    @ExcelProperty("所属业务域：基础关联域/停车资源域/设备运维域")
    private String bizDomain;

    @Schema(description = "状态：启用/停用", example = "1")
    @ExcelProperty("状态：启用/停用")
    private String typeStatus;

    @Schema(description = "业务备注", example = "随便")
    @ExcelProperty("业务备注")
    private String typeRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}