package cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.trainmat.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 培训资料分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TrainMatPageReqVO extends PageParam {

    @Schema(description = "资料ID，唯一编码，UUID生成", example = "1002")
    private String trainMatId;

    @Schema(description = "资料名称，如《智慧城市一网统管平台操作培训PPT》", example = "王五")
    private String matName;

    @Schema(description = "培训类型代码，取值如01=新员工培训，02=技能提升培训，03=专项业务培训，培训类型字典表（sys_dict_train_type）")
    private String trainTypeCode;

    @Schema(description = "培训类型名称，与培训类型代码同步，培训类型字典表（sys_dict_train_type）", example = "赵六")
    private String trainTypeName;

    @Schema(description = "培训对象，如“新员工、城管工作人员”，角色表（sys_role）")
    private String trainObject;

    @Schema(description = "培训时长，如“60分钟”“2小时”，单位为分钟/小时")
    private String trainEndure;

    @Schema(description = "资料格式，如PPT/PDF/MP4/MP3，培训资料文件格式")
    private String matFormat;

    @Schema(description = "资料大小，如“10MB”“500MB”，单位为MB/GB")
    private String matSize;

    @Schema(description = "资料摘要，培训资料核心内容摘要，如“涵盖平台登录、地理编码配置、事件上报操作”")
    private String matAbstract;

    @Schema(description = "资料文件ID，关联文件存储表的文件ID，文件存储表（sys_file_storage）", example = "22811")
    private String matFileId;

    @Schema(description = "资料文件路径，培训资料文件存储路径，文件存储表（sys_file_storage）")
    private String matFilePath;

    @Schema(description = "资料状态：已发布/未发布，标识资料是否公开", example = "2")
    private String matStatus;

    @Schema(description = "下载次数，用户下载资料的次数，默认0", example = "3930")
    private Integer downloadCount;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“资料等级：基础/进阶”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“关联业务：地理编码/事件处置”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“上传版本：V1.0”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“最后下载时间：2025-10-10”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}