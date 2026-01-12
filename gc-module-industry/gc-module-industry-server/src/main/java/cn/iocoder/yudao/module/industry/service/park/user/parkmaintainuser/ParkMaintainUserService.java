package cn.iocoder.yudao.module.industry.service.park.user.parkmaintainuser;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainuser.vo.ParkMaintainUserPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainuser.vo.ParkMaintainUserSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkmaintainuser.ParkMaintainUserDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 运维人员 Service 接口
 *
 * @author lxs
 */
public interface ParkMaintainUserService {

    /**
     * 创建运维人员
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkMaintainUser(@Valid ParkMaintainUserSaveReqVO createReqVO);

    /**
     * 更新运维人员
     *
     * @param updateReqVO 更新信息
     */
    void updateParkMaintainUser(@Valid ParkMaintainUserSaveReqVO updateReqVO);

    /**
     * 删除运维人员
     *
     * @param id 编号
     */
    void deleteParkMaintainUser(Long id);

    /**
     * 获得运维人员
     *
     * @param id 编号
     * @return 运维人员
     */
    ParkMaintainUserDO getParkMaintainUser(Long id);

    /**
     * 获得运维人员分页
     *
     * @param pageReqVO 分页查询
     * @return 运维人员分页
     */
    PageResult<ParkMaintainUserDO> getParkMaintainUserPage(ParkMaintainUserPageReqVO pageReqVO);

}
