package cn.iocoder.yudao.module.park.controller.admin.park.parklot;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.parklot.vo.ParkLotCreateReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.parklot.vo.ParkLotPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.parklot.vo.ParkLotUpdateReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.parkLot.ParkLotDO;
import cn.iocoder.yudao.module.park.service.park.parklot.ParklotServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 停车场列表")
@RestController
@RequestMapping("/park/park-lot")
@Validated
@Slf4j
public class ParkLotController {
    @Resource
    private ParklotServiceImpl parkLotService;

    /**
     * 分页查询车场信息
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询车场信息")
    public CommonResult<PageResult<ParkLotDO>> getParkLotPage(ParkLotPageReqVO reqVO) {
        PageResult<ParkLotDO> pageResult = parkLotService.getPageData(reqVO);
        return success(pageResult);
    }

    @PostMapping("/create")
    public CommonResult<String> createParkLot(@Validated @RequestBody ParkLotCreateReqVO reqVO) {
        try {
            String lotId = parkLotService.createParkLot(reqVO);
            return CommonResult.success(lotId);
        } catch (Exception e) {
            // 记录日志
            log.error("创建车场失败", e);
            return CommonResult.error(500,"系统异常，请联系管理员");
        }
    }
    /**
     * 更新车场
     * @param
     * @return 响应结果
     */
    @PutMapping("/update")
    public CommonResult<Boolean> updateParkLot(@Validated @RequestBody ParkLotUpdateReqVO parkLotUpdateReqVO) {
        try {
            String success = parkLotService.updateParkLot(parkLotUpdateReqVO);
            return CommonResult.success(true);
        }   catch (Exception e) {
            log.error("更新车场失败", e);
            return CommonResult.error(500,"系统异常，请联系管理员");
        }
    }
    /**
     * 删除车场
     */
    @DeleteMapping("/delete")
    public CommonResult<Boolean> updateParkLot(@Validated @RequestParam Long id) {

        try {
            int success = parkLotService.deleteById(id);
            return  CommonResult.success(true);
        }   catch (Exception e) {
            log.error("删除车场失败", e);
            return CommonResult.error(500,"系统异常，请联系管理员");
        }
    }

}
