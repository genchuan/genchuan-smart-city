package cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdicttype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 业务字典分类 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BizDictTypeRespVO {

    @Schema(description = "[主键ID] 字典分类唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "17461")
    @ExcelProperty("[主键ID] 字典分类唯一标识")
    private Long id;

    @Schema(description = "[类型编码] 如：sex、status", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[类型编码] 如：sex、status")
    private String uniCode;

    @Schema(description = "[类型名称] 如：性别、状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("[类型名称] 如：性别、状态")
    private String name;

    @Schema(description = "[分类排序]")
    @ExcelProperty("[分类排序]")
    private Integer sort;

    @Schema(description = "[类型描述] 字典分类的详细说明", example = "你说的对")
    @ExcelProperty("[类型描述] 字典分类的详细说明")
    private String description;

    @Schema(description = "[分类备注] 如：“性别字典，用于用户表性别字段”", example = "随便")
    @ExcelProperty("[分类备注] 如：“性别字典，用于用户表性别字段”")
    private String remark;

    @Schema(description = "[状态]如:0-禁用/1-启用", example = "2")
    @ExcelProperty("[状态]如:0-禁用/1-启用")
    private Integer status;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1]")
    @ExcelProperty("[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    @ExcelProperty("[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    @ExcelProperty("[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    @ExcelProperty("[通用扩展字段4]")
    private String extCommon4;

}
