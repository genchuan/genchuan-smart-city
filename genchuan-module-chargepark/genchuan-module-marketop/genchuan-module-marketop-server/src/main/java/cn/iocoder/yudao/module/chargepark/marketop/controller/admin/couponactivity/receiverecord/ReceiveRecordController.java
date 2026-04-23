package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.receiverecord;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.receiverecord.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.ReceiveRecordDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.receiverecord.ReceiveRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import cn.hutool.core.util.StrUtil;

import java.io.IOException;
import java.util.*;

@Tag(name = "管理后台 - 领用记录")
@RestController
@RequestMapping("/marketop/receive-record")
public class ReceiveRecordController {

    @Resource
    private ReceiveRecordService receiveRecordService;

    @Resource
    private AdminUserApi adminUserApi;

    @GetMapping("/page")
    @Operation(summary = "获得领用记录分页")
    @PreAuthorize("@ss.hasPermission('marketop:receive-record:query')")
    public CommonResult<PageResult<ReceiveRecordRespVO>> getPage(ReceiveRecordPageReqVO reqVO) {
        PageResult<ReceiveRecordDO> pageResult = receiveRecordService.getPage(reqVO);
        PageResult<ReceiveRecordRespVO> bean = BeanUtils.toBean(pageResult, ReceiveRecordRespVO.class);
        injectUserNames(bean.getList());
        return CommonResult.success(bean);
    }

    @GetMapping("/get")
    @Operation(summary = "获得领用记录详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:receive-record:query')")
    public CommonResult<ReceiveRecordRespVO> get(@RequestParam("id") Long id) {
        ReceiveRecordDO receiveRecord = receiveRecordService.get(id);
        ReceiveRecordRespVO respVO = BeanUtils.toBean(receiveRecord, ReceiveRecordRespVO.class);
        if (respVO != null) injectUserNames(Collections.singletonList(respVO));
        return CommonResult.success(respVO);
    }

    @PutMapping("/check")
    @Operation(summary = "核查领用记录")
    @PreAuthorize("@ss.hasPermission('marketop:receive-record:query')")
    public CommonResult<Boolean> check(@Valid @RequestBody ReceiveRecordCheckReqVO reqVO) {
        receiveRecordService.check(reqVO.getId(), reqVO.getCheckResult());
        return CommonResult.success(true);
    }

    @GetMapping("/export")
    @Operation(summary = "导出领用记录")
    @PreAuthorize("@ss.hasPermission('marketop:receive-record:query')")
    public void export(ReceiveRecordPageReqVO reqVO, HttpServletResponse response) throws IOException {
        reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<ReceiveRecordDO> pageResult = receiveRecordService.getPage(reqVO);
        List<ReceiveRecordRespVO> list = BeanUtils.toBean(pageResult.getList(), ReceiveRecordRespVO.class);
        ExcelUtils.write(response, "领用记录.xlsx", "数据", ReceiveRecordRespVO.class, list);
    }

    @GetMapping("/chart")
    @Operation(summary = "领用记录图表统计")
    @PreAuthorize("@ss.hasPermission('marketop:receive-record:query')")
    public CommonResult<ReceiveRecordChartRespVO> getChart(@RequestParam(value = "startTime", required = false) Long startTime,
                                                           @RequestParam(value = "endTime", required = false) Long endTime,
                                                           @RequestParam(value = "stationId", required = false) Long stationId) {
        return CommonResult.success(receiveRecordService.getChart(startTime, endTime, stationId));
    }

    private void injectUserNames(List<ReceiveRecordRespVO> list) {
        Set<Long> userIds = new HashSet<>();
        for (var item : list) {
            if (StrUtil.isNotBlank(item.getCreator())) {
                userIds.add(Long.valueOf(item.getCreator()));
            }
        }
        if (userIds.isEmpty()) return;
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIds);
        for (var item : list) {
            if (StrUtil.isNotBlank(item.getCreator())) {
                AdminUserRespDTO user = userMap.get(Long.valueOf(item.getCreator()));
                if (user != null) item.setCreatorName(user.getNickname());
            }
        }
    }

}
