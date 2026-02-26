package cn.iocoder.yudao.module.envir.service.area;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envir.controller.admin.area.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.area.AreaDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 区域编码 Service 接口
 *
 * @author 芋道源码
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

}