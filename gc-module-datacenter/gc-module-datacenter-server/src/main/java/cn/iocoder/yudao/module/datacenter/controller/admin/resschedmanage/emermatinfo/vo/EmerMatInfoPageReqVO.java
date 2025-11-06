package cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatinfo.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 应急物资信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmerMatInfoPageReqVO extends PageParam {

    @Schema(description = "物资ID，唯一编码，UUID", example = "9092")
    private String matId;

    @Schema(description = "物资编码，系统自动生成")
    private String matCode;

    @Schema(description = "物资名称，标准名称", example = "王五")
    private String matName;

    @Schema(description = "分类ID，关联应急物资分类配置表", example = "15435")
    private String catId;

    @Schema(description = "分类名称，与分类ID同步", example = "芋艿")
    private String catName;

    @Schema(description = "适用区域代码，关联行政区划表")
    private String applyRegionCode;

    @Schema(description = "适用区域名称，与区域代码同步", example = "赵六")
    private String applyRegionName;

    @Schema(description = "物资规格，如“5kg装”")
    private String matSpec;

    @Schema(description = "物资单位，如“个”“箱”")
    private String matUnit;

    @Schema(description = "总数量，物资的总库存数量")
    private Integer totalQuantity;

    @Schema(description = "可用数量，正常状态可使用的物资数量")
    private Integer availableQuantity;

    @Schema(description = "物资状态，1（正常）/0（异常），默认1", example = "1")
    private String matStatus;

    @Schema(description = "创建人，创建人账号，关联用户信息表(sys_user)")
    private String createUser;

    @Schema(description = "创建时间，格式yyyy - MM - dd HH:mm:ss，系统自动生成")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人，更新人账号，关联用户信息表(sys_user)")
    private String updateUser;

}