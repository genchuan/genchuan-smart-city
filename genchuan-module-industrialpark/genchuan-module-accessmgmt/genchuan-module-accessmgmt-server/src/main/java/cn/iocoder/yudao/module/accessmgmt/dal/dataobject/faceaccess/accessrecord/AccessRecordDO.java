package cn.iocoder.yudao.module.accessmgmt.dal.dataobject.faceaccess.accessrecord;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 通行记录 DO
 *
 * @author 亘川智城
 */
@TableName("access_record")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessRecordDO extends BaseDO {

    @TableId
    private Long id;
    private Long faceId;
    private String userName;
    private String accessArea;
    private LocalDateTime accessTime;
    private String verifyType;
    private String accessStatus;
    private String snapImg;
    private String checkResult;
    private String alarmStatus;
    private String handleResult;
    private String reserve1;
    private String reserve2;

}
