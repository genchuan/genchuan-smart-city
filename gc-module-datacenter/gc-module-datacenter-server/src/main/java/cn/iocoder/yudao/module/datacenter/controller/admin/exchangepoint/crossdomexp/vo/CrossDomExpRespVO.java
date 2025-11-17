package cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.crossdomexp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 跨域经验交流 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CrossDomExpRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12454")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "经验ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "21164")
    @ExcelProperty("经验ID，唯一编码，UUID生成")
    private String crossDomExpId;

    @Schema(description = "经验标题，如“上海一网统管平台设备联动经验”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("经验标题，如“上海一网统管平台设备联动经验”")
    private String expTitle;

    @Schema(description = "经验领域代码，取值如01=设备联动，02=数据融合，03=事件处置，经验领域字典表（sys_dict_exp_field）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("经验领域代码，取值如01=设备联动，02=数据融合，03=事件处置，经验领域字典表（sys_dict_exp_field）")
    private String expFieldCode;

    @Schema(description = "经验领域名称，与经验领域代码同步，经验领域字典表（sys_dict_exp_field）", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("经验领域名称，与经验领域代码同步，经验领域字典表（sys_dict_exp_field）")
    private String expFieldName;

    @Schema(description = "来源城市，如“上海、深圳”，行政区划表（sys_area）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("来源城市，如“上海、深圳”，行政区划表（sys_area）")
    private String sourceCity;

    @Schema(description = "经验详细内容（支持富文本，如“1.设备联动：统一物联网设备协议；2.数据融合：建立跨部门数据中台”）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("经验详细内容（支持富文本，如“1.设备联动：统一物联网设备协议；2.数据融合：建立跨部门数据中台”）")
    private String expDetail;

    @Schema(description = "适用场景，经验适用场景（如“设备数量超10万套的大型城市”）")
    @ExcelProperty("适用场景，经验适用场景（如“设备数量超10万套的大型城市”）")
    private String applicableScenario;

    @Schema(description = "附件ID，关联经验附件ID（如方案文档、演示视频），文件存储表（sys_file_storage）", example = "7079")
    @ExcelProperty("附件ID，关联经验附件ID（如方案文档、演示视频），文件存储表（sys_file_storage）")
    private String attachId;

    @Schema(description = "附件路径，附件存储路径，文件存储表（sys_file_storage）")
    @ExcelProperty("附件路径，附件存储路径，文件存储表（sys_file_storage）")
    private String attachPath;

    @Schema(description = "发布状态：已发布/草稿，标识经验是否公开", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("发布状态：已发布/草稿，标识经验是否公开")
    private String publishStatus;

    @Schema(description = "浏览次数，用户浏览经验的次数，默认0", requiredMode = Schema.RequiredMode.REQUIRED, example = "1241")
    @ExcelProperty("浏览次数，用户浏览经验的次数，默认0")
    private Integer viewCount;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人，创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间，格式：yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    @ExcelProperty("更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“经验等级：优秀/良好”")
    @ExcelProperty("分类扩展字段1，预留，如“经验等级：优秀/良好”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“发布渠道：行业会议/官方推荐”")
    @ExcelProperty("分类扩展字段2，预留，如“发布渠道：行业会议/官方推荐”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“关联政策：GB/T 30428.1”")
    @ExcelProperty("通用扩展字段1，预留，如“关联政策：GB/T 30428.1”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“引用次数：5次”")
    @ExcelProperty("通用扩展字段2，预留，如“引用次数：5次”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}