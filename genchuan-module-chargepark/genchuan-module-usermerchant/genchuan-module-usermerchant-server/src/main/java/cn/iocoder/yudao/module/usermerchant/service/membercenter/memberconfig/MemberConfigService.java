package cn.iocoder.yudao.module.usermerchant.service.membercenter.memberconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo.MemberConfigPageReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo.MemberConfigSaveReqVO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberconfig.MemberConfigDO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

/**
 * 会员配置 Service 接口
 *
 * @author QingX
 */
public interface MemberConfigService {

    /**
     * 获得会员配置分页
     *
     * @param pageReqVO 分页查询
     * @return 会员配置分页
     */
    PageResult<MemberConfigDO> getMemberConfigPage(@Valid MemberConfigPageReqVO pageReqVO);

    /**
     * 创建会员配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMemberConfig(@Valid MemberConfigSaveReqVO createReqVO);

    /**
     * 保存会员配置
     *
     * @param saveReqVO 更新信息
     */
    void saveConfig(@Valid MemberConfigSaveReqVO saveReqVO);

    /**
     * 批量生效，禁用会员配置
     *
     * @param ids 编号
     */
    void updateConfigStatus(List<Long> ids, String Status);

    /**
     * 获得会员配置
     *
     * @param id 编号
     * @return 会员配置
     */
    MemberConfigDO getMemberConfig(Long id);

    /**
     * 获得会员配置 old ver
     *
     * @return 第一条积分配置
     */
    MemberConfigDO getConfig();
}
