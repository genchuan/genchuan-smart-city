package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.monitorparttype.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 监测部件类别分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class monitorPartTypePageReqVO extends PageParam {

    @Schema(description = "上级监测类别ID", example = "3079")
    private Long parentTypeId;

    @Schema(description = "唯一类别编码")
    private String typeCode;

    @Schema(description = "类别名称", example = "赵六")
    private String typeName;

    @Schema(description = "核心监测指标")
    private String monitorIndices;

    @Schema(description = "数据类型：状态型/数值型/事件型", example = "2")
    private String dataType;

    @Schema(description = "采集周期（秒）")
    private Integer collectionCycle;

    @Schema(description = "所属业务域：设备运维域/停车资源域")
    private String bizDomain;

    @Schema(description = "状态：启用/停用", example = "1")
    private String typeStatus;

    @Schema(description = "业务备注", example = "你说的对")
    private String typeRemark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}