package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.localregulation.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * 管理后台 - 地方法规信息 Response VO
 *
 * @author Gyh
 */
@Data
@Schema(description = "管理后台 - 地方法规信息 Response VO")
public class LocalRegulationInfoRespVO {

    @Schema(description = "主键ID", example = "1024")
    private Long id;

    @Schema(description = "法规ID，唯一编码，采用UUID", example = "550e8400-e29b-41d4-a716-446655440000")
    private String regulationId;

    @Schema(description = "法规编号", example = "XX省人民政府令〔2025〕1号")
    private String regulationNo;

    @Schema(description = "法规完整名称", example = "XX省智慧城市建设管理办法")
    private String regulationName;

    @Schema(description = "法规类型", example = "政府规章")
    private String regulationType;

    @Schema(description = "所属行政区划代码", example = "330100")
    private String adminCode;

    @Schema(description = "所属行政区划名称", example = "浙江省杭州市")
    private String adminName;

    @Schema(description = "发布单位", example = "XX省人民政府")
    private String issueDept;

    @Schema(description = "发布时间")
    private LocalDateTime issueTime;

    @Schema(description = "生效时间")
    private LocalDate effectiveTime;

    @Schema(description = "失效时间")
    private LocalDate expireTime;

    @Schema(description = "修订依据")
    private String revisionBasis;

    @Schema(description = "关联依据的国家政策ID")
    private String relatedNationalPolicyId;

    @Schema(description = "法规原文存储路径")
    private String regulationFilePath;

    @Schema(description = "法规摘要")
    private String regulationAbstract;

    @Schema(description = "法规状态", example = "有效")
    private String regulationStatus;

    @Schema(description = "上传人")
    private String uploadUserId;

    @Schema(description = "上传时间")
    private LocalDateTime uploadTime;

    @Schema(description = "更新人")
    private String updateUserId;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "扩展分类字段")
    private String extendCategory;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}