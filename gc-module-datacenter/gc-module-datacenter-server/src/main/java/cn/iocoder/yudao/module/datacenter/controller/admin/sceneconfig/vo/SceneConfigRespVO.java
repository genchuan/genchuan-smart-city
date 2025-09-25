package cn.iocoder.yudao.module.datacenter.controller.admin.sceneconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 场景分类 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SceneConfigRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "父级ID")
    @ExcelProperty("父级ID")
    private Long pid;

    @Schema(description = "场景名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("场景名称")
    private String name;

    @Schema(description = "设备配置名称")
    @ExcelProperty("设备配置名称")
    private String deviceConfigName;

    @Schema(description = "资产配置名称")
    @ExcelProperty("资产配置名称")
    private String assetConfigName;

    @Schema(description = "流程配置名称")
    @ExcelProperty("流程配置名称")
    private String flowConfigName;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String info;

    @Schema(description = "备用1")
    @ExcelProperty("备用1")
    private String info1;

    @Schema(description = "备用2")
    @ExcelProperty("备用2")
    private String info2;

    @Schema(description = "备用3")
    @ExcelProperty("备用3")
    private String info3;

    @Schema(description = "备用4")
    @ExcelProperty("备用4")
    private String info4;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}