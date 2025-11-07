package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evttypecfg.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 事件类型配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvtTypeCfgPageReqVO extends PageParam {

    @Schema(description = "类型ID，唯一编码，UUID", example = "1833")
    private String evtTypeId;

    @Schema(description = "父类型ID，关联本表“事件类型ID”，大类父ID为“0”", example = "25825")
    private String parentTypeId;

    @Schema(description = "类型层级，1（大类）/2（小类）")
    private String typeLevel;

    @Schema(description = "类型编码，001 - 999（大类01 - 99、小类001 - 999，扩展类080 - 999倒排），同一父类型下编码唯一")
    private String typeCode;

    @Schema(description = "类型名称，标准名称，扩展类加“（自定义）”", example = "王五")
    private String typeName;

    @Schema(description = "类型说明，描述类型范围，如“占道经营：商户违规占用道路经营”")
    private String typeDesc;

    @Schema(description = "所属区域代码，关联行政区划表，限定区域适用")
    private String regionCode;

    @Schema(description = "所属区域名称，与区域代码同步", example = "李四")
    private String regionName;

    @Schema(description = "启用状态，1（启用）/0（禁用），默认1", example = "2")
    private String enableStatus;

    @Schema(description = "创建人，创建人账号，关联用户信息表(sys_user)")
    private String createUser;

    @Schema(description = "创建时间，格式yyyy - MM - dd HH:mm:ss，系统自动生成")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人，更新人账号，关联用户信息表(sys_user)")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“处置时限”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“处置时限”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储参考标准")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储参考标准")
    private String extCommon2;

}