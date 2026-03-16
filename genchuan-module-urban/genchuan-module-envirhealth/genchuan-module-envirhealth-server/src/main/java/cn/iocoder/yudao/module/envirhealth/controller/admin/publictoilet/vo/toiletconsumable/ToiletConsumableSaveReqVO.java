package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletconsumable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 公厕耗材配置新增/修改 Request VO")
@Data
public class ToiletConsumableSaveReqVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "公厕ID，关联public_toilet.id", example = "32743")
    private String toiletId;

    @Schema(description = "耗材ID，关联sys_consumable.id", example = "4894")
    private String consumableId;

    @Schema(description = "当前库存数量")
    private Integer consumableStock;

    @Schema(description = "预警阈值")
    private Integer consumableThreshold;

    @Schema(description = "预警状态：正常/预警/严重预警")
    private String consumableWarning;

    @Schema(description = "上次补充时间")
    private LocalDateTime lastSupplyTime;

    @Schema(description = "补充照片")
    private String photoUrls;

    @Schema(description = "补充周期，单位：天")
    private Integer supplyCycle;

    @Schema(description = "缺口数量")
    private Integer consumableGap;

    @Schema(description = "负责人ID，关联sys_user.id，负责该耗材的管理和补充", example = "14418")
    private String managerId;

}