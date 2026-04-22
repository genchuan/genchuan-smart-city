package cn.iocoder.yudao.module.vehiclepass.service.inparkmgmt.oilcarhandle;

import java.util.*;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandleBatchHandleReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandlePageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandleRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandleSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inparkmgmt.oilcarhandle.OilCarHandleDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 油车占位处置 Service 接口
 *
 * @author 亘川智城
 */
public interface OilCarHandleService {

    /**
     * 创建油车占位处置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCarHandle(@Valid OilCarHandleSaveReqVO createReqVO);

    /**
     * 更新油车占位处置
     *
     * @param updateReqVO 更新信息
     */
    void updateCarHandle(@Valid OilCarHandleSaveReqVO updateReqVO);

    /**
     * 删除油车占位处置
     *
     * @param id 编号
     */
    void deleteCarHandle(Long id);

    /**
     * 批量删除油车占位处置
     *
     * @param ids 编号
     */
    void deleteCarHandleListByIds(List<Long> ids);

    /**
     * 获得油车占位处置
     *
     * @param id 编号
     * @return 油车占位处置
     */
    OilCarHandleDO getCarHandle(Long id);

    /**
     * 获得油车占位处置分页
     *
     * @param pageReqVO 分页查询
     * @return 油车占位处置分页
     */
    PageResult<OilCarHandleDO> getCarHandlePage(OilCarHandlePageReqVO pageReqVO);

    /**
     * 获得油车占位处置分页（含关联名称）
     *
     * @param pageReqVO 分页查询
     * @return 油车占位处置分页
     */
    PageResult<OilCarHandleRespVO> getCarHandlePageWithJoin(OilCarHandlePageReqVO pageReqVO);

    /**
     * 批量处置油车占位
     *
     * @param reqVO 批量处置请求
     */
    void batchHandle(OilCarHandleBatchHandleReqVO reqVO);

}