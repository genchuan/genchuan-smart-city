package cn.iocoder.yudao.module.kitchen.service.dictionary.cancelreasondict;

import java.util.*;

import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.cancelreasondict.vo.CancelReasonDictPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.cancelreasondict.vo.CancelReasonDictSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.cancelreasondict.CancelReasonDictDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 撤销原因字典 Service 接口
 *
 * @author 亘川智城
 */
public interface CancelReasonDictService {

    /**
     * 创建撤销原因字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCancelReasonDict(@Valid CancelReasonDictSaveReqVO createReqVO);

    /**
     * 更新撤销原因字典
     *
     * @param updateReqVO 更新信息
     */
    void updateCancelReasonDict(@Valid CancelReasonDictSaveReqVO updateReqVO);

    /**
     * 删除撤销原因字典
     *
     * @param id 编号
     */
    void deleteCancelReasonDict(Long id);

    /**
     * 获得撤销原因字典
     *
     * @param id 编号
     * @return 撤销原因字典
     */
    CancelReasonDictDO getCancelReasonDict(Long id);

    /**
     * 获得撤销原因字典分页
     *
     * @param pageReqVO 分页查询
     * @return 撤销原因字典分页
     */
    PageResult<CancelReasonDictDO> getCancelReasonDictPage(CancelReasonDictPageReqVO pageReqVO);

}
