package cn.iocoder.yudao.module.datacenter.service.retroanalytmgmt.disposalresulteval;

import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.disposalresulteval.vo.DisposalResultEvalPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.disposalresulteval.vo.DisposalResultEvalSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.retroanalytmgmt.disposalresulteval.DisposalResultEvalDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 处置结果评估 Service 接口
 *
 * @author 亘川智城
 */
public interface DisposalResultEvalService {

    /**
     * 创建处置结果评估
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDisposalResultEval(@Valid DisposalResultEvalSaveReqVO createReqVO);

    /**
     * 更新处置结果评估
     *
     * @param updateReqVO 更新信息
     */
    void updateDisposalResultEval(@Valid DisposalResultEvalSaveReqVO updateReqVO);

    /**
     * 删除处置结果评估
     *
     * @param id 编号
     */
    void deleteDisposalResultEval(Long id);

    /**
     * 获得处置结果评估
     *
     * @param id 编号
     * @return 处置结果评估
     */
    DisposalResultEvalDO getDisposalResultEval(Long id);

    /**
     * 获得处置结果评估分页
     *
     * @param pageReqVO 分页查询
     * @return 处置结果评估分页
     */
    PageResult<DisposalResultEvalDO> getDisposalResultEvalPage(DisposalResultEvalPageReqVO pageReqVO);

}