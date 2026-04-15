package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo;

import cn.iocoder.yudao.module.usermerchant.framework.FlexibleTimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用户车辆新增/修改 Request VO")
@Data
public class UserCarCreateReqVO {

    @Schema(description = "用户信息ID", example = "1")
    private Long userId;

    @Schema(description = "车牌号码", example = "闽 C12345")
    private String plateNo;

    @Schema(description = "车牌颜色：蓝牌/黄牌/绿牌/黑牌/白牌", example = "蓝牌")
    private String plateColor;

    @Schema(description = "车辆类型：小型车/大型车/新能源/其他", example = "小型车")
    private String carType;

    @Schema(description = "绑定时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime bindTime;

    @Schema(description = "绑定状态：待审核/已绑定/已解绑", example = "待审核")
    private String status;

    @Schema(description = "备注", example = "用户自有车辆")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}