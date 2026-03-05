package cn.iocoder.yudao.module.park.controller.admin.park.user.address.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 地址 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AddressRespVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "5496")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[用户ID] 关联park_user.id，可为NULL", example = "27940")
    @ExcelProperty("[用户ID] 关联park_user.id，可为NULL")
    private Long userId;

    @Schema(description = "[企业ID] 关联park_enterprise_information.enterprise_id，可为NULL", example = "25420")
    @ExcelProperty("[企业ID] 关联park_enterprise_information.enterprise_id，可为NULL")
    private Long enterpriseId;

    @Schema(description = "[收货人姓名]", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("[收货人姓名]")
    private String receiverName;

    @Schema(description = "[联系电话]", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[联系电话]")
    private String phone;

    @Schema(description = "[省份]")
    @ExcelProperty("[省份]")
    private String province;

    @Schema(description = "[城市]")
    @ExcelProperty("[城市]")
    private String city;

    @Schema(description = "[区县]")
    @ExcelProperty("[区县]")
    private String district;

    @Schema(description = "[详细地址]", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[详细地址]")
    private String detailAddress;

    @Schema(description = "[是否默认地址] 如:0-否/1-是")
    @ExcelProperty("[是否默认地址] 如:0-否/1-是")
    private Boolean isDefault;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注]", example = "随便")
    @ExcelProperty("[备注]")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    @ExcelProperty("[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    @ExcelProperty("[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    @ExcelProperty("[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    @ExcelProperty("[通用扩展字段4]")
    private String extCommon4;

}
