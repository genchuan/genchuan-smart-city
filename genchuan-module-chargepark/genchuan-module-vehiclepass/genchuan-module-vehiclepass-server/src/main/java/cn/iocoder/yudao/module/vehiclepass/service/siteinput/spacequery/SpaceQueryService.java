package cn.iocoder.yudao.module.vehiclepass.service.siteinput.spacequery;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQueryPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQueryRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQuerySaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQueryLocationRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQueryChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQueryChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.siteinput.spacequery.SpaceQueryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 泊位查询 Service 接口
 *
 * @author 亘川智城
 */
public interface SpaceQueryService {

    /**
     * 创建泊位查询
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createQuery(@Valid SpaceQuerySaveReqVO createReqVO);

    /**
     * 更新泊位查询
     *
     * @param updateReqVO 更新信息
     */
    void updateQuery(@Valid SpaceQuerySaveReqVO updateReqVO);

    /**
     * 删除泊位查询
     *
     * @param id 编号
     */
    void deleteQuery(Long id);

    /**
     * 批量删除泊位查询
     *
     * @param ids 编号
     */
    void deleteQueryListByIds(List<Long> ids);

    /**
     * 获得泊位查询
     *
     * @param id 编号
     * @return 泊位查询
     */
    SpaceQueryDO getQuery(Long id);

    /**
     * 获得泊位查询（含关联表字段）
     */
    SpaceQueryRespVO getQueryWithJoin(Long id);

    /**
     * 获得泊位查询分页
     *
     * @param pageReqVO 分页查询
     * @return 泊位查询分页
     */
    PageResult<SpaceQueryDO> getQueryPage(SpaceQueryPageReqVO pageReqVO);

    /**
     * 获得泊位查询分页（使用JOIN查询）
     *
     * @param pageReqVO 分页查询
     * @return 泊位查询分页（含关联表字段）
     */
    PageResult<SpaceQueryRespVO> getQueryPageWithJoin(SpaceQueryPageReqVO pageReqVO);

    /**
     * 获取泊位查询定位信息
     *
     * @param id 泊位查询编号
     * @return 定位信息
     */
    SpaceQueryLocationRespVO getLocation(Long id);

    /**
     * 获取泊位查询统计
     *
     * @param chartReqVO 统计请求
     * @return 统计数据
     */
    SpaceQueryChartRespVO getChart(SpaceQueryChartReqVO chartReqVO);

}