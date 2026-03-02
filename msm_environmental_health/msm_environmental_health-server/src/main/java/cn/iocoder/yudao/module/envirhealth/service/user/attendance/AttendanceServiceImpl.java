package cn.iocoder.yudao.module.envirhealth.service.user.attendance;

import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendance.AttendancePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendance.AttendanceSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.AttendanceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.user.AttendanceMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 考勤 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AttendanceServiceImpl implements AttendanceService {

    @Resource
    private AttendanceMapper attendanceMapper;

    @Override
    public Long createAttendance(AttendanceSaveReqVO createReqVO) {
        // 插入
        AttendanceDO attendance = BeanUtils.toBean(createReqVO, AttendanceDO.class);
        attendanceMapper.insert(attendance);
        // 返回
        return attendance.getId();
    }

    @Override
    public void updateAttendance(AttendanceSaveReqVO updateReqVO) {
        // 校验存在
        validateAttendanceExists(updateReqVO.getId());
        // 更新
        AttendanceDO updateObj = BeanUtils.toBean(updateReqVO, AttendanceDO.class);
        attendanceMapper.updateById(updateObj);
    }

    @Override
    public void deleteAttendance(Long id) {
        // 校验存在
        validateAttendanceExists(id);
        // 删除
        attendanceMapper.deleteById(id);
    }

    private void validateAttendanceExists(Long id) {
        if (attendanceMapper.selectById(id) == null) {
            throw exception(ATTENDANCE_NOT_EXISTS);
        }
    }

    @Override
    public AttendanceDO getAttendance(Long id) {
        return attendanceMapper.selectById(id);
    }

    @Override
    public PageResult<AttendanceDO> getAttendancePage(AttendancePageReqVO pageReqVO) {
        return attendanceMapper.selectPage(pageReqVO);
    }

}