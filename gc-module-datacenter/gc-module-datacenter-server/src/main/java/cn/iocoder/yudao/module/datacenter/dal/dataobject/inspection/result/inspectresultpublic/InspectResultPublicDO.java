package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.result.inspectresultpublic;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检结果公示 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_result_public")
@KeySequence("biz_inspect_result_public_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectResultPublicDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 公示ID
     */
    private String publicId;
    /**
     * 公示编码
     */
    private String publicCode;
    /**
     * 公示标题
     */
    private String publicTitle;
    /**
     * 公示类型
     */
    private String publicType;
    /**
     * 所属区域代码
     */
    private String areaCode;
    /**
     * 所属区域名称
     */
    private String areaName;
    /**
     * 公示内容
     */
    private String publicContent;
    /**
     * 公示附件URL
     */
    private String publicAttachUrl;
    /**
     * 公示开始时间
     */
    private LocalDateTime publicStartTime;
    /**
     * 公示结束时间
     */
    private LocalDateTime publicEndTime;
    /**
     * 公示状态
     */
    private String publicStatus;
    /**
     * 公示说明
     */
    private String publicDesc;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private LocalDateTime bizCreateTime;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 更新时间
     */
    private LocalDateTime bizUpdateTime;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}