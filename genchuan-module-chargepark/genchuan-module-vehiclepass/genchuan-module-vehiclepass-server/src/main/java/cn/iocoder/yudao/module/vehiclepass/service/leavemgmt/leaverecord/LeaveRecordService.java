package cn.iocoder.yudao.module.vehiclepass.service.leavemgmt.leaverecord;

import java.util.*;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordCreateReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordUpdateReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordCorrectReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo.LeaveRecordSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.leavemgmt.leaverecord.LeaveRecordDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 离场记录 Service 接口
 *
 * @author 亘川智城
 */
public interface LeaveRecordService {

    /**
     * 创建离场记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRecord(@Valid LeaveRecordSaveReqVO createReqVO);

    /**
     * 更新离场记录
     *
     * @param updateReqVO 更新信息
     */
    void updateRecord(@Valid LeaveRecordSaveReqVO updateReqVO);

    /**
     * 删除离场记录
     *
     * @param id 编号
     */
    void deleteRecord(Long id);

    /**
     * 批量删除离场记录
     *
     * @param ids 编号
     */
    void deleteRecordListByIds(List<Long> ids);

    /**
     * 获得离场记录
     *
     * @param id 编号
     * @return 离场记录
     */
    LeaveRecordDO getRecord(Long id);

    /**
     * 获得离场记录分页
     *
     * @param pageReqVO 分页查询
     * @return 离场记录分页
     */
    PageResult<LeaveRecordDO> getRecordPage(LeaveRecordPageReqVO pageReqVO);

    /**
     * 获得离场记录分页（含关联名称）
     *
     * @param pageReqVO 分页查询
     * @return 离场记录分页
     */
    PageResult<LeaveRecordRespVO> getRecordPageWithJoin(LeaveRecordPageReqVO pageReqVO);

    /**
     * 补录离场记录
     *
     * @param reqVO 创建信息
     * @return 编号
     */
    Long createRecordSupplement(@Valid LeaveRecordCreateReqVO reqVO);

    /**
     * 编辑离场记录
     *
     * @param reqVO 更新信息
     */
    void updateRecordForEdit(@Valid LeaveRecordUpdateReqVO reqVO);

    /**
     * 修正离场记录
     *
     * @param reqVO 修正信息
     */
    void correctRecord(@Valid LeaveRecordCorrectReqVO reqVO);

    /**
     * 获取统计图表数据
     *
     * @param reqVO 统计请求
     * @return 统计数据
     */
    LeaveRecordChartRespVO getChart(@Valid LeaveRecordChartReqVO reqVO);

}