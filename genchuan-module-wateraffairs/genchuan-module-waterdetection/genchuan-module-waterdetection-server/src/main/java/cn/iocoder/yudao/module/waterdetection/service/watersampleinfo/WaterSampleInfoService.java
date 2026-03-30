package cn.iocoder.yudao.module.waterdetection.service.watersampleinfo;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.watersampleinfo.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersampleinfo.WaterSampleInfoDO;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersampleinfo.WaterSampleResultDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 水质检测信息 Service 接口
 *
 * @author 朱聪权
 */
public interface WaterSampleInfoService {

    /**
     * 创建水质检测信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWaterSampleInfo(@Valid WaterSampleInfoSaveReqVO createReqVO);

    /**
     * 更新水质检测信息
     *
     * @param updateReqVO 更新信息
     */
    void updateWaterSampleInfo(@Valid WaterSampleInfoSaveReqVO updateReqVO);

    /**
     * 删除水质检测信息
     *
     * @param id 编号
     */
    void deleteWaterSampleInfo(Long id);

    /**
     * 获得水质检测信息
     *
     * @param id 编号
     * @return 水质检测信息
     */
    WaterSampleInfoDO getWaterSampleInfo(Long id);

    /**
     * 获得水质检测信息分页
     *
     * @param pageReqVO 分页查询
     * @return 水质检测信息分页
     */
    PageResult<WaterSampleInfoDO> getWaterSampleInfoPage(WaterSampleInfoPageReqVO pageReqVO);

    // ==================== 子表（出厂水检测结果） ====================

    /**
     * 获得出厂水检测结果分页
     *
     * @param pageReqVO 分页查询
     * @param waterSampleId 样品编号
     * @return 出厂水检测结果分页
     */
    PageResult<WaterSampleResultDO> getWaterSampleResultPage(PageParam pageReqVO, Long waterSampleId);

    /**
     * 创建出厂水检测结果
     *
     * @param waterSampleResult 创建信息
     * @return 编号
     */
    Long createWaterSampleResult(@Valid WaterSampleResultDO waterSampleResult);

    /**
     * 更新出厂水检测结果
     *
     * @param waterSampleResult 更新信息
     */
    void updateWaterSampleResult(@Valid WaterSampleResultDO waterSampleResult);

    /**
     * 删除出厂水检测结果
     *
     * @param id 编号
     */
    void deleteWaterSampleResult(Long id);

	/**
	 * 获得出厂水检测结果
	 *
	 * @param id 编号
     * @return 出厂水检测结果
	 */
    WaterSampleResultDO getWaterSampleResult(Long id);

}