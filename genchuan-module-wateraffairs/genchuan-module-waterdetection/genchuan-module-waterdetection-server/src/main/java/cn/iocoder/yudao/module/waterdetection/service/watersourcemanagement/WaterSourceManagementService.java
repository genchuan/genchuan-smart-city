package cn.iocoder.yudao.module.waterdetection.service.watersourcemanagement;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.watersourcemanagement.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersourcemanagement.WaterSourceManagementDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 水源类型及属性管理 Service 接口
 *
 * @author zcq
 */
public interface WaterSourceManagementService {

    /**
     * 创建水源类型及属性管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWaterSourceManagement(@Valid WaterSourceManagementSaveReqVO createReqVO);

    /**
     * 更新水源类型及属性管理
     *
     * @param updateReqVO 更新信息
     */
    void updateWaterSourceManagement(@Valid WaterSourceManagementSaveReqVO updateReqVO);

    /**
     * 删除水源类型及属性管理
     *
     * @param id 编号
     */
    void deleteWaterSourceManagement(Long id);

    /**
     * 获得水源类型及属性管理
     *
     * @param id 编号
     * @return 水源类型及属性管理
     */
    WaterSourceManagementDO getWaterSourceManagement(Long id);

    /**
     * 获得水源类型及属性管理分页
     *
     * @param pageReqVO 分页查询
     * @return 水源类型及属性管理分页
     */
    PageResult<WaterSourceManagementDO> getWaterSourceManagementPage(WaterSourceManagementPageReqVO pageReqVO);

}