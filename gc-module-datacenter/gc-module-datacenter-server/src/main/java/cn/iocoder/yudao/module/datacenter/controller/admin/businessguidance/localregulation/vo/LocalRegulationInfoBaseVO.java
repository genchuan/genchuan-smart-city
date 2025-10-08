package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.localregulation.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Schema(description = "管理后台 - 地方法规信息基础 Request VO")
@Data
public class LocalRegulationInfoBaseVO {

    @Schema(description = "法规编号，如\"XX省人民政府令〔2025〕XX号\"", requiredMode = Schema.RequiredMode.REQUIRED, example = "XX省人民政府令〔2025〕1号")
    @NotBlank(message = "法规编号不能为空")
    @Size(max = 50, message = "法规编号长度不能超过50个字符")
    private String regulationNo;

    @Schema(description = "法规完整名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "XX省智慧城市建设管理办法")
    @NotBlank(message = "法规名称不能为空")
    @Size(max = 200, message = "法规名称长度不能超过200个字符")
    private String regulationName;

    @Schema(description = "法规类型，政府规章/地方性法规/规范性文件", requiredMode = Schema.RequiredMode.REQUIRED, example = "政府规章")
    @NotBlank(message = "法规类型不能为空")
    @Size(max = 30, message = "法规类型长度不能超过30个字符")
    private String regulationType;

    @Schema(description = "所属行政区划代码，符合GB/T 2260", requiredMode = Schema.RequiredMode.REQUIRED, example = "330100")
    @NotBlank(message = "行政区划代码不能为空")
    @Size(max = 6, message = "行政区划代码长度不能超过6个字符")
    private String adminCode;

    @Schema(description = "所属行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "浙江省杭州市")
    @NotBlank(message = "行政区划名称不能为空")
    @Size(max = 50, message = "行政区划名称长度不能超过50个字符")
    private String adminName;

    @Schema(description = "发布单位，如\"XX省人民政府\"\"XX市人大常委会\"", requiredMode = Schema.RequiredMode.REQUIRED, example = "XX省人民政府")
    @NotBlank(message = "发布单位不能为空")
    @Size(max = 100, message = "发布单位长度不能超过100个字符")
    private String issueDept;

    @Schema(description = "发布时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-01-01 00:00:00")
    @NotNull(message = "发布时间不能为空")
    private LocalDateTime issueTime;

    @Schema(description = "生效时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-01-01")
    @NotNull(message = "生效时间不能为空")
    private LocalDate effectiveTime;

    @Schema(description = "失效时间，永久有效则为空", example = "2025-12-31")
    private LocalDate expireTime;

    @Schema(description = "修订依据，如\"根据《XX国家政策》修订\"", example = "根据《关于推进智慧城市健康发展的指导意见》修订")
    @Size(max = 500, message = "修订依据长度不能超过500个字符")
    private String revisionBasis;

    @Schema(description = "关联依据的国家政策ID", example = "550e8400-e29b-41d4-a716-446655440000")
    @Size(max = 32, message = "国家政策ID长度不能超过32个字符")
    private String relatedNationalPolicyId;

    @Schema(description = "PDF格式原文存储路径", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "法规文件路径不能为空")
    @Size(max = 255, message = "法规文件路径长度不能超过255个字符")
    private String regulationFilePath;

    @Schema(description = "法规核心内容摘要", example = "明确智慧城市建设的5项重点任务及保障措施")
    @Size(max = 1000, message = "法规摘要长度不能超过1000个字符")
    private String regulationAbstract;

    @Schema(description = "法规状态，未生效/有效/已修订/已废止", requiredMode = Schema.RequiredMode.REQUIRED, example = "有效")
    @NotBlank(message = "法规状态不能为空")
    @Size(max = 10, message = "法规状态长度不能超过10个字符")
    private String regulationStatus;

    @Schema(description = "扩展分类字段，预留用于自定义标签", example = "地方特色法规")
    @Size(max = 30, message = "扩展分类字段长度不能超过30个字符")
    private String extendCategory;
}