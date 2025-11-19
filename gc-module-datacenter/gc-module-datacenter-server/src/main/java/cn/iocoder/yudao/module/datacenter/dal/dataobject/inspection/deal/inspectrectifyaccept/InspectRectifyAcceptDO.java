package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.deal.inspectrectifyaccept;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检整改结果验收 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_rectify_accept")
@KeySequence("biz_inspect_rectify_accept_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectRectifyAcceptDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 验收ID
     */
    private String acceptId;
    /**
     * 验收编码
     */
    private String acceptCode;
    /**
     * 关联整改任务ID
     */
    private String rectifyTaskId;
    /**
     * 关联整改任务编码
     */
    private String rectifyTaskCode;
    /**
     * 关联问题ID
     */
    private String problemId;
    /**
     * 整改结果描述
     */
    private String rectifyResultDesc;
    /**
     * 整改后照片URLs
     */
    private String rectifyAfterPhotoUrls;
    /**
     * 整改前照片URLs
     */
    private String rectifyBeforePhotoUrls;
    /**
     * 验收结果
     */
    private String acceptResult;
    /**
     * 验收意见
     */
    private String acceptOpinion;
    /**
     * 验收人
     */
    private String acceptUser;
    /**
     * 验收时间
     */
    private LocalDateTime acceptTime;
    /**
     * 整改提交人
     */
    private String rectifySubmitUser;
    /**
     * 整改提交时间
     */
    private LocalDateTime rectifySubmitTime;
    /**
     * 任务状态变更
     */
    private String taskStatusChange;
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