package cn.iocoder.yudao.module.ordertrade.rpc.stationresource;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stationresource-server", path = "/admin-api/stationresource/station-info")
public interface StationInfoFeignClient {

    @GetMapping("/get")
    CommonResult<StationInfoDTO> getStationInfo(@RequestParam("id") Long id);

}
