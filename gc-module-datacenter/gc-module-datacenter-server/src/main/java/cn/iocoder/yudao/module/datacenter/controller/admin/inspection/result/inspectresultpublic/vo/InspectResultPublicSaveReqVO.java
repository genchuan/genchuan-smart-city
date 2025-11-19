package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresultpublic.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查巡检结果公示新增/修改 Request VO")
@Data
public class InspectResultPublicSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "公示ID")
    private String publicId;

    @Schema(description = "公示编码")
    private String publicCode;

    @Schema(description = "公示标题")
    private String publicTitle;

    @Schema(description = "公示类型")
    private String publicType;

    @Schema(description = "所属区域代码")
    private String areaCode;

    @Schema(description = "所属区域名称")
    private String areaName;

    @Schema(description = "公示内容")
    private String publicContent;

    @Schema(description = "公示附件URL")
    private String publicAttachUrl;

    @Schema(description = "公示开始时间")
    private LocalDateTime publicStartTime;

    @Schema(description = "公示结束时间")
    private LocalDateTime publicEndTime;

    @Schema(description = "公示状态")
    private String publicStatus;

    @Schema(description = "公示说明")
    private String publicDesc;

    @Schema(description = "创建人")
    private String createUser;

    @Schema(description = "创建时间")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新人")
    private String updateUser;

    @Schema(description = "更新时间")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}