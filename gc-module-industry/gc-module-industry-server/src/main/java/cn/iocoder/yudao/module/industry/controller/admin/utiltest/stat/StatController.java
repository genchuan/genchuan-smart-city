package cn.iocoder.yudao.module.industry.controller.admin.utiltest.stat;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkorderescape.ParkOrderEscapeDO;
import cn.iocoder.yudao.module.industry.framework.util.lxs.importer.ImportUtils;
import cn.iocoder.yudao.module.industry.framework.util.lxs.stat.StatUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Tag(name = "（勿用）工具 - 统计")
@RestController
@RequestMapping("/industry/util-stat")
@Validated
@Slf4j
public class StatController {
    @PostMapping("/test")
    @Operation(summary = "测试统计工具")
    public CommonResult<Object> testStat(@RequestParam(value = "count", defaultValue = "2") int count) {
        // 构造测试数据
        List<ParkOrderEscapeDO> list = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            ParkOrderEscapeDO item = new ParkOrderEscapeDO();
            item.setId((long) i);
            item.setEscapeAmount(BigDecimal.valueOf(100.5 * i));
            item.setTraceTimes(i); // 假设还有 traceTimes 字段
            list.add(item);
        }

        // 调用统计工具
        Map<String, Object> result = StatUtils.statList(ParkOrderEscapeDO.class, list);

        return CommonResult.success(result);
    }

}
