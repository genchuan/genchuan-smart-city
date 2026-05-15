package cn.iocoder.yudao.module.vehiclepass.service.entermgmt.unplateenter;

import java.util.*;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterAuditReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterConfirmReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterCorrectReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterCreateReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.entermgmt.unplateenter.UnplateEnterDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 无牌入场 Service 接口
 *
 * @author 亘川智城
 */
public interface UnplateEnterService {

    /**
     * 创建无牌入场
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEnter(@Valid UnplateEnterSaveReqVO createReqVO);

    /**
     * 更新无牌入场
     *
     * @param updateReqVO 更新信息
     */
    void updateEnter(@Valid UnplateEnterSaveReqVO updateReqVO);

    /**
     * 删除无牌入场
     *
     * @param id 编号
     */
    void deleteEnter(Long id);

    /**
     * 批量删除无牌入场
     *
     * @param ids 编号
     */
    void deleteEnterListByIds(List<Long> ids);

    /**
     * 获得无牌入场
     *
     * @param id 编号
     * @return 无牌入场
     */
    UnplateEnterDO getEnter(Long id);

    /**
     * 获得无牌入场（含场站名称）
     *
     * @param id 编号
     * @return 无牌入场
     */
    UnplateEnterRespVO getUnplateEnterWithStation(Long id);

    /**
     * 获得无牌入场分页
     *
     * @param pageReqVO 分页查询
     * @return 无牌入场分页
     */
    PageResult<UnplateEnterDO> getEnterPage(UnplateEnterPageReqVO pageReqVO);
    /**
     * 获得无牌入场分页
     *
     * @param reqVO 分页查询
     * @return 无牌入场分页
     */
    PageResult<UnplateEnterRespVO> getUnplateEnterPage(UnplateEnterPageReqVO reqVO);

    /**
     * 创建无牌入场车辆
     *
     * @param createReqVO 创建信息
     */
    void createEnterVehiclePass(UnplateEnterCreateReqVO createReqVO);

    /**
     * 审核无牌入场
     *
     * @param auditReqVO 审核信息
     */
    void auditEnter(UnplateEnterAuditReqVO auditReqVO);

    /**
     * 确认无牌入场
     *
     * @param confirmReqVO 确认信息
     */
    void confirmEnter(UnplateEnterConfirmReqVO confirmReqVO);

    /**
     * 修正无牌入场
     *
     * @param correctReqVO 修正信息
     */
    void correctEnter(UnplateEnterCorrectReqVO correctReqVO);

    /**
     * 获取无牌入场统计
     *
     * @param chartReqVO 统计查询
     * @return 统计数据
     */
    UnplateEnterChartRespVO getUnplateEnterChart(UnplateEnterChartReqVO chartReqVO);
}