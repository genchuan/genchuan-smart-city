package cn.iocoder.yudao.module.datacenter.dal.dataobject.bizservicedemand.publicsnapshot;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 随手拍数据 DO
 *
 * @author 亘川智城
 */
@TableName("biz_public_snap_shot")
@KeySequence("biz_public_snap_shot_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicSnapShotDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 随手拍ID，唯一编码，UUID生成
     */
    private String snapShotId;
    /**
     * 记录单号，格式“SP+YYYYMMDD+6位流水号”
     */
    private String snapNo;
    /**
     * 问题类型编码，问题类型，如“路灯故障”，字典表（sys_dict）
     */
    private String problemTypeCode;
    /**
     * 问题类型名称，与类型编码同步，字典表（sys_dict）
     */
    private String problemTypeName;
    /**
     * 事发坐标X，GPS定位经度（2000国家大地坐标系），单位：度，空间数据表（sys_spatial）
     */
    private BigDecimal coordX;
    /**
     * 事发坐标Y，GPS定位纬度，单位：度，空间数据表（sys_spatial）
     */
    private BigDecimal coordY;
    /**
     * 事发区域编码，由坐标反查生成，区域表（sys_area）
     */
    private String areaCode;
    /**
     * 事发区域名称，与区域编码同步，区域表（sys_area）
     */
    private String areaName;
    /**
     * 问题描述，补充问题详情
     */
    private String problemDesc;
    /**
     * 媒体路径，照片/视频存储路径（多个用逗号分隔）
     */
    private String mediaPath;
    /**
     * 媒体类型：1=照片，2=视频，标识媒体类型
     */
    private Integer mediaType;
    /**
     * 上报人姓名
     */
    private String rptName;
    /**
     * 上报人电话，加密存储
     */
    private String rptPhone;
    /**
     * 处置状态：1=待处置，2=处置中，3=已办结，4=无效，流程状态
     */
    private Integer handleStatus;
    /**
     * 处置结果，处置完成后填写
     */
    private String handleResult;
    /**
     * 上报时间，系统自动生成
     */
    private LocalDateTime rptTime;
    /**
     * 办结时间，处置完成时间
     */
    private LocalDateTime finishTime;
    /**
     * 分类扩展字段1，预留，标识问题等级，如轻微/严重
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，标识媒体数量，如1张/多张
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储定位精度
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储处置人员ID
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