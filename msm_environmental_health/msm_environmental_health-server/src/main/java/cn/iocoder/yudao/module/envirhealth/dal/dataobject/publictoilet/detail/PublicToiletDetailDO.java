package cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.PublicToiletDO;
import cn.iocoder.yudao.module.envirhealth.util.json.StringSplitUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/14 11:13
 */
@Data
public class PublicToiletDetailDO extends PublicToiletDO {
    /**
     * 关联sys_area.area_code
     */
    private String areaName;
    /**
     * 关联sys_operation_status.id
     */
    private String operationStatusName;
    /**
     * 关联sys_user.id
     */
    private String managerName;
    /**
     * 保洁人员IDs，JSON
     */
    @JsonIgnore
    private String cleanersNameStr;

    private List<String> cleanersName;

    public void setCleanersNameStr(String cleanersNameStr) {
        this.cleanersNameStr = cleanersNameStr;
        this.cleanersName = StringSplitUtils.splitToStringList(cleanersNameStr);
    }
}