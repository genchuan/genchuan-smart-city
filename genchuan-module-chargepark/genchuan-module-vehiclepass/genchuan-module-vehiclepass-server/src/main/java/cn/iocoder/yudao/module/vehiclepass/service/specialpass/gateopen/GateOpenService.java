package cn.iocoder.yudao.module.vehiclepass.service.specialpass.gateopen;

import java.util.*;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenCreateReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenApproveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenRejectReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenExecuteReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenReapplyReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.specialpass.gateopen.GateOpenDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 开闸管理 Service 接口
 *
 * @author 亘川智城
 */
public interface GateOpenService {

    /**
     * 创建开闸管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOpen(@Valid GateOpenSaveReqVO createReqVO);

    /**
     * 新增申请
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOpenApply(@Valid GateOpenCreateReqVO createReqVO);

    /**
     * 更新开闸管理
     *
     * @param updateReqVO 更新信息
     */
    void updateOpen(@Valid GateOpenSaveReqVO updateReqVO);

    /**
     * 删除开闸管理
     *
     * @param id 编号
     */
    void deleteOpen(Long id);

    /**
     * 批量删除开闸管理
     *
     * @param ids 编号
     */
    void deleteOpenListByIds(List<Long> ids);

    /**
     * 获得开闸管理
     *
     * @param id 编号
     * @return 开闸管理
     */
    GateOpenDO getOpen(Long id);

    /**
     * 获得开闸管理分页
     *
     * @param pageReqVO 分页查询
     * @return 开闸管理分页
     */
    PageResult<GateOpenDO> getOpenPage(GateOpenPageReqVO pageReqVO);

    /**
     * 获得开闸管理分页（含关联名称）
     *
     * @param pageReqVO 分页查询
     * @return 开闸管理分页
     */
    PageResult<GateOpenRespVO> getOpenPageWithJoin(GateOpenPageReqVO pageReqVO);

    /**
     * 通过审批
     *
     * @param reqVO 请求
     */
    void approve(GateOpenApproveReqVO reqVO);

    /**
     * 驳回
     *
     * @param reqVO 请求
     */
    void reject(GateOpenRejectReqVO reqVO);

    /**
     * 执行开闸
     *
     * @param reqVO 请求
     */
    void execute(GateOpenExecuteReqVO reqVO);

    /**
     * 重新申请
     *
     * @param reqVO 请求
     */
    void reapply(GateOpenReapplyReqVO reqVO);

    /**
     * 获取统计图表数据
     *
     * @param reqVO 统计请求
     * @return 统计数据
     */
    GateOpenChartRespVO getChart(GateOpenChartReqVO reqVO);

}