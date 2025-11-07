package cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteaminfo.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 救援队伍信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmerTeamInfoPageReqVO extends PageParam {

    @Schema(description = "队伍ID，唯一编码，UUID", example = "32221")
    private String teamId;

    @Schema(description = "队伍编码，系统自动生成")
    private String teamCode;

    @Schema(description = "队伍名称，标准名称", example = "王五")
    private String teamName;

    @Schema(description = "分类ID，关联救援队伍分类配置表", example = "30131")
    private String catId;

    @Schema(description = "分类名称，与分类ID同步", example = "芋艿")
    private String catName;

    @Schema(description = "适用区域代码，关联行政区划表")
    private String applyRegionCode;

    @Schema(description = "适用区域名称，与区域代码同步", example = "张三")
    private String applyRegionName;

    @Schema(description = "队伍人数，队伍的总人数", example = "4905")
    private Integer teamMemberCount;

    @Schema(description = "可用人数，可参与救援的队员数量", example = "7717")
    private Integer availableMemberCount;

    @Schema(description = "队伍状态，1（正常）/0（异常），默认1", example = "2")
    private String teamStatus;

    @Schema(description = "创建人，创建人账号，关联用户信息表(sys_user)")
    private String createUser;

    @Schema(description = "创建时间，格式yyyy - MM - dd HH:mm:ss，系统自动生成")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人，更新人账号，关联用户信息表(sys_user)")
    private String updateUser;

}