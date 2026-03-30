package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "环境卫生管理 - 公厕新增/修改 Request VO")
@Data
public class PublicToiletSaveReqVO {

    @Schema(description = "主键ID", example = "22794")
    private Long id;

    @Schema(description = "公厕编码", example = "uuid-toilet-001")
    private String toiletId;

    @Schema(description = "公厕名称", example = "中山公园公厕", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "公厕名称不能为空")
    private String name;

    @Schema(description = "公厕位置", example = "中山公园东门旁")
    private String location;

    @Schema(description = "所属区域编码", example = "1001")
    private String areaCode;

    @Schema(description = "开放时段", example = "06:00-22:00")
    private String openHours;

    @Schema(description = "蹲位数量", example = "12")
    @Min(value = 0, message = "蹲位数量不能小于0")
    private Integer stallCount;

    @Schema(description = "运营状态ID", example = "uuid-op-status-001")
    private String operationStatusId;

    @Schema(description = "负责人ID", example = "uuid-user-001")
    private String managerId;

    @Schema(description = "保洁达标率", example = "98.50")
    private BigDecimal cleaningRate;

    @Schema(description = "投诉办结率", example = "100.00")
    private BigDecimal complaintRate;

    @Schema(description = "设施完好率", example = "95.00")
    private BigDecimal facilityRate;

    @Schema(description = "耗材库存预警数", example = "2")
    @Min(value = 0, message = "耗材库存预警数不能小于0")
    private Integer warningCount;
}