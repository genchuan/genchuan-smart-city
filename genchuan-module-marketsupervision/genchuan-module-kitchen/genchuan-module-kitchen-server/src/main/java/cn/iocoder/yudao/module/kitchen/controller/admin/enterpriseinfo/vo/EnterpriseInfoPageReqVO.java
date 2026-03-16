package cn.iocoder.yudao.module.kitchen.controller.admin.enterpriseinfo.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 企业信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EnterpriseInfoPageReqVO extends PageParam {

    @Schema(description = "[企业编码] 唯一编码")
    private String entCode;

    @Schema(description = "[企业名称] 企业全称", example = "赵六")
    private String entName;

    @Schema(description = "[所属区域ID] 关联area_dict.id", example = "24551")
    private Long areaId;

    @Schema(description = "[地区名] 冗余的地区名称", example = "李四")
    private String areaName;

    @Schema(description = "[企业类型ID] 关联ent_type_dict.id", example = "28435")
    private Long entTypeId;

    @Schema(description = "[详细地址] 企业注册或经营地址")
    private String address;

    @Schema(description = "[联系人] 企业联系人姓名")
    private String contactPerson;

    @Schema(description = "[联系电话] 企业联系电话")
    private String contactPhone;

    @Schema(description = "[企业经营状态] 如：正常/停业/注销", example = "2")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1] 预留")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 预留")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 预留")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 预留")
    private String extCommon4;

}
