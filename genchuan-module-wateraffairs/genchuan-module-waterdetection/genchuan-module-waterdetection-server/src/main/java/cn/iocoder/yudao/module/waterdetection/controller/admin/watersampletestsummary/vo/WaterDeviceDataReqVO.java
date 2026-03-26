package cn.iocoder.yudao.module.waterdetection.controller.admin.watersampletestsummary.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 设备数据传输请求 VO
 */
@Data
public class WaterDeviceDataReqVO {

    @Schema(description = "仪器编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "b69a5f64cef9f3e3")
    @NotBlank(message = "仪器编号不能为空")
    private String yqbh;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @Schema(description = "检测单位", requiredMode = Schema.RequiredMode.REQUIRED, example = "检测单位")
    @NotBlank(message = "检测单位不能为空")
    private String dwmc;

    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED, example = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(description = "检测详情列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "检测详情不能为空")
    @Valid
    private List<DeviceDetailVO> details;

    @Data
    public static class DeviceDetailVO {

        @Schema(description = "样品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "样品")
        @NotBlank(message = "样品名称不能为空")
        private String yangpinmingcheng;

        @Schema(description = "检测结果", requiredMode = Schema.RequiredMode.REQUIRED, example = "合格")
        @NotBlank(message = "检测结果不能为空")
        private String jiancejieguo;

        @Schema(description = "联系电话", example = "电话")
        private String lianxidianhua;

        @Schema(description = "检测人", example = "检测人")
        private String jianceren;

        @Schema(description = "检测值", requiredMode = Schema.RequiredMode.REQUIRED, example = "10%")
        @NotBlank(message = "检测值不能为空")
        private String jiancezhi;

        @Schema(description = "检测地点", example = "检测单位")
        private String jiancedidian;

        @Schema(description = "检测项目", requiredMode = Schema.RequiredMode.REQUIRED, example = "pH值")
        @NotBlank(message = "检测项目不能为空")
        private String jiancexiangmu;

        @Schema(description = "样品编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "样品编号")
        @NotBlank(message = "样品编号不能为空")
        private String yangpinbianhao;

        @Schema(description = "检测日期", requiredMode = Schema.RequiredMode.REQUIRED, example = "2023-05-04 10:26:33")
        @NotBlank(message = "检测日期不能为空")
        private String jianceriqi;

        @Schema(description = "商户名称", example = "商户名称")
        private String shanghumingcheng;
    }
}