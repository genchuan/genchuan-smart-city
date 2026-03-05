package cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理事项实例新增/修改 Request VO")
@Data
public class matterInstanceSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3039")
    private Long id;

    @Schema(description = "管理事项实例ID", example = "9652")
    private String matterInstanceId;

    @Schema(description = "事项名称", example = "芋艿")
    private String name;

    @Schema(description = "16位标识码")
    private String uniqueCode;

    @Schema(description = "所属分类ID", example = "9172")
    private String categoryId;

    @Schema(description = "所属分类名称", example = "芋艿")
    private String categoryName;

    @Schema(description = "上级分类ID", example = "22607")
    private String parentCategoryId;

    @Schema(description = "事发位置")
    private String location;

    @Schema(description = "所在网格ID", example = "799")
    private String gridId;

    @Schema(description = "所在网格名称", example = "赵六")
    private String gridName;

    @Schema(description = "描述信息", example = "你说的对")
    private String description;

    @Schema(description = "状态ID", example = "16708")
    private String statusId;

    @Schema(description = "状态名称", example = "李四")
    private String statusName;

    @Schema(description = "主管部门ID", example = "19737")
    private String deptId;

    @Schema(description = "主管部门名称", example = "王五")
    private String deptName;

    @Schema(description = "附件信息列表")
    private String attachmentInfo;

    @Schema(description = "关联管理部件ID列表")
    private String partIds;

    @Schema(description = "关联部件数", example = "29562")
    private Integer partCount;

    @Schema(description = "超时标识")
    private Boolean timeoutFlag;

    @Schema(description = "超时时长（分钟）")
    private Integer timeoutDuration;

    @Schema(description = "处置意见")
    private String dealOpinion;

    @Schema(description = "处置人ID")
    private String dealBy;

    @Schema(description = "处置人名称", example = "张三")
    private String handlerName;

    @Schema(description = "处置时间")
    private LocalDateTime dealTime;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建人")
    private String creator;

}