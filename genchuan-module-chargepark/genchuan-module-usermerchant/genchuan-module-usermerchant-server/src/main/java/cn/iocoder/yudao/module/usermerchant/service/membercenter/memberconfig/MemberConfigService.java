package cn.iocoder.yudao.module.usermerchant.service.membercenter.memberconfig;

import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo.MemberConfigSaveReqVO;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberconfig.MemberConfigDO;
import jakarta.validation.Valid;

/**
 * 会员配置 Service 接口
 *
 * @author QingX
 */
public interface MemberConfigService {

    /**
     * 保存会员配置
     *
     * @param saveReqVO 更新信息
     */
    void saveConfig(@Valid MemberConfigSaveReqVO saveReqVO);

    /**
     * 获得会员配置
     *
     * @return 积分配置
     */
    MemberConfigDO getConfig();

}
