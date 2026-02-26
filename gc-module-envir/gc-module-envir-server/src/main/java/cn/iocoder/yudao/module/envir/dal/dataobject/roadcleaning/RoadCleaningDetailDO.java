package cn.iocoder.yudao.module.envir.dal.dataobject.roadcleaning;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/11 15:57
 */
@Data
public class RoadCleaningDetailDO extends RoadCleaningDO{
    /**
     * 清扫路段（关联sys_road.sys_road_id）
     */
    private String roadName;
    /**
     * 责任区域（关联sys_area.area_code）
     */
    private String areaName;
    /**
     * 负责人员（关联sys_user.id，多个用逗号分隔）
     */
    private String usersNameStr;
    private List<String> usersName;
    /**
     * 清扫工具（关联sys_tool.sys_tool_id，多个用逗号分隔）
     */
    private String toolsNameStr;
    private List<String> toolsName;
    /**
     * 计划状态（关联sys_plan_status.sys_plan_status_id）
     */
    private String planStatusName;

    public void setUsersNameStr(String usersNameStr) {
        this.usersNameStr = usersNameStr;
        if (usersNameStr != null && !usersNameStr.trim().isEmpty()) {
            this.usersName = Arrays.asList(usersNameStr.split(","));
        } else {
            this.usersName = new ArrayList<>();
        }
    }

    public void setToolsNameStr(String toolsNameStr) {
        this.toolsNameStr = toolsNameStr;
        if (toolsNameStr != null && !toolsNameStr.trim().isEmpty()) {
            this.toolsName = Arrays.asList(toolsNameStr.split(","));
        } else {
            this.toolsName = new ArrayList<>();
        }
    }
}