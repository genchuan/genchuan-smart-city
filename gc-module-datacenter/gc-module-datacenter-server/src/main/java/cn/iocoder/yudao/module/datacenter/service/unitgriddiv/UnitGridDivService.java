package cn.iocoder.yudao.module.datacenter.service.unitgriddiv;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.unitgriddiv.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.unitgriddiv.UnitGridDivDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 单元网格划分 Service 接口
 *
 * @author zcq
 */
public interface UnitGridDivService {

    /**
     * 创建单元网格划分
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUnitGridDiv(@Valid UnitGridDivSaveReqVO createReqVO);

    /**
     * 更新单元网格划分
     *
     * @param updateReqVO 更新信息
     */
    void updateUnitGridDiv(@Valid UnitGridDivSaveReqVO updateReqVO);

    /**
     * 删除单元网格划分
     *
     * @param id 编号
     */
    void deleteUnitGridDiv(Long id);

    /**
     * 获得单元网格划分
     *
     * @param id 编号
     * @return 单元网格划分
     */
    UnitGridDivDO getUnitGridDiv(Long id);

    /**
     * 获得单元网格划分分页
     *
     * @param pageReqVO 分页查询
     * @return 单元网格划分分页
     */
    PageResult<UnitGridDivDO> getUnitGridDivPage(UnitGridDivPageReqVO pageReqVO);

}