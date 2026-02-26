package cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.Detail;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.CleaningProblemDO;
import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/14 15:58
 */
@Data
public class CleaningProblemDetailDO extends CleaningProblemDO {
    /**
     * 关联road_cleaning.cleaning_id
     */
    private String planNo;
    /**
     * 关联sys_problem_type.id
     */
    private String problemTypeName;
    /**
     * 关联sys_user.id
     */
    private String reportName;
    /**
     * 关联sys_team.id
     */
    private String teamName;
}