package cn.iocoder.yudao.module.smartcity.service.casedisposal;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.casedisposal.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.casedisposal.CaseDisposalDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 案件处理 Service 接口
 *
 * @author 朱聪权
 */
public interface CaseDisposalService {

    /**
     * 创建案件处理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCaseDisposal(@Valid CaseDisposalSaveReqVO createReqVO);

    /**
     * 更新案件处理
     *
     * @param updateReqVO 更新信息
     */
    void updateCaseDisposal(@Valid CaseDisposalSaveReqVO updateReqVO);

    /**
     * 删除案件处理
     *
     * @param id 编号
     */
    void deleteCaseDisposal(Long id);

    /**
     * 获得案件处理
     *
     * @param id 编号
     * @return 案件处理
     */
    CaseDisposalDO getCaseDisposal(Long id);

    /**
     * 获得案件处理分页
     *
     * @param pageReqVO 分页查询
     * @return 案件处理分页
     */
    PageResult<CaseDisposalDO> getCaseDisposalPage(CaseDisposalPageReqVO pageReqVO);

}