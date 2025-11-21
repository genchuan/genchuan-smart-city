package cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultarch.vo.EvalResultArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultarch.vo.EvalResultArchSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultarch.EvalResultArchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 结果存档管理 Service 接口
 *
 * @author zcq
 */
public interface EvalResultArchService {

    /**
     * 创建结果存档管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalResultArch(@Valid EvalResultArchSaveReqVO createReqVO);

    /**
     * 更新结果存档管理
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalResultArch(@Valid EvalResultArchSaveReqVO updateReqVO);

    /**
     * 删除结果存档管理
     *
     * @param id 编号
     */
    void deleteEvalResultArch(Long id);

    /**
     * 获得结果存档管理
     *
     * @param id 编号
     * @return 结果存档管理
     */
    EvalResultArchDO getEvalResultArch(Long id);

    /**
     * 获得结果存档管理分页
     *
     * @param pageReqVO 分页查询
     * @return 结果存档管理分页
     */
    PageResult<EvalResultArchDO> getEvalResultArchPage(EvalResultArchPageReqVO pageReqVO);

}