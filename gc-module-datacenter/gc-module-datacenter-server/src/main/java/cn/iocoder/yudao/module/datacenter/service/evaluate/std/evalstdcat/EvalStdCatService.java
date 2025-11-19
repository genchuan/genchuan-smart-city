package cn.iocoder.yudao.module.datacenter.service.evaluate.std.evalstdcat;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.std.evalstdcat.vo.EvalStdCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.std.evalstdcat.vo.EvalStdCatSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.std.evalstdcat.EvalStdCatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 标准分类管理 Service 接口
 *
 * @author zcq
 */
public interface EvalStdCatService {

    /**
     * 创建标准分类管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalStdCat(@Valid EvalStdCatSaveReqVO createReqVO);

    /**
     * 更新标准分类管理
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalStdCat(@Valid EvalStdCatSaveReqVO updateReqVO);

    /**
     * 删除标准分类管理
     *
     * @param id 编号
     */
    void deleteEvalStdCat(Long id);

    /**
     * 获得标准分类管理
     *
     * @param id 编号
     * @return 标准分类管理
     */
    EvalStdCatDO getEvalStdCat(Long id);

    /**
     * 获得标准分类管理分页
     *
     * @param pageReqVO 分页查询
     * @return 标准分类管理分页
     */
    PageResult<EvalStdCatDO> getEvalStdCatPage(EvalStdCatPageReqVO pageReqVO);

}