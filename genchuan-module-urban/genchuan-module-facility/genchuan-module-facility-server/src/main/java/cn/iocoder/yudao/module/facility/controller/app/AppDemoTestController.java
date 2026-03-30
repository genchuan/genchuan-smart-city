package cn.iocoder.yudao.module.facility.controller.app;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 App - Test")
@RestController
@RequestMapping("/facility/test")
@Validated
public class AppDemoTestController {

    // 这个构造方法，只是方便大家，验证 Controller 有生效
    public AppDemoTestController() {
        System.out.println(getClass() + "生效啦！！！");
    }

    @GetMapping("/get")
    @Operation(summary = "获取 test2 信息")
    public CommonResult<String> get() {
        return success("2026");
    }

    @GetMapping("/get123")
    @Operation(summary = "获取 test123 信息")
    public CommonResult<String> get123() {
        return success("2026");
    }

}
