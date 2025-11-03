package cn.iocoder.yudao.module.datacenter.service.unitgridrpt;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.unitgridrpt.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.unitgridrpt.UnitGridRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 单元网格统计 Service 接口
 *
 * @author zhucongquan
 */
public interface UnitGridRptService {

    /**
     * 创建单元网格统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUnitGridRpt(@Valid UnitGridRptSaveReqVO createReqVO);

    /**
     * 更新单元网格统计
     *
     * @param updateReqVO 更新信息
     */
    void updateUnitGridRpt(@Valid UnitGridRptSaveReqVO updateReqVO);

    /**
     * 删除单元网格统计
     *
     * @param id 编号
     */
    void deleteUnitGridRpt(Long id);

    /**
     * 获得单元网格统计
     *
     * @param id 编号
     * @return 单元网格统计
     */
    UnitGridRptDO getUnitGridRpt(Long id);

    /**
     * 获得单元网格统计分页
     *
     * @param pageReqVO 分页查询
     * @return 单元网格统计分页
     */
    PageResult<UnitGridRptDO> getUnitGridRptPage(UnitGridRptPageReqVO pageReqVO);

}