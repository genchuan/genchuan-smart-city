package cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopunitinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 联动单位信息新增/修改 Request VO")
@Data
public class CoopUnitInfoSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13860")
    private Long id;

    @Schema(description = "单位ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "16083")
    @NotEmpty(message = "单位ID，唯一编码，UUID生成不能为空")
    private String unitId;

    @Schema(description = "单位编码，系统自动生成，格式“UNIT+年份（4位）+6位流水号”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "单位编码，系统自动生成，格式“UNIT+年份（4位）+6位流水号”不能为空")
    private String unitCode;

    @Schema(description = "单位名称，如“XX市消防救援支队XX中队”", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "单位名称，如“XX市消防救援支队XX中队”不能为空")
    private String unitName;

    @Schema(description = "单位类型，政府部门/企业/社会组织", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "单位类型，政府部门/企业/社会组织不能为空")
    private String unitType;

    @Schema(description = "行政区划代码，关联行政区划表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划代码，关联行政区划表不能为空")
    private String regionCode;

    @Schema(description = "行政区划名称，与区域代码同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "行政区划名称，与区域代码同步不能为空")
    private String regionName;

    @Schema(description = "负责人姓名", example = "张三")
    private String leaderName;

    @Schema(description = "负责人联系方式，加密存储")
    private String leaderContact;

    @Schema(description = "单位详细地址")
    private String contactAddress;

    @Schema(description = "联动范围，如“火灾救援、建筑物坍塌救援”")
    private String coopScope;

    @Schema(description = "单位状态，启用/禁用，默认启用", example = "2")
    private String unitStatus;

    @Schema(description = "创建人账号，关联用户信息表")
    private String createUser;

    @Schema(description = "更新人账号，关联用户信息表")
    private String updateUser;

}