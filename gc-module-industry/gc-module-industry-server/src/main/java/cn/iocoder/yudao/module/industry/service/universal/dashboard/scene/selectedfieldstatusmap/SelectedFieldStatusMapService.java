package cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.selectedfieldstatusmap;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.selectedfieldstatusmap.vo.SelectedFieldStatusMapPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.selectedfieldstatusmap.vo.SelectedFieldStatusMapSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.selectedfieldstatusmap.SelectedFieldStatusMapDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 场景字段状态映射 Service 接口
 *
 * @author lxs
 */
public interface SelectedFieldStatusMapService {

    /**
     * 创建场景字段状态映射
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSelectedFieldStatusMap(@Valid SelectedFieldStatusMapSaveReqVO createReqVO);

    /**
     * 更新场景字段状态映射
     *
     * @param updateReqVO 更新信息
     */
    void updateSelectedFieldStatusMap(@Valid SelectedFieldStatusMapSaveReqVO updateReqVO);

    /**
     * 删除场景字段状态映射
     *
     * @param id 编号
     */
    void deleteSelectedFieldStatusMap(Long id);

    /**
     * 获得场景字段状态映射
     *
     * @param id 编号
     * @return 场景字段状态映射
     */
    SelectedFieldStatusMapDO getSelectedFieldStatusMap(Long id);

    /**
     * 获得场景字段状态映射分页
     *
     * @param pageReqVO 分页查询
     * @return 场景字段状态映射分页
     */
    PageResult<SelectedFieldStatusMapDO> getSelectedFieldStatusMapPage(SelectedFieldStatusMapPageReqVO pageReqVO);

}
