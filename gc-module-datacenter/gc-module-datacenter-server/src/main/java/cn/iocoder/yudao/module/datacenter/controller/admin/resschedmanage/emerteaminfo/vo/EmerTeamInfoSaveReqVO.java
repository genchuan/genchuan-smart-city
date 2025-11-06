package cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteaminfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 救援队伍信息新增/修改 Request VO")
@Data
public class EmerTeamInfoSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1099")
    private Long id;

    @Schema(description = "队伍ID，唯一编码，UUID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32221")
    @NotEmpty(message = "队伍ID，唯一编码，UUID不能为空")
    private String teamId;

    @Schema(description = "队伍编码，系统自动生成", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "队伍编码，系统自动生成不能为空")
    private String teamCode;

    @Schema(description = "队伍名称，标准名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "队伍名称，标准名称不能为空")
    private String teamName;

    @Schema(description = "分类ID，关联救援队伍分类配置表", requiredMode = Schema.RequiredMode.REQUIRED, example = "30131")
    @NotEmpty(message = "分类ID，关联救援队伍分类配置表不能为空")
    private String catId;

    @Schema(description = "分类名称，与分类ID同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "分类名称，与分类ID同步不能为空")
    private String catName;

    @Schema(description = "适用区域代码，关联行政区划表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "适用区域代码，关联行政区划表不能为空")
    private String applyRegionCode;

    @Schema(description = "适用区域名称，与区域代码同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "适用区域名称，与区域代码同步不能为空")
    private String applyRegionName;

    @Schema(description = "队伍人数，队伍的总人数", requiredMode = Schema.RequiredMode.REQUIRED, example = "4905")
    @NotNull(message = "队伍人数，队伍的总人数不能为空")
    private Integer teamMemberCount;

    @Schema(description = "可用人数，可参与救援的队员数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "7717")
    @NotNull(message = "可用人数，可参与救援的队员数量不能为空")
    private Integer availableMemberCount;

    @Schema(description = "队伍状态，1（正常）/0（异常），默认1", example = "2")
    private String teamStatus;

    @Schema(description = "创建人，创建人账号，关联用户信息表(sys_user)")
    private String createUser;

    @Schema(description = "更新人，更新人账号，关联用户信息表(sys_user)")
    private String updateUser;

}