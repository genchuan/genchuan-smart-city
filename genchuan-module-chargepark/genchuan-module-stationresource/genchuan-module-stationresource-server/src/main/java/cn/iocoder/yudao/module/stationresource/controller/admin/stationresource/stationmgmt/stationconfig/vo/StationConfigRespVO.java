package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 场站配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StationConfigRespVO {

    @Schema(description = "[主键ID] 主键，场站配置记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "13890")
    @ExcelProperty("[主键ID] 主键，场站配置记录唯一标识")
    private Long id;

    @Schema(description = "[所属场站] 关联场站信息表 station_info.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "30533")
    @ExcelProperty("[所属场站] 关联场站信息表 station_info.id")
    private Long stationId;

    @Schema(description = "[配置类型] 如：通行规则/收费规则/联动规则，关联芋道字典表：station_config_type", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[配置类型] 如：通行规则/收费规则/联动规则，关联芋道字典表：station_config_type")
    private String type;

    @Schema(description = "[配置内容] 配置内容JSON格式varchar", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[配置内容] 配置内容JSON格式varchar")
    private String content;

    @Schema(description = "[状态] 如：未生效/已生效，关联芋道字典表：station_config_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[状态] 如：未生效/已生效，关联芋道字典表：station_config_status")
    private String status;

    @Schema(description = "[审核时间] 审核时间")
    @ExcelProperty("[审核时间] 审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "[审核人] 关联芋道用户表 system_user.id", example = "7419")
    @ExcelProperty("[审核人] 关联芋道用户表 system_user.id")
    private Long auditUserId;

    @Schema(description = "[同步时间] 同步时间")
    @ExcelProperty("[同步时间] 同步时间")
    private LocalDateTime syncTime;

    @Schema(description = "[备注] 场站配置相关备注说明", example = "随便")
    @ExcelProperty("[备注] 场站配置相关备注说明")
    private String remark;

    @Schema(description = "[备用字段1] 备用字段1")
    @ExcelProperty("[备用字段1] 备用字段1")
    private String reserve1;

    @Schema(description = "[备用字段2] 备用字段2")
    @ExcelProperty("[备用字段2] 备用字段2")
    private String reserve2;

    @Schema(description = "[创建者] 创建人账号/姓名")
    @ExcelProperty("[创建者] 创建人账号/姓名")
    private String creator;

    @Schema(description = "[更新者] 更新人账号/姓名")
    @ExcelProperty("[更新者] 更新人账号/姓名")
    private String updater;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[更新时间] 记录最后更新时间")
    @ExcelProperty("[更新时间] 记录最后更新时间")
    private LocalDateTime updateTime;

}
