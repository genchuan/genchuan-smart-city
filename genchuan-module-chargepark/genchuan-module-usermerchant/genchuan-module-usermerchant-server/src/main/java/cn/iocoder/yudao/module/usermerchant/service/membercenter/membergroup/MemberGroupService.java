package cn.iocoder.yudao.module.usermerchant.service.membercenter.membergroup;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membergroup.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membergroup.MemberGroupDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 会员分组 Service 接口
 *
 * @author 亘川智城
 */
public interface MemberGroupService {

    /**
     * 创建会员分组
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMemberGroup(@Valid MemberGroupSaveReqVO createReqVO);

    /**
     * 更新会员分组
     *
     * @param updateReqVO 更新信息
     */
    void updateMemberGroup(@Valid MemberGroupSaveReqVO updateReqVO);

    /**
     * 删除会员分组
     *
     * @param id 编号
     */
    void deleteMemberGroup(Long id);

    /**
    * 批量删除会员分组
    *
    * @param ids 编号
    */
    void deleteMemberGroupListByIds(List<Long> ids);

    /**
     * 获得会员分组
     *
     * @param id 编号
     * @return 会员分组
     */
    MemberGroupDO getMemberGroup(Long id);

    /**
     * 获得会员分组分页
     *
     * @param pageReqVO 分页查询
     * @return 会员分组分页
     */
    PageResult<MemberGroupDO> getMemberGroupPage(MemberGroupPageReqVO pageReqVO);

    /**
     * 保存会员分组
     *
     * @param saveReqVO 会员分组
     * @return 布尔值
     */
    Boolean saveMemberGroup(@Valid MemberGroupSaveReqVO saveReqVO);

    /**
     * 更新会员分组状态
     *
     * @param ids ids
     * @param status 状态
     */
    void updateGroupStatus(List<Long> ids, Integer status);
}