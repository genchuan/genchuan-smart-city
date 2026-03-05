package cn.iocoder.yudao.module.industry.dal.dataobject.park.through.inspectionevidence;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 稽查证据 DO
 *
 * @author zhucongquan
 */
@TableName("park_inspection_evidence")
@KeySequence("park_inspection_evidence_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkInspectionEvidenceDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 证据ID（UUID）
     */
    private String evidenceId;
    /**
     * 稽查记录ID
     */
    private String inspectionId;
    /**
     * 证据类型：图片/视频/日志
     */
    private String evidenceType;
    /**
     * 存储地址
     */
    private String evidenceUrl;
    /**
     * 描述
     */
    private String evidenceDesc;
    /**
     * 上传时间
     */
    private LocalDateTime uploadTime;
    /**
     * 上传人ID
     */
    private Long uploadBy;
    /**
     * 业务创建时间
     */
    private LocalDateTime evidenceCreateTime;
    /**
     * 业务备注
     */
    private String evidenceRemark;

}