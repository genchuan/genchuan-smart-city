package cn.iocoder.yudao.module.usermerchant.service.usermgmt.userinfo;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.userinfo.UserInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 用户信息 Service 接口
 *
 * @author 亘川智城
 */
public interface UserInfoService {

    /**
     * 创建用户信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Boolean createUserInfo(@Valid UserInfoCreateReqVO createReqVO);

    /**
     * 更新用户信息
     *
     * @param updateReqVO 更新信息
     */
    void updateUserInfo(@Valid UserInfoUpdateReqVO updateReqVO);

    /**
     * 获得用户信息
     *
     * @param id 编号
     * @return 用户信息
     */
    UserInfoDO getUserInfo(Long id);

    /**
     * 获得用户信息分页
     *
     * @param pageReqVO 分页查询
     * @return 用户信息分页
     */
    PageResult<UserInfoDO> getUserInfoPage(UserInfoPageReqVO pageReqVO);

    /**
     * 导入用户信息
     *
     * @param list 用户信息
     */
    Boolean importUsers(List<UserInfoImportExcelVO> list, Boolean updateSupport);

    /**
     * 批量禁用用户信息
     *
     * @param ids 编号
     */
    void updateUserStatus(List<Long> ids, String status);

    /**
     * 用户信息统计可视化
     *
     * @param chartReqVO 时间范围
     * @return 统计信息
     */
    UserInfoChartRespVO getUserInfoChart(@Valid UserInfoChartReqVO chartReqVO);
}