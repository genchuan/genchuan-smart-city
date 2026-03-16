package cn.iocoder.yudao.module.envirhealth.service.user.attendanceabnormaltype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendanceabnormaltype.AttendanceAbnormalTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.attendanceabnormaltype.AttendanceAbnormalTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.AttendanceAbnormalTypeDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.user.AttendanceAbnormalTypeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.ATTENDANCE_ABNORMAL_TYPE_NOT_EXISTS;

/**
 * 考勤异常类型字典表 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AttendanceAbnormalTypeServiceImpl implements AttendanceAbnormalTypeService {

    @Resource
    private AttendanceAbnormalTypeMapper attendanceAbnormalTypeMapper;

    @Override
    public Long createAttendanceAbnormalType(AttendanceAbnormalTypeSaveReqVO createReqVO) {
        // 插入
        AttendanceAbnormalTypeDO attendanceAbnormalType = BeanUtils.toBean(createReqVO, AttendanceAbnormalTypeDO.class);
        attendanceAbnormalTypeMapper.insert(attendanceAbnormalType);
        // 返回
        return attendanceAbnormalType.getId();
    }

    @Override
    public void updateAttendanceAbnormalType(AttendanceAbnormalTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateAttendanceAbnormalTypeExists(updateReqVO.getId());
        // 更新
        AttendanceAbnormalTypeDO updateObj = BeanUtils.toBean(updateReqVO, AttendanceAbnormalTypeDO.class);
        attendanceAbnormalTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteAttendanceAbnormalType(Long id) {
        // 校验存在
        validateAttendanceAbnormalTypeExists(id);
        // 删除
        attendanceAbnormalTypeMapper.deleteById(id);
    }

    private void validateAttendanceAbnormalTypeExists(Long id) {
        if (attendanceAbnormalTypeMapper.selectById(id) == null) {
            throw exception(ATTENDANCE_ABNORMAL_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public AttendanceAbnormalTypeDO getAttendanceAbnormalType(Long id) {
        return attendanceAbnormalTypeMapper.selectById(id);
    }

    @Override
    public PageResult<AttendanceAbnormalTypeDO> getAttendanceAbnormalTypePage(AttendanceAbnormalTypePageReqVO pageReqVO) {
        return attendanceAbnormalTypeMapper.selectPage(pageReqVO);
    }

}