package cn.iocoder.yudao.module.appearance.dal.dataobject.outdoorad;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 附件文件表 DO
 *
 * @author 亘川智城
 */
@TableName("sys_attach_file")
@KeySequence("sys_attach_file_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysAttachFileDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 附件文件ID
     */
    private String fileId;
    /**
     * 附件文件名称
     */
    private String fileName;
    /**
     * 附件文件URL
     */
    private String fileUrl;
    /**
     * 附件文件类型
     */
    private String fileType;
   }
