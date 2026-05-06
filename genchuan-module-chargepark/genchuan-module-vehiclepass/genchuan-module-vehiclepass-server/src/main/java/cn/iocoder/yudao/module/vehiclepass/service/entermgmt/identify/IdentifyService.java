package cn.iocoder.yudao.module.vehiclepass.service.entermgmt.identify;

import java.util.*;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.identify.vo.*;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.entermgmt.identify.IdentifyDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 车牌识别 Service 接口
 *
 * @author 亘川智城
 */
public interface IdentifyService {

    /**
     * 创建车牌识别
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createIdentify(@Valid IdentifySaveReqVO createReqVO);

    /**
     * 更新车牌识别
     *
     * @param updateReqVO 更新信息
     */
    void updateIdentify(@Valid IdentifySaveReqVO updateReqVO);

    /**
     * 删除车牌识别
     *
     * @param id 编号
     */
    void deleteIdentify(Long id);

    /**
     * 批量删除车牌识别
     *
     * @param ids 编号
     */
    void deleteIdentifyListByIds(List<Long> ids);

    /**
     * 获得车牌识别
     *
     * @param id 编号
     * @return 车牌识别
     */
    IdentifyDO getIdentify(Long id);

    /**
     * 获得车牌识别分页
     *
     * @param pageReqVO 分页查询
     * @return 车牌识别分页
     */
    PageResult<IdentifyRespVO> getIdentifyPage(IdentifyPageReqVO pageReqVO);
    /**
     * 手动录入车牌识别
     *
     * @param reqVO 创建信息
     * @return 是否成功
     */
    Boolean createIdentify(IdentifyCreateReqVO reqVO);
    /**
     * 修改车牌识别
     *
     * @param reqVO 修改信息
     * @return 是否成功
     */
    Boolean correctIdentify(IdentifyCorrectReqVO reqVO);

    /**
     * 获取车牌识别统计图表数据
     */
    PlateIdentifyChartRespVO getIdentifyChartData(PlateIdentifyChartReqVO reqVO);

    /**
     * 确认车牌识别记录
     *
     * @param reqVO 确认信息
     * @return 是否成功
     */
    Boolean confirmIdentify(PlateIdentifyConfirmReqVO reqVO);

}