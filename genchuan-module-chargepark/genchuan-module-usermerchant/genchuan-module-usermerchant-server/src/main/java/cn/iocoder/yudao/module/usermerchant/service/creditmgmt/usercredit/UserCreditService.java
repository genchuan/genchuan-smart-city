package cn.iocoder.yudao.module.usermerchant.service.creditmgmt.usercredit;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.usermerchant.controller.admin.creditmgmt.usercredit.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.creditmgmt.usercredit.UserCreditDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 用户信用 Service 接口
 *
 * @author 亘川智城
 */
public interface UserCreditService {

    /**
     * 创建用户信用
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUserCredit(@Valid UserCreditSaveReqVO createReqVO);

    /**
     * 更新用户信用
     *
     * @param updateReqVO 更新信息
     */
    void updateUserCredit(@Valid UserCreditSaveReqVO updateReqVO);

    /**
     * 删除用户信用
     *
     * @param id 编号
     */
    void deleteUserCredit(Long id);

    /**
    * 批量删除用户信用
    *
    * @param ids 编号
    */
    void deleteUserCreditListByIds(List<Long> ids);

    /**
     * 获得用户信用
     *
     * @param id 编号
     * @return 用户信用
     */
    UserCreditDO getUserCredit(Long id);

    /**
     * 获得用户信用分页
     *
     * @param pageReqVO 分页查询
     * @return 用户信用分页
     */
    PageResult<UserCreditDO> getUserCreditPage(UserCreditPageReqVO pageReqVO);

    /**
     * 用户信用提醒（批量）
     *
     * @param ids 信用ID数组
     */
    void remindUserCredit(List<Long> ids);

    /**
     * 用户信用统计可视化
     *
     * @param chartReqVO 时间范围
     * @return 统计信息
     */
    UserCreditChartRespVO getUserCreditChart(@Valid UserCreditChartReqVO chartReqVO);

}