package cn.iocoder.yudao.module.smartcity.service.caseacceptance;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.caseacceptance.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.caseacceptance.CaseAcceptanceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 案件受理 Service 接口
 *
 * @author 朱聪权
 */
public interface CaseAcceptanceService {

    /**
     * 创建案件受理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCaseAcceptance(@Valid CaseAcceptanceSaveReqVO createReqVO);

    /**
     * 更新案件受理
     *
     * @param updateReqVO 更新信息
     */
    void updateCaseAcceptance(@Valid CaseAcceptanceSaveReqVO updateReqVO);

    /**
     * 删除案件受理
     *
     * @param id 编号
     */
    void deleteCaseAcceptance(Long id);

    /**
     * 获得案件受理
     *
     * @param id 编号
     * @return 案件受理
     */
    CaseAcceptanceDO getCaseAcceptance(Long id);

    /**
     * 获得案件受理分页
     *
     * @param pageReqVO 分页查询
     * @return 案件受理分页
     */
    PageResult<CaseAcceptanceDO> getCaseAcceptancePage(CaseAcceptancePageReqVO pageReqVO);

}