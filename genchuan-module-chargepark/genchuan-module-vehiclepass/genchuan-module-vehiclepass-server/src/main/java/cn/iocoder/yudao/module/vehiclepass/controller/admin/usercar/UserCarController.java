package cn.iocoder.yudao.module.vehiclepass.controller.admin.usercar;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.usercar.vo.*;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.usercar.UserCarDO;
import cn.iocoder.yudao.module.vehiclepass.service.usercar.UserCarService;

@Tag(name = "管理后台 - 用户车辆")
@RestController
@RequestMapping("/vehiclepass/user-car")
@Validated
public class UserCarController {

    @Resource
    private UserCarService userCarService;

    @GetMapping("/get-by-plate")
    @Operation(summary = "通过车牌号获得用户车辆")
    @Parameter(name = "plateNo", description = "车牌号码", required = true, example = "闽C12345")
    public CommonResult<UserCarRespVO> getUserCarByPlateNo(@RequestParam("plateNo") String plateNo) {
        UserCarDO userCar = userCarService.getUserCarByPlateNo(plateNo);
        return success(BeanUtils.toBean(userCar, UserCarRespVO.class));
    }

}
