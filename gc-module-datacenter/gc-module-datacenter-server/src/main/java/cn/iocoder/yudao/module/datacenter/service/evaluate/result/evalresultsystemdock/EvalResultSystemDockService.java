package cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultsystemdock;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultsystemdock.vo.EvalResultSystemDockPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultsystemdock.vo.EvalResultSystemDockSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultsystemdock.EvalResultSystemDockDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 结果系统对接 Service 接口
 *
 * @author zcq
 */
public interface EvalResultSystemDockService {

    /**
     * 创建结果系统对接
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalResultSystemDock(@Valid EvalResultSystemDockSaveReqVO createReqVO);

    /**
     * 更新结果系统对接
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalResultSystemDock(@Valid EvalResultSystemDockSaveReqVO updateReqVO);

    /**
     * 删除结果系统对接
     *
     * @param id 编号
     */
    void deleteEvalResultSystemDock(Long id);

    /**
     * 获得结果系统对接
     *
     * @param id 编号
     * @return 结果系统对接
     */
    EvalResultSystemDockDO getEvalResultSystemDock(Long id);

    /**
     * 获得结果系统对接分页
     *
     * @param pageReqVO 分页查询
     * @return 结果系统对接分页
     */
    PageResult<EvalResultSystemDockDO> getEvalResultSystemDockPage(EvalResultSystemDockPageReqVO pageReqVO);

}