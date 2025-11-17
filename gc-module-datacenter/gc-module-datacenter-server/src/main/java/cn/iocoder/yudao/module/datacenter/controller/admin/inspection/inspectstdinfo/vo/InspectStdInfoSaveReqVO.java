package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.inspectstdinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查巡检标准信息新增/修改 Request VO")
@Data
public class InspectStdInfoSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "标准ID")
    private String stdId;

    @Schema(description = "标准名称")
    private String stdName;

    @Schema(description = "标准编码")
    private String stdCode;

    @Schema(description = "标准类型")
    private String stdType;

    @Schema(description = "适用区域代码")
    private String areaCode;

    @Schema(description = "适用区域名称")
    private String areaName;

    @Schema(description = "检查项列表")
    private String ckItemList;

    @Schema(description = "评分规则")
    private String scoreRule;

    @Schema(description = "合格阈值")
    private Integer qualifiedThreshold;

    @Schema(description = "标准状态")
    private Boolean stdStatus;

    @Schema(description = "生效时间")
    private LocalDate effectiveTime;

    @Schema(description = "失效时间")
    private LocalDate expireTime;

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