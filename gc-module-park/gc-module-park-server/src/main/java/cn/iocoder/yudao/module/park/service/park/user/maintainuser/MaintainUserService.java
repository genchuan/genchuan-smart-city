package cn.iocoder.yudao.module.park.service.park.user.maintainuser;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.user.maintainuser.vo.MaintainUserPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.maintainuser.vo.MaintainUserSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.maintainuser.MaintainUserDO;
import jakarta.validation.Valid;

/**
 * 运维人员 Service 接口
 *
 * @author 亘川智城
 */
public interface MaintainUserService {

    /**
     * 创建运维人员
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMaintainUser(@Valid MaintainUserSaveReqVO createReqVO);

    /**
     * 更新运维人员
     *
     * @param updateReqVO 更新信息
     */
    void updateMaintainUser(@Valid MaintainUserSaveReqVO updateReqVO);

    /**
     * 删除运维人员
     *
     * @param id 编号
     */
    void deleteMaintainUser(Long id);

    /**
     * 获得运维人员
     *
     * @param id 编号
     * @return 运维人员
     */
    MaintainUserDO getMaintainUser(Long id);

    /**
     * 获得运维人员分页
     *
     * @param pageReqVO 分页查询
     * @return 运维人员分页
     */
    PageResult<MaintainUserDO> getMaintainUserPage(MaintainUserPageReqVO pageReqVO);

}
