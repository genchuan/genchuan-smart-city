package cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultpublic;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpublic.vo.EvalResultPublicPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultpublic.vo.EvalResultPublicSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultpublic.EvalResultPublicDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 结果公示 Service 接口
 *
 * @author zcq
 */
public interface EvalResultPublicService {

    /**
     * 创建结果公示
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalResultPublic(@Valid EvalResultPublicSaveReqVO createReqVO);

    /**
     * 更新结果公示
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalResultPublic(@Valid EvalResultPublicSaveReqVO updateReqVO);

    /**
     * 删除结果公示
     *
     * @param id 编号
     */
    void deleteEvalResultPublic(Long id);

    /**
     * 获得结果公示
     *
     * @param id 编号
     * @return 结果公示
     */
    EvalResultPublicDO getEvalResultPublic(Long id);

    /**
     * 获得结果公示分页
     *
     * @param pageReqVO 分页查询
     * @return 结果公示分页
     */
    PageResult<EvalResultPublicDO> getEvalResultPublicPage(EvalResultPublicPageReqVO pageReqVO);

}