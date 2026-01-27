package cn.iocoder.yudao.module.park.service.park.user.user;

import java.util.*;

import cn.iocoder.yudao.module.park.controller.admin.park.user.user.vo.UserPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.user.vo.UserSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.user.UserDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 系统用户 Service 接口
 *
 * @author 亘川智城
 */
public interface UserService {

    /**
     * 创建系统用户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUser(@Valid UserSaveReqVO createReqVO);

    /**
     * 更新系统用户
     *
     * @param updateReqVO 更新信息
     */
    void updateUser(@Valid UserSaveReqVO updateReqVO);

    /**
     * 删除系统用户
     *
     * @param id 编号
     */
    void deleteUser(Long id);

    /**
     * 获得系统用户
     *
     * @param id 编号
     * @return 系统用户
     */
    UserDO getUser(Long id);

    /**
     * 获得系统用户分页
     *
     * @param pageReqVO 分页查询
     * @return 系统用户分页
     */
    PageResult<UserDO> getUserPage(UserPageReqVO pageReqVO);

}
