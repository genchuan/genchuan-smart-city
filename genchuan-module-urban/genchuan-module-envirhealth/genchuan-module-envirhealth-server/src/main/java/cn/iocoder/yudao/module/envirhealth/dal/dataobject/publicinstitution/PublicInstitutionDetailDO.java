package cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution;

import cn.iocoder.yudao.module.envirhealth.framework.util.json.StringSplitUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/24 13:59
 */
@Data
public class PublicInstitutionDetailDO extends PublicInstitutionDO {
    /**
     * 关联sys_institution_type.id
     */
    private String institutionTypeName;
    /**
     * 关联sys_area.area_code
     */
    private String areaName;
    /**
     * 关联sys_user.id
     */
    private String managerName;
    /**
     * 关联sys_operation_status.id
     */
    private String operationStatusName;

    /**
     * 关联sys_user.id
     */
    @JsonIgnore
    private String cleanersNameStr;

    private List<String> cleanersName;

    public void setCleanersNameStr(String cleanersNameStr) {
        this.cleanersNameStr = cleanersNameStr;
        this.cleanersName = StringSplitUtils.splitToStringList(cleanersNameStr);
    }
}