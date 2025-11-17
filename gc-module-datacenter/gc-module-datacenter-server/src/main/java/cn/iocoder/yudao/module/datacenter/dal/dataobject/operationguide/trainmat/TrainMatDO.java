package cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.trainmat;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 培训资料 DO
 *
 * @author 亘川智城
 */
@TableName("biz_train_mat")
@KeySequence("biz_train_mat_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainMatDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 资料ID，唯一编码，UUID生成
     */
    private String trainMatId;
    /**
     * 资料名称，如《智慧城市一网统管平台操作培训PPT》
     */
    private String matName;
    /**
     * 培训类型代码，取值如01=新员工培训，02=技能提升培训，03=专项业务培训，培训类型字典表（sys_dict_train_type）
     */
    private String trainTypeCode;
    /**
     * 培训类型名称，与培训类型代码同步，培训类型字典表（sys_dict_train_type）
     */
    private String trainTypeName;
    /**
     * 培训对象，如“新员工、城管工作人员”，角色表（sys_role）
     */
    private String trainObject;
    /**
     * 培训时长，如“60分钟”“2小时”，单位为分钟/小时
     */
    private String trainEndure;
    /**
     * 资料格式，如PPT/PDF/MP4/MP3，培训资料文件格式
     */
    private String matFormat;
    /**
     * 资料大小，如“10MB”“500MB”，单位为MB/GB
     */
    private String matSize;
    /**
     * 资料摘要，培训资料核心内容摘要，如“涵盖平台登录、地理编码配置、事件上报操作”
     */
    private String matAbstract;
    /**
     * 资料文件ID，关联文件存储表的文件ID，文件存储表（sys_file_storage）
     */
    private String matFileId;
    /**
     * 资料文件路径，培训资料文件存储路径，文件存储表（sys_file_storage）
     */
    private String matFilePath;
    /**
     * 资料状态：已发布/未发布，标识资料是否公开
     */
    private String matStatus;
    /**
     * 下载次数，用户下载资料的次数，默认0
     */
    private Integer downloadCount;
    /**
     * 创建人，创建人ID，用户信息表（sys_user）
     */
    private String createUser;
    /**
     * 更新人，更新人ID，用户信息表（sys_user）
     */
    private String updateUser;
    /**
     * 分类扩展字段1，预留，如“资料等级：基础/进阶”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“关联业务：地理编码/事件处置”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，如“上传版本：V1.0”
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，如“最后下载时间：2025-10-10”
     */
    private String extCommon2;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}