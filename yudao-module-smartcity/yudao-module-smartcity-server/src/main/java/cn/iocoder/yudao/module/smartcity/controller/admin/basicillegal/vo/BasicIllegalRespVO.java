package cn.iocoder.yudao.module.smartcity.controller.admin.basicillegal.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 违建基本信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BasicIllegalRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "22624")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "违建编号")
    @ExcelProperty("违建编号")
    private String buildingNumber;

    @Schema(description = "违建地址")
    @ExcelProperty("违建地址")
    private String buildingAddress;

    @Schema(description = "违建面积")
    @ExcelProperty("违建面积")
    private String constructionArea;

    @Schema(description = "违建结构类型")
    @ExcelProperty("违建结构类型")
    private String typesStructures;

    @Schema(description = "违建层数")
    @ExcelProperty("违建层数")
    private String numberFloors;

    @Schema(description = "违建用途")
    @ExcelProperty("违建用途")
    private String unauthorizedBuildings;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}