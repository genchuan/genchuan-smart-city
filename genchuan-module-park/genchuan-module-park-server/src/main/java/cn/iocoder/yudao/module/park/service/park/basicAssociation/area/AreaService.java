package cn.iocoder.yudao.module.park.service.park.basicAssociation.area;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.area.vo.AreaPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.area.vo.AreaSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.area.AreaDO;
import jakarta.validation.Valid;

/**
 * 行政区划配置表 Service 接口
 *
 * @author zhucongquan
 */
public interface AreaService {

    /**
     * 创建行政区划配置表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createArea(@Valid AreaSaveReqVO createReqVO);

    /**
     * 更新行政区划配置表
     *
     * @param updateReqVO 更新信息
     */
    void updateArea(@Valid AreaSaveReqVO updateReqVO);

    /**
     * 删除行政区划配置表
     *
     * @param id 编号
     */
    void deleteArea(Long id);

    /**
     * 获得行政区划配置表
     *
     * @param id 编号
     * @return 行政区划配置表
     */
    AreaDO getArea(Long id);

    /**
     * 获得行政区划配置表分页
     *
     * @param pageReqVO 分页查询
     * @return 行政区划配置表分页
     */
    PageResult<AreaDO> getAreaPage(AreaPageReqVO pageReqVO);

}
