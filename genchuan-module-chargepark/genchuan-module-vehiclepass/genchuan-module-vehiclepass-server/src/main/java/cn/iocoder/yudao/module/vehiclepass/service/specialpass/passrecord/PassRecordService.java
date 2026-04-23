package cn.iocoder.yudao.module.vehiclepass.service.specialpass.passrecord;

import java.util.*;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo.PassRecordPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo.PassRecordRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo.PassRecordCheckReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo.PassRecordChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo.PassRecordChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo.PassRecordSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.specialpass.passrecord.PassRecordDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 放行记录 Service 接口
 *
 * @author 亘川智城
 */
public interface PassRecordService {

    /**
     * 创建放行记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRecord(@Valid PassRecordSaveReqVO createReqVO);

    /**
     * 更新放行记录
     *
     * @param updateReqVO 更新信息
     */
    void updateRecord(@Valid PassRecordSaveReqVO updateReqVO);

    /**
     * 删除放行记录
     *
     * @param id 编号
     */
    void deleteRecord(Long id);

    /**
     * 批量删除放行记录
     *
     * @param ids 编号
     */
    void deleteRecordListByIds(List<Long> ids);

    /**
     * 获得放行记录
     *
     * @param id 编号
     * @return 放行记录
     */
    PassRecordDO getRecord(Long id);

    /**
     * 获得放行记录分页
     *
     * @param pageReqVO 分页查询
     * @return 放行记录分页
     */
    PageResult<PassRecordDO> getRecordPage(PassRecordPageReqVO pageReqVO);

    /**
     * 获得放行记录分页（含关联名称）
     *
     * @param pageReqVO 分页查询
     * @return 放行记录分页
     */
    PageResult<PassRecordRespVO> getRecordPageWithJoin(PassRecordPageReqVO pageReqVO);

    /**
     * 核查放行记录
     *
     * @param reqVO 请求
     */
    void check(PassRecordCheckReqVO reqVO);

    /**
     * 获取统计图表数据
     *
     * @param reqVO 统计请求
     * @return 统计数据
     */
    PassRecordChartRespVO getChart(PassRecordChartReqVO reqVO);

}