package cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultpush;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpush.vo.EvalResultPushPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpush.vo.EvalResultPushSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultpush.EvalResultPushDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 结果关联推送 Service 接口
 *
 * @author zcq
 */
public interface EvalResultPushService {

    /**
     * 创建结果关联推送
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalResultPush(@Valid EvalResultPushSaveReqVO createReqVO);

    /**
     * 更新结果关联推送
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalResultPush(@Valid EvalResultPushSaveReqVO updateReqVO);

    /**
     * 删除结果关联推送
     *
     * @param id 编号
     */
    void deleteEvalResultPush(Long id);

    /**
     * 获得结果关联推送
     *
     * @param id 编号
     * @return 结果关联推送
     */
    EvalResultPushDO getEvalResultPush(Long id);

    /**
     * 获得结果关联推送分页
     *
     * @param pageReqVO 分页查询
     * @return 结果关联推送分页
     */
    PageResult<EvalResultPushDO> getEvalResultPushPage(EvalResultPushPageReqVO pageReqVO);

}