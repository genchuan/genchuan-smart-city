package cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegaltypedict.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 违规类型字典分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IllegalTypeDictPageReqVO extends PageParam {

    @Schema(description = "[分类编码]对应AI场景告警字典的告警类型编码(alertType不唯一)")
    private String typeCategory;

    @Schema(description = "[违规类型唯一编码] 对应AI场景告警字典的算法编码（aiAbilityCode唯一）")
    private String typeCode;

    @Schema(description = "[违规类型名称] 对应AI场景告警字典的场景名称", example = "李四")
    private String typeName;

    @Schema(description = "[违法行为说明]补充type_name说明", example = "随便")
    private String illegalBehaviorDescription;

    @Schema(description = "[告警设备说明]对应AI场景告警字典的告警设备说明", example = "你猜")
    private String alarmDeviceDescription;

    @Schema(description = "[排序序号] 数值越小越靠前")
    private Integer sort;

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
