package cn.iocoder.yudao.module.datacenter.service.evaluate.inspect.evalinspectrec;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspect.evalinspectrec.vo.EvalInspectRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspect.evalinspectrec.vo.EvalInspectRecSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.inspect.evalinspectrec.EvalInspectRecDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 考察记录数据 Service 接口
 *
 * @author zcq
 */
public interface EvalInspectRecService {

    /**
     * 创建考察记录数据
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalInspectRec(@Valid EvalInspectRecSaveReqVO createReqVO);

    /**
     * 更新考察记录数据
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalInspectRec(@Valid EvalInspectRecSaveReqVO updateReqVO);

    /**
     * 删除考察记录数据
     *
     * @param id 编号
     */
    void deleteEvalInspectRec(Long id);

    /**
     * 获得考察记录数据
     *
     * @param id 编号
     * @return 考察记录数据
     */
    EvalInspectRecDO getEvalInspectRec(Long id);

    /**
     * 获得考察记录数据分页
     *
     * @param pageReqVO 分页查询
     * @return 考察记录数据分页
     */
    PageResult<EvalInspectRecDO> getEvalInspectRecPage(EvalInspectRecPageReqVO pageReqVO);

}