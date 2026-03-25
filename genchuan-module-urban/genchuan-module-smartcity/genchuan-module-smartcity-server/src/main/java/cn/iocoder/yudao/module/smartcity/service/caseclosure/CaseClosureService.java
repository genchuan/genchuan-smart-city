package cn.iocoder.yudao.module.smartcity.service.caseclosure;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.caseclosure.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.caseclosure.CaseClosureDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 案件结案 Service 接口
 *
 * @author 超级管理员
 */
public interface CaseClosureService {

    /**
     * 创建案件结案
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCaseClosure(@Valid CaseClosureSaveReqVO createReqVO);

    /**
     * 更新案件结案
     *
     * @param updateReqVO 更新信息
     */
    void updateCaseClosure(@Valid CaseClosureSaveReqVO updateReqVO);

    /**
     * 删除案件结案
     *
     * @param id 编号
     */
    void deleteCaseClosure(Long id);

    /**
     * 获得案件结案
     *
     * @param id 编号
     * @return 案件结案
     */
    CaseClosureDO getCaseClosure(Long id);

    /**
     * 获得案件结案分页
     *
     * @param pageReqVO 分页查询
     * @return 案件结案分页
     */
    PageResult<CaseClosureDO> getCaseClosurePage(CaseClosurePageReqVO pageReqVO);

}