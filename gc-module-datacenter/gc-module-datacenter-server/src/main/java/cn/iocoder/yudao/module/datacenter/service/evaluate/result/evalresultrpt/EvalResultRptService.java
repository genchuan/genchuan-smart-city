package cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultrpt.vo.EvalResultRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultrpt.vo.EvalResultRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultrpt.EvalResultRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 结果报表管理 Service 接口
 *
 * @author zcq
 */
public interface EvalResultRptService {

    /**
     * 创建结果报表管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalResultRpt(@Valid EvalResultRptSaveReqVO createReqVO);

    /**
     * 更新结果报表管理
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalResultRpt(@Valid EvalResultRptSaveReqVO updateReqVO);

    /**
     * 删除结果报表管理
     *
     * @param id 编号
     */
    void deleteEvalResultRpt(Long id);

    /**
     * 获得结果报表管理
     *
     * @param id 编号
     * @return 结果报表管理
     */
    EvalResultRptDO getEvalResultRpt(Long id);

    /**
     * 获得结果报表管理分页
     *
     * @param pageReqVO 分页查询
     * @return 结果报表管理分页
     */
    PageResult<EvalResultRptDO> getEvalResultRptPage(EvalResultRptPageReqVO pageReqVO);

}