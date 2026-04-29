package cn.iocoder.yudao.module.usermerchant.service.membercenter.memberuser;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberuser.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberuser.MemberUserDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 会员用户 Service 接口
 *
 * @author 亘川智城
 */
public interface MemberUserService {

    /**
     * 创建会员用户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMemberUser(@Valid MemberUserSaveReqVO createReqVO);

    /**
     * 更新会员用户
     *
     * @param updateReqVO 更新信息
     */
    void updateMemberUser(@Valid MemberUserSaveReqVO updateReqVO);

    /**
     * 删除会员用户
     *
     * @param id 编号
     */
    void deleteMemberUser(Long id);

    /**
    * 批量删除会员用户
    *
    * @param ids 编号
    */
    void deleteMemberUserListByIds(List<Long> ids);

    /**
     * 获得会员用户
     *
     * @param id 编号
     * @return 会员用户
     */
    MemberUserDO getMemberUser(Long id);

    /**
     * 获得会员用户分页
     *
     * @param pageReqVO 分页查询
     * @return 会员用户分页
     */
    PageResult<MemberUserDO> getMemberUserPage(MemberUserPageReqVO pageReqVO);

}