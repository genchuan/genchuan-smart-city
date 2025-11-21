package cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicsvcinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 服务信息发布新增/修改 Request VO")
@Data
public class PublicSvcInfoSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20234")
    private Long id;

    @Schema(description = "服务信息ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "12759")
    @NotEmpty(message = "服务信息ID，唯一编码，UUID生成不能为空")
    private String svcInfoId;

    @Schema(description = "服务标题，明确服务类型，如“XX公交3路站点调整”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "服务标题，明确服务类型，如“XX公交3路站点调整”不能为空")
    private String svcTitle;

    @Schema(description = "服务类型编码，服务分类编码，如“交通”“医疗”，字典表（sys_dict）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "服务类型编码，服务分类编码，如“交通”“医疗”，字典表（sys_dict）不能为空")
    private String svcTypeCode;

    @Schema(description = "服务类型名称，与类型编码同步，字典表（sys_dict）", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "服务类型名称，与类型编码同步，字典表（sys_dict）不能为空")
    private String svcTypeName;

    @Schema(description = "服务内容，服务详情，如调整后的站点列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "服务内容，服务详情，如调整后的站点列表不能为空")
    private String svcContent;

    @Schema(description = "服务提供单位，提供服务的单位，如“XX公交公司”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "服务提供单位，提供服务的单位，如“XX公交公司”不能为空")
    private String svcProvider;

    @Schema(description = "联系电话，服务咨询电话，加密存储")
    private String contactPhone;

    @Schema(description = "生效时间，服务信息生效时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "生效时间，服务信息生效时间不能为空")
    private LocalDateTime effectiveTime;

    @Schema(description = "失效时间，服务信息失效时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "失效时间，服务信息失效时间不能为空")
    private LocalDateTime expireTime;

    @Schema(description = "发布人ID，发布人ID，用户表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "30361")
    @NotNull(message = "发布人ID，发布人ID，用户表（sys_user）不能为空")
    private Long publishUserId;

    @Schema(description = "发布时间，系统自动生成", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "发布时间，系统自动生成不能为空")
    private LocalDateTime publishTime;

    @Schema(description = "信息状态：1=草稿，2=已发布，3=已过期，控制展示", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "信息状态：1=草稿，2=已发布，3=已过期，控制展示不能为空")
    private Integer infoStatus;

    @Schema(description = "备注，补充说明，如“临时调整，有效期1个月”", example = "你猜")
    private String remark;

    @Schema(description = "分类扩展字段1，预留，标识服务级别，如常规/临时")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，标识服务区域，如全市/某区")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储服务地点，如“XX医院门诊楼”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储服务时间，如“每日8:00-17:00”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}