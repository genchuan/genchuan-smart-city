package cn.iocoder.yudao.module.vehiclepass.service.inspectmgmt.resulthandle;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo.ResultHandlePageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo.ResultHandleRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo.ResultHandleSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo.ResultHandleBatchHandleReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inspectmgmt.resulthandle.ResultHandleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 结果处置 Service 接口
 *
 * @author 亘川智城
 */
public interface ResultHandleService {

    /**
     * 创建结果处置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHandle(@Valid ResultHandleSaveReqVO createReqVO);

    /**
     * 更新结果处置
     *
     * @param updateReqVO 更新信息
     */
    void updateHandle(@Valid ResultHandleSaveReqVO updateReqVO);

    /**
     * 删除结果处置
     *
     * @param id 编号
     */
    void deleteHandle(Long id);

    /**
     * 批量删除结果处置
     *
     * @param ids 编号
     */
    void deleteHandleListByIds(List<Long> ids);

    /**
     * 获得结果处置
     *
     * @param id 编号
     * @return 结果处置
     */
    ResultHandleDO getHandle(Long id);

    /**
     * 获得结果处置分页
     *
     * @param pageReqVO 分页查询
     * @return 结果处置分页
     */
    PageResult<ResultHandleDO> getHandlePage(ResultHandlePageReqVO pageReqVO);

    /**
     * 获得结果处置分页（使用JOIN查询）
     *
     * @param pageReqVO 分页查询
     * @return 结果处置分页（含关联表字段）
     */
    PageResult<ResultHandleRespVO> getHandlePageWithJoin(ResultHandlePageReqVO pageReqVO);

    /**
     * 批量处置结果
     *
     * @param reqVO 批量处置请求
     */
    void batchHandle(ResultHandleBatchHandleReqVO reqVO);

}