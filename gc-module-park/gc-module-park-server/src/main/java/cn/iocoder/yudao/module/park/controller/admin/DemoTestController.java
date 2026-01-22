package cn.iocoder.yudao.module.park.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUser;

@Tag(name = "管理后台 - Test")
@RestController
@RequestMapping("/park/demo-test")
@Validated
public class DemoTestController {

    // 这个构造方法，只是方便大家，验证 Controller 有生效
    public DemoTestController() {
        System.out.println(getClass() + "生效啦！！！");
    }

    @GetMapping("/get")
    @Operation(summary = "获取 test 信息")
    public CommonResult<String> get() {
        return success("admin true");
    }

    // 在你的微服务中临时添加一个测试端点


        @Value("${xxl.job.admin.addresses}")
        private String adminAddresses;

        @GetMapping("/xxl-config")
        public Map<String, String> getXxlConfig() {
            return Map.of(
                    "adminAddresses", adminAddresses,
                    "configSource", "检查配置来源"
            );
        }

        @GetMapping("/getXXXUser")
        public Object getXXXUser() {
            return getLoginUser();
        }

}
