package cn.iocoder.yudao.module.envirhealth.service.user.attendancestatus;

import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendancestatus.AttendanceStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendancestatus.AttendanceStatusSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.AttendanceStatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.user.AttendanceStatusMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 考勤状态字典表 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AttendanceStatusServiceImpl implements AttendanceStatusService {

    @Resource
    private AttendanceStatusMapper attendanceStatusMapper;

    @Override
    public Long createAttendanceStatus(AttendanceStatusSaveReqVO createReqVO) {
        // 插入
        AttendanceStatusDO attendanceStatus = BeanUtils.toBean(createReqVO, AttendanceStatusDO.class);
        attendanceStatusMapper.insert(attendanceStatus);
        // 返回
        return attendanceStatus.getId();
    }

    @Override
    public void updateAttendanceStatus(AttendanceStatusSaveReqVO updateReqVO) {
        // 校验存在
        validateAttendanceStatusExists(updateReqVO.getId());
        // 更新
        AttendanceStatusDO updateObj = BeanUtils.toBean(updateReqVO, AttendanceStatusDO.class);
        attendanceStatusMapper.updateById(updateObj);
    }

    @Override
    public void deleteAttendanceStatus(Long id) {
        // 校验存在
        validateAttendanceStatusExists(id);
        // 删除
        attendanceStatusMapper.deleteById(id);
    }

    private void validateAttendanceStatusExists(Long id) {
        if (attendanceStatusMapper.selectById(id) == null) {
            throw exception(ATTENDANCE_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public AttendanceStatusDO getAttendanceStatus(Long id) {
        return attendanceStatusMapper.selectById(id);
    }

    @Override
    public PageResult<AttendanceStatusDO> getAttendanceStatusPage(AttendanceStatusPageReqVO pageReqVO) {
        return attendanceStatusMapper.selectPage(pageReqVO);
    }

}