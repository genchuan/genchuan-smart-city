//package cn.iocoder.yudao.module.industry.client.datacenter.device.deviceinfo;
//
//import cn.iocoder.yudao.framework.common.pojo.CommonResult;
//import cn.iocoder.yudao.framework.common.pojo.PageResult;
//import cn.iocoder.yudao.module.datacenter.controller.admin.device.deviceinfo.vo.DeviceInfoPageReqVO;
//import cn.iocoder.yudao.module.datacenter.controller.admin.device.deviceinfo.vo.DeviceInfoRespVO;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@FeignClient(name = "datacenter-server", path = "/admin-api/datacenter/device-info/page")
//public interface DeviceInfoFeignClient {
//    @GetMapping("/page")
//    CommonResult<PageResult<DeviceInfoRespVO>> getDeviceInfoPage(@RequestBody DeviceInfoPageReqVO pageReqVO);
//}
