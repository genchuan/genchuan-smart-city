package cn.iocoder.yudao.module.usermerchant.service.membercenter.memberlevel;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberlevel.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberlevel.MemberLevelDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 会员等级 Service 接口
 *
 * @author 亘川智城
 */
public interface MemberLevelService {

    /**
     * 创建会员等级
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMemberLevel(@Valid MemberLevelSaveReqVO createReqVO);

    /**
     * 更新会员等级
     *
     * @param updateReqVO 更新信息
     */
    void updateMemberLevel(@Valid MemberLevelSaveReqVO updateReqVO);

    /**
     * 删除会员等级
     *
     * @param id 编号
     */
    void deleteMemberLevel(Long id);

    /**
    * 批量删除会员等级
    *
    * @param ids 编号
    */
    void deleteMemberLevelListByIds(List<Long> ids);

    /**
     * 获得会员等级
     *
     * @param id 编号
     * @return 会员等级
     */
    MemberLevelDO getMemberLevel(Long id);

    /**
     * 获得会员等级分页
     *
     * @param pageReqVO 分页查询
     * @return 会员等级分页
     */
    PageResult<MemberLevelDO> getMemberLevelPage(MemberLevelPageReqVO pageReqVO);

}