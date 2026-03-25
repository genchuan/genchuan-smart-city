package cn.iocoder.yudao.module.smartcity.service.drainageuser;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.drainageuser.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.drainageuser.DrainageUserDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 排水户信息 Service 接口
 *
 * @author 超级管理员
 */
public interface DrainageUserService {

    /**
     * 创建排水户信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDrainageUser(@Valid DrainageUserSaveReqVO createReqVO);

    /**
     * 更新排水户信息
     *
     * @param updateReqVO 更新信息
     */
    void updateDrainageUser(@Valid DrainageUserSaveReqVO updateReqVO);

    /**
     * 删除排水户信息
     *
     * @param id 编号
     */
    void deleteDrainageUser(Long id);

    /**
     * 获得排水户信息
     *
     * @param id 编号
     * @return 排水户信息
     */
    DrainageUserDO getDrainageUser(Long id);

    /**
     * 获得排水户信息分页
     *
     * @param pageReqVO 分页查询
     * @return 排水户信息分页
     */
    PageResult<DrainageUserDO> getDrainageUserPage(DrainageUserPageReqVO pageReqVO);

}