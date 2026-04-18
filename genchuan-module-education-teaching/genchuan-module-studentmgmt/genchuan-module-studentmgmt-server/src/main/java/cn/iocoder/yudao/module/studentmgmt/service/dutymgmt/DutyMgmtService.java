package cn.iocoder.yudao.module.studentmgmt.service.dutymgmt;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dutymgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dutymgmt.DutyMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 值班管理 Service 接口
 *
 * @author 芋道源码
 */
public interface DutyMgmtService {

    /**
     * 创建值班管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDutyMgmt(@Valid DutyMgmtSaveReqVO createReqVO);

    /**
     * 更新值班管理
     *
     * @param updateReqVO 更新信息
     */
    void updateDutyMgmt(@Valid DutyMgmtUpdateReqVO updateReqVO);

    /**
     * 删除值班管理
     *
     * @param id 编号
     */
    void deleteDutyMgmt(Long id);

    /**
    * 批量删除值班管理
    *
    * @param ids 编号
    */
    void deleteDutyMgmtListByIds(List<Long> ids);

    /**
     * 获得值班管理
     *
     * @param id 编号
     * @return 值班管理
     */
    DutyMgmtDO getDutyMgmt(Long id);

    /**
     * 获得值班管理分页
     *
     * @param pageReqVO 分页查询
     * @return 值班管理分页
     */
    PageResult<DutyMgmtDO> getDutyMgmtPage(DutyMgmtPageReqVO pageReqVO);

    boolean schedule(@Valid DutyMgmtScheduleReqVO updateReqVO);

    boolean checkin(@Valid DutyMgmtCheckinReqVO reqVo);

    boolean shiftApply(@Valid DutyMgmtShiftApplyReqVO reqVo);

     boolean shiftAudit(@Valid DutyMgmtShiftAuditReqVO reqVo);

    boolean vehicleApply(@Valid DutyMgmtVehicleApplyReqVO reqVo);

    boolean vehicleAudit(@Valid DutyMgmtShiftAuditReqVO reqVo);

    boolean uploadRecord(@Valid DutyMgmtUploadRecordReqVO reqVo);

    DutyMgmtChartRespVO chart(@Valid DutyMgmtChartReqVO reqVo);

    DutyMgmtChartIndexRespVO dutyIndex(@Valid DutyMgmtChartReqVO reqVo);
}