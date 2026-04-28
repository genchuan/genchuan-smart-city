package cn.iocoder.yudao.module.usermerchant.controller.app.membercenter.memberlevel;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.usermerchant.controller.app.membercenter.memberlevel.vo.level.AppMemberLevelRespVO;
import cn.iocoder.yudao.module.usermerchant.convert.membercenter.memberlevel.MemberLevelConvert;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberlevel.MemberLevelDO;
import cn.iocoder.yudao.module.usermerchant.service.membercenter.memberlevel.MemberLevelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 App - 会员等级")
@RestController
@RequestMapping("/member/level")
@Validated
public class AppMemberLevelController {

    @Resource
    private MemberLevelService levelService;

    @GetMapping("/list")
    @Operation(summary = "获得会员等级列表")
    @PermitAll
    public CommonResult<List<AppMemberLevelRespVO>> getLevelList() {
        List<MemberLevelDO> result = levelService.getEnableLevelList();
        return success(MemberLevelConvert.INSTANCE.convertList02(result));
    }

}
