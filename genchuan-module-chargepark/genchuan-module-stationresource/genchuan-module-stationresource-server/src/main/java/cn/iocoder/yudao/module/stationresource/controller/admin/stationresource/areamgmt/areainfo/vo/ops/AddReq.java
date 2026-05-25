package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.ops;

import cn.idev.excel.annotation.ExcelIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 片区信息新增/修改 Request VO")
@Data
public class AddReq {


    @Schema(description = "[片区编号] ", requiredMode = Schema.RequiredMode.REQUIRED, example = "AREA-20250601-001")
    @NotEmpty(message = "[片区编号]")
    private String areaNo;

    @Schema(description = "[片区名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "泉州丰泽片区")
    @NotEmpty(message = "[片区名称] 片区名称不能为空")
    private String name;

//    @Schema(description = "[上级片区ID] 可以为null，表示顶层", example = "1001")
//    private Long parentId;

//    @Schema(description = "[省份] 省份", requiredMode = Schema.RequiredMode.REQUIRED, example = "福建省")
//    @NotEmpty(message = "[省份] 省份不能为空")
//    private String province;

//    @Schema(description = "[城市] 城市", requiredMode = Schema.RequiredMode.REQUIRED, example = "泉州市")
//    @NotEmpty(message = "[城市] 城市不能为空")
//    private String city;

    @Schema(description = "[所属行政区划]", requiredMode = Schema.RequiredMode.REQUIRED, example = "丰泽区")
    @NotEmpty(message = "[所属行政区划] 所属行政区划不能为空")
    private String district;

//    @Schema(description = "[详细地址] 详细地址", example = "福建省泉州市丰泽区滨海街100号")
//    private String address;

//    @Schema(description = "[负责人ID] 关联芋道用户表system_user", example = "10001")
//    private Long leaderId;
//    @Schema(description = "[负责人名称] 片区名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张得法")
////    @NotEmpty(message = "[负责人名称] 负责人名称不能为空")
//    private String leaderName;

    @Schema(description = "[联系电话]", example = "13800138000")
    private String phone;

    // 后端自己生成
    @Schema(description = "[状态] 如:未生效/已生效/已禁用", example = "未生效", hidden = true)
    private String status;

    @Schema(description = "[备注]", example = "核心城区充电片区")
    private String remark;

    @Schema(description = "[备用字段1]", example = "备用信息1")
    @ExcelIgnore
    private String reserve1;

    @Schema(description = "[备用字段2]", example = "备用信息2")
    @ExcelIgnore
    private String reserve2;

    @Schema(description = "[负责人ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "负责人ID不能为空")
    private Long userId;

    // ==================== 后端自动赋值字段 ====================
    @Schema(description = "[绑定时间]", hidden = true)
    @ExcelIgnore
    private LocalDateTime bindTime;

    @Schema(description = "[绑定人ID]", hidden = true)
    @ExcelIgnore
    private Long bindUserId;

    /**
     * 经度
     */
    @Schema(description = "[经度] ", example = "111")
    @ExcelIgnore
    private Double lon;

    /**
     * 纬度
     */
    @Schema(description = "[纬度] ", example = "111")
    @ExcelIgnore
    private Double lat;
}
