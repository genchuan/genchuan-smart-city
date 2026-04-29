package cn.iocoder.yudao.module.usermerchant.service.membercenter.membertag;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membertag.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membertag.MemberTagDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 会员标签 Service 接口
 *
 * @author 亘川智城
 */
public interface MemberTagService {

    /**
     * 创建会员标签
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMemberTag(@Valid MemberTagSaveReqVO createReqVO);

    /**
     * 更新会员标签
     *
     * @param updateReqVO 更新信息
     */
    void updateMemberTag(@Valid MemberTagSaveReqVO updateReqVO);

    /**
     * 删除会员标签
     *
     * @param id 编号
     */
    void deleteMemberTag(Long id);

    /**
    * 批量删除会员标签
    *
    * @param ids 编号
    */
    void deleteMemberTagListByIds(List<Long> ids);

    /**
     * 获得会员标签
     *
     * @param id 编号
     * @return 会员标签
     */
    MemberTagDO getMemberTag(Long id);

    /**
     * 获得会员标签分页
     *
     * @param pageReqVO 分页查询
     * @return 会员标签分页
     */
    PageResult<MemberTagDO> getMemberTagPage(MemberTagPageReqVO pageReqVO);

}