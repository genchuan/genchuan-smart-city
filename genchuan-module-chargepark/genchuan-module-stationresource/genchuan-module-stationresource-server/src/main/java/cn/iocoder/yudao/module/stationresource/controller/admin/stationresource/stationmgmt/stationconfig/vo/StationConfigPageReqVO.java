package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 场站配置分页 Request VO")
@Data
public class StationConfigPageReqVO extends PageParam {

    @Schema(description = "[所属场站id] ", example = "30533")
    private Long id;
    @Schema(description = "[所属场站] 关联场站信息表 station_info.id", example = "30533")
    private Long stationId;

    @Schema(description = "[配置类型] 如：通行规则/收费规则/联动规则，关联芋道字典表：station_config_type", example = "2")
    private String type;

    @Schema(description = "[配置内容] 配置内容JSON格式varchar")
    private String content;

    @Schema(description = "[状态] 如：未生效/已生效，关联芋道字典表：station_config_status", example = "2")
    private String status;

    @Schema(description = "[审核时间] 审核时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] auditTime;

    @Schema(description = "[审核人] 关联芋道用户表 system_user.id", example = "7419")
    private Long auditUserId;

    @Schema(description = "[同步时间] 同步时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] syncTime;

    @Schema(description = "[备注] 场站配置相关备注说明", example = "随便")
    private String remark;

    @Schema(description = "[备用字段1] 备用字段1")
    private String reserve1;

    @Schema(description = "[备用字段2] 备用字段2")
    private String reserve2;

    @Schema(description = "[创建者] 创建人账号/姓名")
    private String creator;

    @Schema(description = "[更新者] 更新人账号/姓名")
    private String updater;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[更新时间] 记录最后更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}
