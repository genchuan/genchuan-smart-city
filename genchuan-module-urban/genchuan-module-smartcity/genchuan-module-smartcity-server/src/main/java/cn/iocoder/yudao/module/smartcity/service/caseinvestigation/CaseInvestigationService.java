package cn.iocoder.yudao.module.smartcity.service.caseinvestigation;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.caseinvestigation.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.caseinvestigation.CaseInvestigationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 案件调查 Service 接口
 *
 * @author 朱聪权
 */
public interface CaseInvestigationService {

    /**
     * 创建案件调查
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCaseInvestigation(@Valid CaseInvestigationSaveReqVO createReqVO);

    /**
     * 更新案件调查
     *
     * @param updateReqVO 更新信息
     */
    void updateCaseInvestigation(@Valid CaseInvestigationSaveReqVO updateReqVO);

    /**
     * 删除案件调查
     *
     * @param id 编号
     */
    void deleteCaseInvestigation(Long id);

    /**
     * 获得案件调查
     *
     * @param id 编号
     * @return 案件调查
     */
    CaseInvestigationDO getCaseInvestigation(Long id);

    /**
     * 获得案件调查分页
     *
     * @param pageReqVO 分页查询
     * @return 案件调查分页
     */
    PageResult<CaseInvestigationDO> getCaseInvestigationPage(CaseInvestigationPageReqVO pageReqVO);

}