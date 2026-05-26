package cn.iocoder.yudao.module.usermerchant.service.membercenter.memberconfig;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberconfig.MemberConfigDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 会员配置 Service 接口
 *
 * @author 亘川智城
 */
public interface MemberConfigService {

    /**
     * 创建会员配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMemberConfig(@Valid MemberConfigSaveReqVO createReqVO);

    /**
     * 更新会员配置
     *
     * @param updateReqVO 更新信息
     */
    void updateMemberConfig(@Valid MemberConfigSaveReqVO updateReqVO);

    /**
     * 删除会员配置
     *
     * @param id 编号
     */
    void deleteMemberConfig(Long id);

    /**
    * 批量删除会员配置
    *
    * @param ids 编号
    */
    void deleteMemberConfigListByIds(List<Long> ids);

    /**
     * 获得会员配置
     *
     * @param id 编号
     * @return 会员配置
     */
    MemberConfigDO getMemberConfig(Long id);

    /**
     * 获得会员配置分页
     *
     * @param pageReqVO 分页查询
     * @return 会员配置分页
     */
    PageResult<MemberConfigDO> getMemberConfigPage(MemberConfigPageReqVO pageReqVO);

    /**
     * 保存会员配置
     *
     * @param saveReqVO 保存信息
     */
    Boolean saveMemberConfig(@Valid MemberConfigSaveReqVO saveReqVO);

    /**
     * 改变会员配置状态
     *
     * @param ids ids
     * @param status 状态
     */
    void updateConfigStatus(List<Long> ids, Integer status);

}