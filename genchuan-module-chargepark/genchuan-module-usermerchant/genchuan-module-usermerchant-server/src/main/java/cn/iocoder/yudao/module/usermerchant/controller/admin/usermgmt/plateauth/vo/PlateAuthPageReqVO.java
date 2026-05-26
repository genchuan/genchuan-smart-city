package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.plateauth.vo;

import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.FlexibleTimestampDeserializer;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 车牌认证分页 Request VO")
@Data
public class PlateAuthPageReqVO extends PageParam {

    @Schema(description = "所属用户ID，关联 user_info 表 id", example = "1")
    private Long userId;

    @TableField(exist = false)
    @Schema(description = "用户姓名", example = "张三")
    private String nickname;

    @Schema(description = "关联车辆ID，关联 user_car 表 id", example = "1")
    private Long carId;

    @Schema(description = "车牌号码", example = "闽 C12345")
    private String plateNo;

    @Schema(description = "认证申请时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime[] applyTime;

    @Schema(description = "认证状态：待审核/已认证/已驳回，关联芋道字典表 plate_auth_status", example = "待审核")
    private String status;

    @Schema(description = "审核人ID，关联芋道用户表 system_user", example = "1")
    private Long auditorId;

    @TableField(exist = false)
    @Schema(description = "审核人姓名", example = "admin")
    private String auditorName;

    @Schema(description = "审核时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime[] auditTime;

    @Schema(description = "审核备注")
    private String auditRemark;

    @Schema(description = "备注", example = "新车认证申请")
    private String remark;

}