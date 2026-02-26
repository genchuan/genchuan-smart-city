package cn.iocoder.yudao.module.envir.controller.admin.publictoilet.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 公厕新增/修改 Request VO")
@Data
public class PublicToiletSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32760")
    private Long id;

    @Schema(description = "业务主键（UUID）", example = "3287")
    private String publicToiletId;

    @Schema(description = "公厕名称", example = "张三")
    private String name;

    @Schema(description = "公厕位置（含经纬度）")
    private String location;

    @Schema(description = "所属区域（关联sys_area.area_code）")
    private String areaCode;

    @Schema(description = "开放时段")
    private String openHours;

    @Schema(description = "蹲位数量", example = "19862")
    private Integer stallCount;

    @Schema(description = "配套设施（关联sys_facility.sys_facility_id，多个用逗号分隔）")
    private String facilityIds;

    @Schema(description = "耗材字典表ID（多个用逗号分隔）")
    private String consumableIds;

    @Schema(description = "运营状态（关联sys_operation_status.sys_operation_status_id）", example = "5386")
    private String operationStatusId;

    @Schema(description = "负责人（关联sys_user.id）", example = "17876")
    private String managerId;

    @Schema(description = "业务创建人（关联sys_user.id）")
    private String abnormalCreateBy;

    @Schema(description = "业务创建时间")
    private LocalDateTime abnormalCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime abnormalUpdateTime;

    @Schema(description = "保洁达标率")
    private BigDecimal cleaningRate;

    @Schema(description = "耗材库存预警数", example = "7381")
    private Integer warningCount;

    @Schema(description = "投诉办结率")
    private BigDecimal complaintRate;

    @Schema(description = "公厕现场照片URL（多个用逗号分隔）", example = "https://www.iocoder.cn")
    private String photoUrl;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}