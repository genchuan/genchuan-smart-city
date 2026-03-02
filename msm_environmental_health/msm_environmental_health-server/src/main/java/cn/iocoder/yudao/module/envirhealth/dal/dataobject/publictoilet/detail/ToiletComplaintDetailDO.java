package cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletComplaintDO;
import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/14 13:43
 */
@Data
public class ToiletComplaintDetailDO extends ToiletComplaintDO {
    /**
     * 关联public_toilet.toilet_id
     */
    private String toiletName;
    /**
     * 关联sys_complaint_type.id
     */
    private String complaintTypeName;
    /**
     * 关联sys_user.id
     */
    private String handlerName;
}