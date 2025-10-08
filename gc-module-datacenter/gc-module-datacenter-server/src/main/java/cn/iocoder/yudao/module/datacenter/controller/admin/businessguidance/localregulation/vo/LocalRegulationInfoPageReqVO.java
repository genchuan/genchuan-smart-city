package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.localregulation.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDate;

/**
 * 管理后台 - 地方法规信息分页 Request VO
 *
 * @author Gyh
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "管理后台 - 地方法规信息分页 Request VO")
public class LocalRegulationInfoPageReqVO extends PageParam {

    @Schema(description = "法规名称", example = "智慧城市")
    private String regulationName;

    @Schema(description = "法规编号", example = "省人民政府令")
    private String regulationNo;

    @Schema(description = "法规类型", example = "政府规章")
    private String regulationType;

    @Schema(description = "法规状态", example = "有效")
    private String regulationStatus;

    @Schema(description = "行政区划代码", example = "330100")
    private String adminCode;

    @Schema(description = "行政区划名称", example = "杭州市")
    private String adminName;

    @Schema(description = "发布单位", example = "省人民政府")
    private String issueDept;

    @Schema(description = "开始发布时间")
    private LocalDate startIssueTime;

    @Schema(description = "结束发布时间")
    private LocalDate endIssueTime;

    @Schema(description = "开始生效时间")
    private LocalDate startEffectiveTime;

    @Schema(description = "结束生效时间")
    private LocalDate endEffectiveTime;
}