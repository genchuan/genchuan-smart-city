package cn.iocoder.yudao.module.datacenter.service.evaluate.review.evalappeal;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalappeal.vo.EvalAppealPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalappeal.vo.EvalAppealSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.review.evalappeal.EvalAppealDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 申诉管理 Service 接口
 *
 * @author zhucongquan
 */
public interface EvalAppealService {

    /**
     * 创建申诉管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalAppeal(@Valid EvalAppealSaveReqVO createReqVO);

    /**
     * 更新申诉管理
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalAppeal(@Valid EvalAppealSaveReqVO updateReqVO);

    /**
     * 删除申诉管理
     *
     * @param id 编号
     */
    void deleteEvalAppeal(Long id);

    /**
     * 获得申诉管理
     *
     * @param id 编号
     * @return 申诉管理
     */
    EvalAppealDO getEvalAppeal(Long id);

    /**
     * 获得申诉管理分页
     *
     * @param pageReqVO 分页查询
     * @return 申诉管理分页
     */
    PageResult<EvalAppealDO> getEvalAppealPage(EvalAppealPageReqVO pageReqVO);

}