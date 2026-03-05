package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.apptype.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 行业应用类别 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AppTypeRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31490")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "上级应用类别ID", example = "6589")
    @ExcelProperty("上级应用类别ID")
    private Long parentTypeId;

    @Schema(description = "唯一应用编码")
    @ExcelProperty("唯一应用编码")
    private String appCode;

    @Schema(description = "应用名称", example = "张三")
    @ExcelProperty("应用名称")
    private String appName;

    @Schema(description = "所属业务域")
    @ExcelProperty("所属业务域")
    private String bizDomain;

    @Schema(description = "核心功能描述")
    @ExcelProperty("核心功能描述")
    private String functionDesc;

    @Schema(description = "访问权限编码")
    @ExcelProperty("访问权限编码")
    private String accessPermCode;

    @Schema(description = "状态：启用/停用", example = "2")
    @ExcelProperty("状态：启用/停用")
    private String appStatus;

    @Schema(description = "业务备注", example = "你猜")
    @ExcelProperty("业务备注")
    private String appRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
