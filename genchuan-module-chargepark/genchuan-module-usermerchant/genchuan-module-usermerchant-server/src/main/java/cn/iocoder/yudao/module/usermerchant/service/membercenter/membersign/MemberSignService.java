package cn.iocoder.yudao.module.usermerchant.service.membercenter.membersign;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membersign.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membersign.MemberSignDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 会员签到 Service 接口
 *
 * @author 亘川智城
 */
public interface MemberSignService {

    /**
     * 创建会员签到
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMemberSign(@Valid MemberSignSaveReqVO createReqVO);

    /**
     * 更新会员签到
     *
     * @param updateReqVO 更新信息
     */
    void updateMemberSign(@Valid MemberSignSaveReqVO updateReqVO);

    /**
     * 删除会员签到
     *
     * @param id 编号
     */
    void deleteMemberSign(Long id);

    /**
    * 批量删除会员签到
    *
    * @param ids 编号
    */
    void deleteMemberSignListByIds(List<Long> ids);

    /**
     * 获得会员签到
     *
     * @param id 编号
     * @return 会员签到
     */
    MemberSignDO getMemberSign(Long id);

    /**
     * 获得会员签到分页
     *
     * @param pageReqVO 分页查询
     * @return 会员签到分页
     */
    PageResult<MemberSignDO> getMemberSignPage(MemberSignPageReqVO pageReqVO);

}