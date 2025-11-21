package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectpersoninfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查巡检人员信息新增/修改 Request VO")
@Data
public class InspectPersonInfoSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "人员ID")
    private String personId;

    @Schema(description = "人员姓名")
    private String personName;

    @Schema(description = "人员账号")
    private String personAccount;

    @Schema(description = "所属部门代码")
    private String deptCode;

    @Schema(description = "所属部门名称")
    private String deptName;

    @Schema(description = "联系电话")
    private String personPhone;

    @Schema(description = "负责网格ID")
    private String gridId;

    @Schema(description = "负责网格名称")
    private String gridName;

    @Schema(description = "人员角色")
    private String personRole;

    @Schema(description = "关联设备编码")
    private String deviceCode;

    @Schema(description = "人员状态")
    private Boolean personStatus;

    @Schema(description = "入职日期")
    private LocalDate entryDate;

    @Schema(description = "创建人")
    private String createUser;

    @Schema(description = "创建时间（业务）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新人")
    private String updateUser;

    @Schema(description = "更新时间（业务）")
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