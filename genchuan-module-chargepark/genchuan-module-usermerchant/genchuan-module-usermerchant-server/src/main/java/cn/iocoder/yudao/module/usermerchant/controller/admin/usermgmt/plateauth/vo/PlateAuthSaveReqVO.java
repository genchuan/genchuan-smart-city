package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.plateauth.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 车牌认证新增/修改 Request VO")
@Data
public class PlateAuthSaveReqVO {

    @Schema(description = "主键ID", example = "1")
    private Long id;

    @Schema(description = "所属用户ID，关联 user_info 表 id", example = "1")
    private Long userId;

    @Schema(description = "关联车辆ID，关联 user_car 表 id", example = "1")
    private Long carId;

    @Schema(description = "车牌号码", example = "闽 C12345")
    private String plateNo;

    @Schema(description = "行驶证图片地址", example = "/genchuan/chargePark/userMerchant/driving/123.jpg")
    private String drivingLicense;

    @Schema(description = "认证申请时间")
    private LocalDateTime applyTime;

    @Schema(description = "认证状态：待审核/已认证/已驳回，关联芋道字典表 plate_auth_status", example = "待审核")
    private String status;

    @Schema(description = "审核人ID，关联芋道用户表 system_user", example = "1")
    private Long auditorId;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核备注")
    private String auditRemark;

    @Schema(description = "备注", example = "新车认证申请")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @TableField(exist = false)
    @Schema(description = "主键ID组")
    private List<Long> ids;

    @TableField(exist = false)
    @Schema(description = "审核结果")
    private String auditResult;
}