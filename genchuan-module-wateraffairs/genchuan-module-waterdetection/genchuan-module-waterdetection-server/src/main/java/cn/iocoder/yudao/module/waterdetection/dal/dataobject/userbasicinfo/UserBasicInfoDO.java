package cn.iocoder.yudao.module.waterdetection.dal.dataobject.userbasicinfo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 用户基础信息登记 DO
 *
 * @author zcq
 */
@TableName("gc_user_basic_info")
@KeySequence("gc_user_basic_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBasicInfoDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 用户编号
     */
    private String userCode;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 身份证号
     */
    private String idCardNo;
    /**
     * 家庭住址
     */
    private String address;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 开户日期
     */
    private LocalDateTime openDate;

}