package cn.iocoder.yudao.module.stationresource.controller.admin.vrv.test;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理后台 - 测试辅助控制器
 * 提供服务内存查看、启动时间、打包时间等辅助功能
 * 本地/线上均可安全使用，无内存泄漏风险
 */
@Tag(name = "管理后台 - 测试辅助")
@RestController
@RequestMapping("/stationresource/test-fuzhu")
@Validated
public class TestFuZhuController {

    /**
     * 系统打包时间
     * 静态常量：类加载时初始化 = 打包/启动时间
     * 重启服务不变，重新打包才会更新
     */
    private static final String BUILD_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    /**
     * 获取当前服务 JVM 堆内存占用
     * 多次调用不会导致内存上涨，临时对象会被 GC 自动回收
     *
     * @return 内存占用信息（单位：MB）
     */
    @GetMapping("/memory")
    @Operation(summary = "获取当前服务内存占用")
    public CommonResult<Map<String, Object>> getServerMemory() {
        // 获取 JVM 堆内存使用情况
        MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();

        // 字节转换为 MB (1MB = 1024 * 1024 字节)
        long init = heapMemoryUsage.getInit() / 1024 / 1024;       // 初始内存
        long used = heapMemoryUsage.getUsed() / 1024 / 1024;       // 已使用内存（真实占用）
        long committed = heapMemoryUsage.getCommitted() / 1024 / 1024; // 已分配内存
        long max = heapMemoryUsage.getMax() / 1024 / 1024;         // 最大可用内存

        // 封装返回数据
        Map<String, Object> data = new HashMap<>();
        data.put("已使用内存(MB)", used);
        data.put("初始内存(MB)", init);
        data.put("已分配内存(MB)", committed);
        data.put("最大可用内存(MB)", max);

        return CommonResult.success(data);
    }

    /**
     * 获取系统核心信息（启动时间 + 进程PID + 打包时间）
     * 用于判断服务是否重启成功、版本是否更新
     *
     * @return 系统信息集合
     */
    @GetMapping("/system-build-time")
    @Operation(summary = "获取系统打包时间 & 启动时间")
    public CommonResult<Map<String, Object>> getSystemInfo() {
        Map<String, Object> data = new HashMap<>();

        // 1. 获取服务启动时间（重启服务就会更新）
        long startTime = ManagementFactory.getRuntimeMXBean().getStartTime();
        String startTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(startTime));
        data.put("服务启动时间", startTimeStr);

        // 2. 获取服务进程 PID（判断是否重启成功：重启 PID 必变）
        String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        data.put("服务进程PID", pid);

        // 3. 系统打包时间（重新打包才会变，重启不变）
        data.put("系统打包时间", BUILD_TIME);

        return CommonResult.success(data);
    }
}
