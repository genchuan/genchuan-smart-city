package cn.iocoder.yudao.module.usermerchant.service.membercenter.memberpoint;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberpoint.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberpoint.MemberPointDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 会员积分 Service 接口
 *
 * @author 亘川智城
 */
public interface MemberPointService {

    /**
     * 创建会员积分
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMemberPoint(@Valid MemberPointSaveReqVO createReqVO);

    /**
     * 更新会员积分
     *
     * @param updateReqVO 更新信息
     */
    void updateMemberPoint(@Valid MemberPointSaveReqVO updateReqVO);

    /**
     * 删除会员积分
     *
     * @param id 编号
     */
    void deleteMemberPoint(Long id);

    /**
    * 批量删除会员积分
    *
    * @param ids 编号
    */
    void deleteMemberPointListByIds(List<Long> ids);

    /**
     * 获得会员积分
     *
     * @param id 编号
     * @return 会员积分
     */
    MemberPointDO getMemberPoint(Long id);

    /**
     * 获得会员积分分页
     *
     * @param pageReqVO 分页查询
     * @return 会员积分分页
     */
    PageResult<MemberPointDO> getMemberPointPage(MemberPointPageReqVO pageReqVO);

    /**
     * 核查用户积分
     *
     * @param reqVO 核查信息
     */
    void checkPointRecord(@Valid MemberPointCheckReqVO reqVO);
}