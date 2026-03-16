package cn.iocoder.yudao.module.envirhealth.service.user.attendanceabnormaltype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendanceabnormaltype.AttendanceAbnormalTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendanceabnormaltype.AttendanceAbnormalTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.AttendanceAbnormalTypeDO;
import jakarta.validation.Valid;

/**
 * 考勤异常类型字典表 Service 接口
 *
 * @author 芋道源码
 */
public interface AttendanceAbnormalTypeService {

    /**
     * 创建考勤异常类型字典表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAttendanceAbnormalType(@Valid AttendanceAbnormalTypeSaveReqVO createReqVO);

    /**
     * 更新考勤异常类型字典表
     *
     * @param updateReqVO 更新信息
     */
    void updateAttendanceAbnormalType(@Valid AttendanceAbnormalTypeSaveReqVO updateReqVO);

    /**
     * 删除考勤异常类型字典表
     *
     * @param id 编号
     */
    void deleteAttendanceAbnormalType(Long id);

    /**
     * 获得考勤异常类型字典表
     *
     * @param id 编号
     * @return 考勤异常类型字典表
     */
    AttendanceAbnormalTypeDO getAttendanceAbnormalType(Long id);

    /**
     * 获得考勤异常类型字典表分页
     *
     * @param pageReqVO 分页查询
     * @return 考勤异常类型字典表分页
     */
    PageResult<AttendanceAbnormalTypeDO> getAttendanceAbnormalTypePage(AttendanceAbnormalTypePageReqVO pageReqVO);

}