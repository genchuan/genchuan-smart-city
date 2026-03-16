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

    @Schema(description = "[违规类型编码] 唯一编码")
    private String typeCode;

    @Schema(description = "[违规类型名称] 如：未佩戴工牌/未穿工作服/从业人员未持健康证/操作区卫生不达标/食材存放不规范/设备未定期检修/操作流程不规范", example = "李四")
    private String typeName;

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
