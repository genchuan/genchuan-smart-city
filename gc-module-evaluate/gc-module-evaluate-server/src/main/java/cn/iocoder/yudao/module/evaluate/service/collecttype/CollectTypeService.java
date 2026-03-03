package cn.iocoder.yudao.module.evaluate.service.collecttype;

import cn.iocoder.yudao.module.evaluate.controller.admin.sys.collecttype.vo.CollectTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.collecttype.vo.CollectTypeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.collecttype.CollectTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 采集方式字典 Service 接口
 *
 * @author 亘川智城
 */
public interface CollectTypeService {

    /**
     * 创建采集方式字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCollectType(@Valid CollectTypeSaveReqVO createReqVO);

    /**
     * 更新采集方式字典
     *
     * @param updateReqVO 更新信息
     */
    void updateCollectType(@Valid CollectTypeSaveReqVO updateReqVO);

    /**
     * 删除采集方式字典
     *
     * @param id 编号
     */
    void deleteCollectType(Long id);

    /**
     * 获得采集方式字典
     *
     * @param id 编号
     * @return 采集方式字典
     */
    CollectTypeDO getCollectType(Long id);

    /**
     * 获得采集方式字典分页
     *
     * @param pageReqVO 分页查询
     * @return 采集方式字典分页
     */
    PageResult<CollectTypeDO> getCollectTypePage(CollectTypePageReqVO pageReqVO);

}