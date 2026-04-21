package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo;

import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.FlexibleTimestampDeserializer;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用户车辆分页 Request VO")
@Data
public class UserCarPageReqVO extends PageParam {

    @Schema(description = "用户信息ID", example = "1")
    private Long userId;

    @TableField(exist = false)
    @Schema(description = "用户姓名", example = "张三")
    private String nickname;

    @Schema(description = "车牌号码", example = "闽 C12345")
    private String plateNo;

    @Schema(description = "车牌颜色：蓝牌/黄牌/绿牌/黑牌/白牌", example = "蓝牌")
    private String plateColor;

    @Schema(description = "车辆类型：小型车/大型车/新能源/其他", example = "小型车")
    private String carType;

    @Schema(description = "绑定时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime[] bindTime;

    @Schema(description = "绑定状态：待审核/已绑定/已解绑", example = "已绑定")
    private String status;

    @Schema(description = "审核人ID", example = "1")
    private Long auditorId;

    @Schema(description = "审核时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime[] auditTime;

    @Schema(description = "备注", example = "用户自有车辆")
    private String remark;

}