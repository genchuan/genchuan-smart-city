package cn.iocoder.yudao.module.usermerchant.api.membercenter.memberconfig;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.usermerchant.api.membercenter.memberconfig.dto.MemberConfigRespDTO;
import cn.iocoder.yudao.module.usermerchant.convert.membercenter.memberconfig.MemberConfigConvert;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.memberconfig.MemberConfigService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 用户配置 API 实现类
 *
 * @author owen
 */
@RestController // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class MemberConfigApiImpl implements MemberConfigApi {

    @Resource
    private MemberConfigService memberConfigService;

    @Override
    public CommonResult<MemberConfigRespDTO> getMemberConfig(Long id) {
        return success(MemberConfigConvert.INSTANCE.convert01(memberConfigService.getMemberConfig(id)));
    }

}
