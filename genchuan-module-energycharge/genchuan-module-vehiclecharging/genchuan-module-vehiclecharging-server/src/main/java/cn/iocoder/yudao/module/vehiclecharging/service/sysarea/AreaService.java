package cn.iocoder.yudao.module.vehiclecharging.service.sysarea;

import java.util.*;

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sysarea.vo.AreaPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sysarea.vo.AreaSaveReqVO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sysarea.AreaDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 统一行政区划配置表（树形结构） Service 接口
 *
 * @author 亘川智城
 */
public interface AreaService {

    /**
     * 创建统一行政区划配置表（树形结构）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createArea(@Valid AreaSaveReqVO createReqVO);

    /**
     * 更新统一行政区划配置表（树形结构）
     *
     * @param updateReqVO 更新信息
     */
    void updateArea(@Valid AreaSaveReqVO updateReqVO);

    /**
     * 删除统一行政区划配置表（树形结构）
     *
     * @param id 编号
     */
    void deleteArea(Long id);

    /**
     * 批量删除统一行政区划配置表（树形结构）
     *
     * @param ids 编号
     */
    void deleteAreaListByIds(List<Long> ids);

    /**
     * 获得统一行政区划配置表（树形结构）
     *
     * @param id 编号
     * @return 统一行政区划配置表（树形结构）
     */
    AreaDO getArea(Long id);

    /**
     * 获得统一行政区划配置表（树形结构）分页
     *
     * @param pageReqVO 分页查询
     * @return 统一行政区划配置表（树形结构）分页
     */
    PageResult<AreaDO> getAreaPage(AreaPageReqVO pageReqVO);

}