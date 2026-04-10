package cn.iocoder.yudao.module.studentmgmt.controller.admin.moralresource.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 德育资源新增/修改 Request VO")
@Data
public class MoralResourceSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28140")
    private Long id;

    @Schema(description = "资源名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "资源名称不能为空")
    private String resourceName;

    @Schema(description = "资源类型：课程/图书/专题包", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "资源类型：课程/图书/专题包不能为空")
    private String resourceType;

    @Schema(description = "资源地址", example = "https://www.iocoder.cn")
    private String resourceUrl;

    @Schema(description = "学习人数")
    private Integer learnNum;

    @Schema(description = "学习完成率")
    private BigDecimal learnRate;

    @Schema(description = "上架时间")
    private LocalDateTime publishTime;

    @Schema(description = "下架时间")
    private LocalDateTime offTime;

    @Schema(description = "状态：未上架/已上架", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "状态：未上架/已上架不能为空")
    private String status;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}