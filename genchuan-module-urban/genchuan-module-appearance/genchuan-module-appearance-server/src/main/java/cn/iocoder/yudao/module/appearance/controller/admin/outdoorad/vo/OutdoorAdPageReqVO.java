package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 户外广告分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class OutdoorAdPageReqVO extends PageParam {

    @Schema(description = "广告名称（模糊查询）", example = "商业广场广告")
    private String adName;

    @Schema(description = "广告类型（精确匹配）", example = "电子屏")
    private String adType;

    @Schema(description = "所属区域编码（精确匹配）", example = "350105")
    private String areaCode;

    @Schema(description = "所属网格编码（精确匹配）", example = "GRID-350105001-001")
    private String gridCode;

    @Schema(description = "审批状态（精确匹配）", example = "已审批")
    private String approvalStatus;

    @Schema(description = "数据状态", example = "1")
    private Integer dataStatus;

    @Schema(description = "审批开始时间", example = "2026-03-01 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startApprovalTime;

    @Schema(description = "审批结束时间", example = "2026-03-31 23:59:59")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endApprovalTime;
}