package cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning;

import cn.iocoder.yudao.module.envirhealth.framework.util.json.StringSplitUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/14 15:08
 */
@Data
public class RoadCleaningDetailDO extends RoadCleaningDO {
    /**
     * 关联sys_road.id
     */
    private String roadName;
    /**
     * 关联sys_area.area_code
     */
    private String areaName;
    /**
     * 负责人员IDs，JSON
     */
    @JsonIgnore
    private String staffsNameStr;

    private List<String> staffsName;
    /**
     * 关联sys_plan_status.id
     */
    private String planStatusName;
    /**
     * 清扫工具IDs，JSON
     */
    @JsonIgnore
    private String toolsNameStr;

    private List<String> toolsName;

    public void setStaffsNameStr(String staffsNameStr) {
        this.staffsNameStr = staffsNameStr;
        this.staffsName = StringSplitUtils.splitToStringList(staffsNameStr);
    }

    public void setToolsNameStr(String toolsNameStr) {
        this.toolsNameStr = toolsNameStr;
        this.toolsName = StringSplitUtils.splitToStringList(toolsNameStr);
    }
}