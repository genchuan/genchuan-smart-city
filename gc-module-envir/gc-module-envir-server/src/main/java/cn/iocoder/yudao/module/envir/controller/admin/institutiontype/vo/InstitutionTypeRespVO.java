package cn.iocoder.yudao.module.envir.controller.admin.institutiontype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 机构类型字典 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InstitutionTypeRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23054")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "879")
    @ExcelProperty("业务主键（UUID）")
    private String sysInstitutionTypeId;

    @Schema(description = "类型名称（可选值：学校/医院/政府机关/公园/图书馆/体育馆/博物馆/车站）", example = "张三")
    @ExcelProperty("类型名称（可选值：学校/医院/政府机关/公园/图书馆/体育馆/博物馆/车站）")
    private String name;

    @Schema(description = "类型编码")
    @ExcelProperty("类型编码")
    private String code;

    @Schema(description = "状态（可选值：0-禁用/1-启用）", example = "2")
    @ExcelProperty("状态（可选值：0-禁用/1-启用）")
    private Integer status;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}