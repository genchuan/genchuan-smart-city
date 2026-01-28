package cn.iocoder.yudao.module.park.service.park.user.governmentdepartment;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.user.governmentdepartment.vo.GovernmentDepartmentPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.governmentdepartment.vo.GovernmentDepartmentSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.governmentdepartment.GovernmentDepartmentDO;
import jakarta.validation.Valid;

/**
 * 政府部门 Service 接口
 *
 * @author 亘川智城
 */
public interface GovernmentDepartmentService {

    /**
     * 创建政府部门
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGovernmentDepartment(@Valid GovernmentDepartmentSaveReqVO createReqVO);

    /**
     * 更新政府部门
     *
     * @param updateReqVO 更新信息
     */
    void updateGovernmentDepartment(@Valid GovernmentDepartmentSaveReqVO updateReqVO);

    /**
     * 删除政府部门
     *
     * @param id 编号
     */
    void deleteGovernmentDepartment(Long id);

    /**
     * 获得政府部门
     *
     * @param id 编号
     * @return 政府部门
     */
    GovernmentDepartmentDO getGovernmentDepartment(Long id);

    /**
     * 获得政府部门分页
     *
     * @param pageReqVO 分页查询
     * @return 政府部门分页
     */
    PageResult<GovernmentDepartmentDO> getGovernmentDepartmentPage(GovernmentDepartmentPageReqVO pageReqVO);

}
