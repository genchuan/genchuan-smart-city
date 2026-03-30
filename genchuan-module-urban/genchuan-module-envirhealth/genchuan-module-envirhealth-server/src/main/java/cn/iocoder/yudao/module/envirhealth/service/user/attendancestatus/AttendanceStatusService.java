package cn.iocoder.yudao.module.envirhealth.service.user.attendancestatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendancestatus.AttendanceStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendancestatus.AttendanceStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.AttendanceStatusDO;
import jakarta.validation.Valid;

/**
 * 考勤状态字典表 Service 接口
 *
 * @author 芋道源码
 */
public interface AttendanceStatusService {

    /**
     * 创建考勤状态字典表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAttendanceStatus(@Valid AttendanceStatusSaveReqVO createReqVO);

    /**
     * 更新考勤状态字典表
     *
     * @param updateReqVO 更新信息
     */
    void updateAttendanceStatus(@Valid AttendanceStatusSaveReqVO updateReqVO);

    /**
     * 删除考勤状态字典表
     *
     * @param id 编号
     */
    void deleteAttendanceStatus(Long id);

    /**
     * 获得考勤状态字典表
     *
     * @param id 编号
     * @return 考勤状态字典表
     */
    AttendanceStatusDO getAttendanceStatus(Long id);

    /**
     * 获得考勤状态字典表分页
     *
     * @param pageReqVO 分页查询
     * @return 考勤状态字典表分页
     */
    PageResult<AttendanceStatusDO> getAttendanceStatusPage(AttendanceStatusPageReqVO pageReqVO);

}