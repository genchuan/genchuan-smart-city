package cn.iocoder.yudao.module.vehiclepass.service.siteinput.endpark;

import java.util.*;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo.EndParkPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo.EndParkRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo.EndParkSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.siteinput.endpark.EndParkDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 结束停车 Service 接口
 *
 * @author 亘川智城
 */
public interface EndParkService {

    /**
     * 创建结束停车
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPark(@Valid EndParkSaveReqVO createReqVO);

    /**
     * 更新结束停车
     *
     * @param updateReqVO 更新信息
     */
    void updatePark(@Valid EndParkSaveReqVO updateReqVO);

    /**
     * 删除结束停车
     *
     * @param id 编号
     */
    void deletePark(Long id);

    /**
     * 批量删除结束停车
     *
     * @param ids 编号
     */
    void deleteParkListByIds(List<Long> ids);

    /**
     * 获得结束停车
     *
     * @param id 编号
     * @return 结束停车
     */
    EndParkDO getPark(Long id);

    /**
     * 获得结束停车分页
     *
     * @param pageReqVO 分页查询
     * @return 结束停车分页
     */
    PageResult<EndParkDO> getParkPage(EndParkPageReqVO pageReqVO);

    /**
     * 获得结束停车分页（使用JOIN查询）
     *
     * @param pageReqVO 分页查询
     * @return 结束停车分页（含关联表字段）
     */
    PageResult<EndParkRespVO> getParkPageWithJoin(EndParkPageReqVO pageReqVO);

}