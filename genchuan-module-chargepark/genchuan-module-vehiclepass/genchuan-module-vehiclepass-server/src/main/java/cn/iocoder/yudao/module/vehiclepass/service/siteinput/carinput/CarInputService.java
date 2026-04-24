package cn.iocoder.yudao.module.vehiclepass.service.siteinput.carinput;

import java.util.*;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo.CarInputPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo.CarInputRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo.CarInputSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.siteinput.carinput.CarInputDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 车辆录入 Service 接口
 *
 * @author 亘川智城
 */
public interface CarInputService {

    /**
     * 创建车辆录入
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInput(@Valid CarInputSaveReqVO createReqVO);

    /**
     * 更新车辆录入
     *
     * @param updateReqVO 更新信息
     */
    void updateInput(@Valid CarInputSaveReqVO updateReqVO);

    /**
     * 删除车辆录入
     *
     * @param id 编号
     */
    void deleteInput(Long id);

    /**
     * 批量删除车辆录入
     *
     * @param ids 编号
     */
    void deleteInputListByIds(List<Long> ids);

    /**
     * 获得车辆录入
     *
     * @param id 编号
     * @return 车辆录入
     */
    CarInputDO getInput(Long id);

    /**
     * 获得车辆录入分页
     *
     * @param pageReqVO 分页查询
     * @return 车辆录入分页
     */
    PageResult<CarInputDO> getInputPage(CarInputPageReqVO pageReqVO);

    /**
     * 获得车辆录入分页（使用JOIN查询）
     *
     * @param pageReqVO 分页查询
     * @return 车辆录入分页（含关联表字段）
     */
    PageResult<CarInputRespVO> getInputPageWithJoin(CarInputPageReqVO pageReqVO);

}