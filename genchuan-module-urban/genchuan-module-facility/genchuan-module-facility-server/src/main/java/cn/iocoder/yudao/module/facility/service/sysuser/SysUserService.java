package cn.iocoder.yudao.module.facility.service.sysuser;

import java.util.*;

import cn.iocoder.yudao.module.facility.controller.admin.sysuser.vo.SysUserPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.sysuser.vo.SysUserSaveReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.sysuser.SysUserDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 系统用户 Service 接口
 *
 * @author 亘川智城
 */
public interface SysUserService {

    /**
     * 创建系统用户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSysUser(@Valid SysUserSaveReqVO createReqVO);

    /**
     * 更新系统用户
     *
     * @param updateReqVO 更新信息
     */
    void updateSysUser(@Valid SysUserSaveReqVO updateReqVO);

    /**
     * 删除系统用户
     *
     * @param id 编号
     */
    void deleteSysUser(Long id);

    /**
     * 获得系统用户
     *
     * @param id 编号
     * @return 系统用户
     */
    SysUserDO getSysUser(Long id);

    /**
     * 获得系统用户分页
     *
     * @param pageReqVO 分页查询
     * @return 系统用户分页
     */
    PageResult<SysUserDO> getSysUserPage(SysUserPageReqVO pageReqVO);

}
