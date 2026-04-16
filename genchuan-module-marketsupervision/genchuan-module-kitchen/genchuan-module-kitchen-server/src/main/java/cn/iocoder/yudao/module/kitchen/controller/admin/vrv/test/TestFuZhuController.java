package cn.iocoder.yudao.module.kitchen.controller.admin.vrv.test;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Manifest;

@Tag(name = "管理后台 - 测试辅助")
@RestController
@RequestMapping("/kitchen/test-fuzhu")
@Validated
public class TestFuZhuController {

    @GetMapping("/memory")
    @Operation(summary = "获取当前服务内存占用")
    public CommonResult<Map<String, Object>> getServerMemory() {
        MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();

        // 转 MB
        long init = heapMemoryUsage.getInit() / 1024 / 1024;
        long used = heapMemoryUsage.getUsed() / 1024 / 1024;
        long committed = heapMemoryUsage.getCommitted() / 1024 / 1024;
        long max = heapMemoryUsage.getMax() / 1024 / 1024;

        Map<String, Object> data = new HashMap<>();
        data.put("已使用内存(服务真正占用的内存)(MB)", used);  // <-- 服务真正占用的内存
        data.put("初始内存(MB)", init);
        data.put("已分配内存(MB)", committed);
        data.put("最大可用内存(MB)", max);
        data.put("单位", "MB");

        return CommonResult.success(data);
    }

    // 2. 获取【系统代码最新更新/打包时间】
    @GetMapping("/system-build-time")
    @Operation(summary = "获取系统最新更新/启动时间")
    public CommonResult<Map<String, Object>> getSystemBuildTime() {
        Map<String, Object> data = new HashMap<>();

        // 获取服务启动时间（永远不报错，最准确的系统更新时间）
        long startTime = ManagementFactory.getRuntimeMXBean().getStartTime();
        // 转成标准时间格式
        String updateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new java.util.Date(startTime));

        data.put("系统最新更新时间", updateTime);
        return CommonResult.success(data);
    }

}
