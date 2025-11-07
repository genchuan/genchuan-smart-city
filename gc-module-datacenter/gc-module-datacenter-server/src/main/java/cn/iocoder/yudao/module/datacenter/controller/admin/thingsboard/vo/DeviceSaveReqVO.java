package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
        import java.util.*;
        import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 设备新增/修改 Request VO")
@Data
public class DeviceSaveReqVO {

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19775")
    private String id;

    @Schema(description = "TB租户", requiredMode = Schema.RequiredMode.REQUIRED, example = "10102")
    @NotEmpty(message = "TB租户不能为空")
    private String tbTenantId;

    @Schema(description = "客户ID", example = "4677")
    private String customerId;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "设备名称不能为空")
    private String name;

    @Schema(description = "设备类型", example = "2")
    private String type;

    @Schema(description = "标签")
    private String label;

    @Schema(description = "设备实体ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28197")
    @NotEmpty(message = "设备实体ID不能为空")
    private String deviceProfileId;

    @Schema(description = "设备配置")
    private byte[] deviceData;

    @Schema(description = "firmware", example = "7123")
    private String firmwareId;

    @Schema(description = "software", example = "23691")
    private String softwareId;

    @Schema(description = "Additional", example = "21772")
    private String externalId;

    @Schema(description = "版本")
    private Long version;

}