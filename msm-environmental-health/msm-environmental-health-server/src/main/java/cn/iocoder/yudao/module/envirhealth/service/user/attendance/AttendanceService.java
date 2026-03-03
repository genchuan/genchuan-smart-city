package cn.iocoder.yudao.module.envirhealth.service.user.attendance;

import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendance.AttendancePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendance.AttendanceSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.AttendanceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 考勤 Service 接口
 *
 * @author 芋道源码
 */
public interface AttendanceService {

    /**
     * 创建考勤
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAttendance(@Valid AttendanceSaveReqVO createReqVO);

    /**
     * 更新考勤
     *
     * @param updateReqVO 更新信息
     */
    void updateAttendance(@Valid AttendanceSaveReqVO updateReqVO);

    /**
     * 删除考勤
     *
     * @param id 编号
     */
    void deleteAttendance(Long id);

    /**
     * 获得考勤
     *
     * @param id 编号
     * @return 考勤
     */
    AttendanceDO getAttendance(Long id);

    /**
     * 获得考勤分页
     *
     * @param pageReqVO 分页查询
     * @return 考勤分页
     */
    PageResult<AttendanceDO> getAttendancePage(AttendancePageReqVO pageReqVO);

}