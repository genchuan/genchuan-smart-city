package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterminor.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 管理事项小类分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizMngMatterMinorPageReqVO extends PageParam {

    @Schema(description = "管理事项小类ID，唯一编码，UUID生成", example = "9537")
    private String mngMatterMinorId;

    @Schema(description = "所属大类ID，关联管理事项大类ID，关联管理事项大类表(gc_biz_mng_matter_major)", example = "27704")
    private String parentMajorId;

    @Schema(description = "所属大类名称，与所属大类ID同步，不可改，关联管理事项大类表(gc_biz_mng_matter_major)", example = "王五")
    private String parentMajorName;

    @Schema(description = "小类代码，3位字符，001 - 999，标准类001 - 079、扩展类080 - 999倒排，同一大类下唯一")
    private String matterMinorCode;

    @Schema(description = "小类名称，如私搭乱建/垃圾乱堆乱放等，国标名，扩展类加“（自定义）”", example = "王五")
    private String matterMinorName;

    @Schema(description = "小类说明，描述小类定义，如“私搭乱建：未经审批搭建”")
    private String matterMinorDesc;

    @Schema(description = "主管部门代码，主管部门信用代码，关联部门信息表(sys_org)")
    private String deptCode;

    @Schema(description = "主管部门名称，与主管部门代码同步，关联部门信息表(sys_org)", example = "王五")
    private String deptName;

    @Schema(description = "是否扩展类，0（标准类）/1（扩展类），按代码自动判断")
    private String isExt;

    @Schema(description = "启用状态，1（启用）/0（禁用），默认继承大类状态", example = "2")
    private String enableStatus;

    @Schema(description = "创建人，配置人账号，关联用户信息表(sys_user)")
    private String createUser;

    @Schema(description = "创建时间，系统生成，格式yyyy - MM - dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人，修改人账号，关联用户信息表(sys_user)")
    private String updateUser;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}