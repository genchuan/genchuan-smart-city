package cn.iocoder.yudao.module.datacenter.service.evaluate.std.evalstditem;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.std.evalstditem.vo.EvalStdItemPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.std.evalstditem.vo.EvalStdItemSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.std.evalstditem.EvalStdItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 标准项管理 Service 接口
 *
 * @author zcq
 */
public interface EvalStdItemService {

    /**
     * 创建标准项管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalStdItem(@Valid EvalStdItemSaveReqVO createReqVO);

    /**
     * 更新标准项管理
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalStdItem(@Valid EvalStdItemSaveReqVO updateReqVO);

    /**
     * 删除标准项管理
     *
     * @param id 编号
     */
    void deleteEvalStdItem(Long id);

    /**
     * 获得标准项管理
     *
     * @param id 编号
     * @return 标准项管理
     */
    EvalStdItemDO getEvalStdItem(Long id);

    /**
     * 获得标准项管理分页
     *
     * @param pageReqVO 分页查询
     * @return 标准项管理分页
     */
    PageResult<EvalStdItemDO> getEvalStdItemPage(EvalStdItemPageReqVO pageReqVO);

}