package cn.iocoder.yudao.module.datacenter.service.evaluate.idx.evalidxweight;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxweight.vo.EvalIdxWeightPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxweight.vo.EvalIdxWeightSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.idx.evalidxweight.EvalIdxWeightDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 指标权重管理 Service 接口
 *
 * @author zcq
 */
public interface EvalIdxWeightService {

    /**
     * 创建指标权重管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalIdxWeight(@Valid EvalIdxWeightSaveReqVO createReqVO);

    /**
     * 更新指标权重管理
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalIdxWeight(@Valid EvalIdxWeightSaveReqVO updateReqVO);

    /**
     * 删除指标权重管理
     *
     * @param id 编号
     */
    void deleteEvalIdxWeight(Long id);

    /**
     * 获得指标权重管理
     *
     * @param id 编号
     * @return 指标权重管理
     */
    EvalIdxWeightDO getEvalIdxWeight(Long id);

    /**
     * 获得指标权重管理分页
     *
     * @param pageReqVO 分页查询
     * @return 指标权重管理分页
     */
    PageResult<EvalIdxWeightDO> getEvalIdxWeightPage(EvalIdxWeightPageReqVO pageReqVO);

}