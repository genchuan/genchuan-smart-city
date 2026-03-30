package cn.iocoder.yudao.module.waterdetection.service.waterprotectionarea;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.waterprotectionarea.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.waterprotectionarea.WaterProtectionAreaDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 水源保护区管理 Service 接口
 *
 * @author zcq
 */
public interface WaterProtectionAreaService {

    /**
     * 创建水源保护区管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWaterProtectionArea(@Valid WaterProtectionAreaSaveReqVO createReqVO);

    /**
     * 更新水源保护区管理
     *
     * @param updateReqVO 更新信息
     */
    void updateWaterProtectionArea(@Valid WaterProtectionAreaSaveReqVO updateReqVO);

    /**
     * 删除水源保护区管理
     *
     * @param id 编号
     */
    void deleteWaterProtectionArea(Long id);

    /**
     * 获得水源保护区管理
     *
     * @param id 编号
     * @return 水源保护区管理
     */
    WaterProtectionAreaDO getWaterProtectionArea(Long id);

    /**
     * 获得水源保护区管理分页
     *
     * @param pageReqVO 分页查询
     * @return 水源保护区管理分页
     */
    PageResult<WaterProtectionAreaDO> getWaterProtectionAreaPage(WaterProtectionAreaPageReqVO pageReqVO);

}