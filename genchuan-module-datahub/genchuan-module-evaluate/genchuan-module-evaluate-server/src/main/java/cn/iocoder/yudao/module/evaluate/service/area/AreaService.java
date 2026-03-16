package cn.iocoder.yudao.module.evaluate.service.area;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.area.vo.AreaPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.area.vo.AreaSaveReqVO;
import cn.iocoder.yudao.module.evaluate.controller.common.vo.SelectOptionRespVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.area.AreaDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 区域编码 Service 接口
 *
 * @author 亘川智城
 */
public interface AreaService {

    /**
     * 创建区域编码
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createArea(@Valid AreaSaveReqVO createReqVO);

    /**
     * 更新区域编码
     *
     * @param updateReqVO 更新信息
     */
    void updateArea(@Valid AreaSaveReqVO updateReqVO);

    /**
     * 删除区域编码
     *
     * @param id 编号
     */
    void deleteArea(Long id);

    /**
     * 获得区域编码
     *
     * @param id 编号
     * @return 区域编码
     */
    AreaDO getArea(Long id);

    /**
     * 获得区域编码分页
     *
     * @param pageReqVO 分页查询
     * @return 区域编码分页
     */
    PageResult<AreaDO> getAreaPage(AreaPageReqVO pageReqVO);

    // Service 核心逻辑
    List<SelectOptionRespVO> getAreaSimpleList();
}