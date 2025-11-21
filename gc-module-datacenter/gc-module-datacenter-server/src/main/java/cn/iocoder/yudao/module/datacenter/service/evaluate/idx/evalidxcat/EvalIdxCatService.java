package cn.iocoder.yudao.module.datacenter.service.evaluate.idx.evalidxcat;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxcat.vo.EvalIdxCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxcat.vo.EvalIdxCatSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.idx.evalidxcat.EvalIdxCatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 指标分类管理 Service 接口
 *
 * @author zcq
 */
public interface EvalIdxCatService {

    /**
     * 创建指标分类管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalIdxCat(@Valid EvalIdxCatSaveReqVO createReqVO);

    /**
     * 更新指标分类管理
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalIdxCat(@Valid EvalIdxCatSaveReqVO updateReqVO);

    /**
     * 删除指标分类管理
     *
     * @param id 编号
     */
    void deleteEvalIdxCat(Long id);

    /**
     * 获得指标分类管理
     *
     * @param id 编号
     * @return 指标分类管理
     */
    EvalIdxCatDO getEvalIdxCat(Long id);

    /**
     * 获得指标分类管理分页
     *
     * @param pageReqVO 分页查询
     * @return 指标分类管理分页
     */
    PageResult<EvalIdxCatDO> getEvalIdxCatPage(EvalIdxCatPageReqVO pageReqVO);

}