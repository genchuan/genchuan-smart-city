//package cn.iocoder.yudao.module.industry.client.datacenter;
//
//import cn.iocoder.yudao.module.datacenter.controller.admin.appscenecategory.vo.AppSceneCategoryTreeRespVO;
//import cn.iocoder.yudao.framework.common.pojo.CommonResult;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//@FeignClient(name = "datacenter-server", path = "/admin-api/datacenter/app-scene-category")
//public interface AppSceneCategoryFeignClient {
//
//    @GetMapping("/tree")
//    CommonResult<List<AppSceneCategoryTreeRespVO>> getAppSceneCategoryTree();
//}
