package cn.iocoder.yudao.module.datacenter.service.evaluate.idx.evalidxitem;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxitem.vo.EvalIdxItemPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxitem.vo.EvalIdxItemSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.idx.evalidxitem.EvalIdxItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 指标项管理 Service 接口
 *
 * @author zcq
 */
public interface EvalIdxItemService {

    /**
     * 创建指标项管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalIdxItem(@Valid EvalIdxItemSaveReqVO createReqVO);

    /**
     * 更新指标项管理
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalIdxItem(@Valid EvalIdxItemSaveReqVO updateReqVO);

    /**
     * 删除指标项管理
     *
     * @param id 编号
     */
    void deleteEvalIdxItem(Long id);

    /**
     * 获得指标项管理
     *
     * @param id 编号
     * @return 指标项管理
     */
    EvalIdxItemDO getEvalIdxItem(Long id);

    /**
     * 获得指标项管理分页
     *
     * @param pageReqVO 分页查询
     * @return 指标项管理分页
     */
    PageResult<EvalIdxItemDO> getEvalIdxItemPage(EvalIdxItemPageReqVO pageReqVO);

}