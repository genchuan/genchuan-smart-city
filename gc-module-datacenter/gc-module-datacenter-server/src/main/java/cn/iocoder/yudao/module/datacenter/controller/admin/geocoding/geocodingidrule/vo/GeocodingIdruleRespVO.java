package cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingidrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 标识码编码规则配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GeocodingIdruleRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "规则ID")
    @ExcelProperty("规则ID")
    private String idRuleId;

    @Schema(description = "规则名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("规则名称")
    private String ruleName;

    @Schema(description = "县级行政代码位数")
    @ExcelProperty("县级行政代码位数")
    private Integer adminCodeLength;

    @Schema(description = "街道代码位数")
    @ExcelProperty("街道代码位数")
    private Integer streetCodeLength;

    @Schema(description = "图层代码位数")
    @ExcelProperty("图层代码位数")
    private Integer layerCodeLength;

    @Schema(description = "顺序码位数")
    @ExcelProperty("顺序码位数")
    private Integer seqCodeLength;

    @Schema(description = "顺序码生成规则")
    @ExcelProperty("顺序码生成规则")
    private String seqGenRule;

    @Schema(description = "启用状态(0-禁用,1-启用)")
    @ExcelProperty("启用状态(0-禁用,1-启用)")
    private Integer enableStatus;

    @Schema(description = "配置人")
    @ExcelProperty("配置人")
    private String configUser;

    @Schema(description = "配置时间")
    @ExcelProperty("配置时间")
    private LocalDateTime configTime;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCategory1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCategory2;

    @Schema(description = "分类扩展字段3")
    @ExcelProperty("分类扩展字段3")
    private String extCategory3;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}