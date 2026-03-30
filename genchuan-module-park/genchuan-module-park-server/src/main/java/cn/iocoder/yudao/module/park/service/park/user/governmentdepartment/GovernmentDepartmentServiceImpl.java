package cn.iocoder.yudao.module.park.service.park.user.governmentdepartment;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.governmentdepartment.vo.GovernmentDepartmentPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.governmentdepartment.vo.GovernmentDepartmentSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.governmentdepartment.GovernmentDepartmentDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.user.governmentdepartment.GovernmentDepartmentMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.GOVERNMENT_DEPARTMENT_NOT_EXISTS;

/**
 * 政府部门 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class GovernmentDepartmentServiceImpl implements GovernmentDepartmentService {

    @Resource
    private GovernmentDepartmentMapper governmentDepartmentMapper;

    @Override
    public Long createGovernmentDepartment(GovernmentDepartmentSaveReqVO createReqVO) {
        // 插入
        GovernmentDepartmentDO governmentDepartment = BeanUtils.toBean(createReqVO, GovernmentDepartmentDO.class);
        governmentDepartmentMapper.insert(governmentDepartment);
        // 返回
        return governmentDepartment.getId();
    }

    @Override
    public void updateGovernmentDepartment(GovernmentDepartmentSaveReqVO updateReqVO) {
        // 校验存在
        validateGovernmentDepartmentExists(updateReqVO.getId());
        // 更新
        GovernmentDepartmentDO updateObj = BeanUtils.toBean(updateReqVO, GovernmentDepartmentDO.class);
        governmentDepartmentMapper.updateById(updateObj);
    }

    @Override
    public void deleteGovernmentDepartment(Long id) {
        // 校验存在
        validateGovernmentDepartmentExists(id);
        // 删除
        governmentDepartmentMapper.deleteById(id);
    }

    private void validateGovernmentDepartmentExists(Long id) {
        if (governmentDepartmentMapper.selectById(id) == null) {
            throw exception(GOVERNMENT_DEPARTMENT_NOT_EXISTS);
        }
    }

    @Override
    public GovernmentDepartmentDO getGovernmentDepartment(Long id) {
        return governmentDepartmentMapper.selectById(id);
    }

    @Override
    public PageResult<GovernmentDepartmentDO> getGovernmentDepartmentPage(GovernmentDepartmentPageReqVO pageReqVO) {
        return governmentDepartmentMapper.selectPage(pageReqVO);
    }

}
