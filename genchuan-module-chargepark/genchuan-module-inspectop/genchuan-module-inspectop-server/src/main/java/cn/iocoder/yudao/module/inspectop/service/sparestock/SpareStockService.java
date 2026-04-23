package cn.iocoder.yudao.module.inspectop.service.sparestock;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.sparestock.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.sparestock.SpareStockDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 备件仓储 Service 接口
 *
 * @author zhucongquan
 */
public interface SpareStockService {

    /**
     * 创建备件仓储
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSpareStock(@Valid SpareStockSaveReqVO createReqVO);

    /**
     * 更新备件仓储
     *
     * @param updateReqVO 更新信息
     */
    void updateSpareStock(@Valid SpareStockSaveReqVO updateReqVO);

    /**
     * 删除备件仓储
     *
     * @param id 编号
     */
    void deleteSpareStock(Long id);

    /**
    * 批量删除备件仓储
    *
    * @param ids 编号
    */
    void deleteSpareStockListByIds(List<Long> ids);

    /**
     * 获得备件仓储
     *
     * @param id 编号
     * @return 备件仓储
     */
    SpareStockDO getSpareStock(Long id);

    /**
     * 获得备件仓储分页
     *
     * @param pageReqVO 分页查询
     * @return 备件仓储分页
     */
    PageResult<SpareStockDO> getSpareStockPage(SpareStockPageReqVO pageReqVO);

    /**
     * 备件入库
     *
     * @param reqVO 入库信息
     */
    void inSpareStock(@Valid SpareStockInReqVO reqVO);

    /**
     * 备件出库
     *
     * @param reqVO 出库信息
     */
    void outSpareStock(@Valid SpareStockOutReqVO reqVO);

    /**
     * 备件补货
     *
     * @param reqVO 补货信息
     */
    void replenishSpareStock(@Valid SpareStockReplenishReqVO reqVO);

    /**
     * 获得备件仓储统计图表
     *
     * @param reqVO 查询条件
     * @return 图表统计结果
     */
    SpareStockChartRespVO getSpareStockChart(SpareStockChartReqVO reqVO);

}