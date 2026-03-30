package cn.iocoder.yudao.module.waterdetection.service.waterhydrologyparam;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.waterhydrologyparam.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.waterhydrologyparam.WaterHydrologyParamDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 水源水文参数管理 Service 接口
 *
 * @author zcq
 */
public interface WaterHydrologyParamService {

    /**
     * 创建水源水文参数管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWaterHydrologyParam(@Valid WaterHydrologyParamSaveReqVO createReqVO);

    /**
     * 更新水源水文参数管理
     *
     * @param updateReqVO 更新信息
     */
    void updateWaterHydrologyParam(@Valid WaterHydrologyParamSaveReqVO updateReqVO);

    /**
     * 删除水源水文参数管理
     *
     * @param id 编号
     */
    void deleteWaterHydrologyParam(Long id);

    /**
     * 获得水源水文参数管理
     *
     * @param id 编号
     * @return 水源水文参数管理
     */
    WaterHydrologyParamDO getWaterHydrologyParam(Long id);

    /**
     * 获得水源水文参数管理分页
     *
     * @param pageReqVO 分页查询
     * @return 水源水文参数管理分页
     */
    PageResult<WaterHydrologyParamDO> getWaterHydrologyParamPage(WaterHydrologyParamPageReqVO pageReqVO);

}