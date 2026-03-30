package cn.iocoder.yudao.module.waterdetection.service.consumablemanagement;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.consumablemanagement.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.consumablemanagement.ConsumableManagementDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 耗材库存与更换管理 Service 接口
 *
 * @author zcq
 */
public interface ConsumableManagementService {

    /**
     * 创建耗材库存与更换管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createConsumableManagement(@Valid ConsumableManagementSaveReqVO createReqVO);

    /**
     * 更新耗材库存与更换管理
     *
     * @param updateReqVO 更新信息
     */
    void updateConsumableManagement(@Valid ConsumableManagementSaveReqVO updateReqVO);

    /**
     * 删除耗材库存与更换管理
     *
     * @param id 编号
     */
    void deleteConsumableManagement(Long id);

    /**
     * 获得耗材库存与更换管理
     *
     * @param id 编号
     * @return 耗材库存与更换管理
     */
    ConsumableManagementDO getConsumableManagement(Long id);

    /**
     * 获得耗材库存与更换管理分页
     *
     * @param pageReqVO 分页查询
     * @return 耗材库存与更换管理分页
     */
    PageResult<ConsumableManagementDO> getConsumableManagementPage(ConsumableManagementPageReqVO pageReqVO);

}