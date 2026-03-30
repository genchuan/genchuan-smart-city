package cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet;

import cn.iocoder.yudao.module.envirhealth.framework.util.json.StringSplitUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/18 11:11
 */
@Data
public class ToiletCleaningTaskDetailDO extends ToiletCleaningTaskDO {

    /**
     * 公厕ID，关联public_toilet.id
     */
    private String toiletName;

    /**
     * 保洁人员IDs，JSON数组格式
     */
    @JsonIgnore
    private String cleanersNameStr;

    private List<String> cleanerNames;

    /**
     * 计划状态ID，关联sys_plan_status.id
     */
    private String planStatusName;

    private String areaName;

    public void setCleanersNameStr(String cleanersNameStr) {
        this.cleanersNameStr = cleanersNameStr;
        this.cleanerNames = StringSplitUtils.splitToStringList(cleanersNameStr);
    }
}