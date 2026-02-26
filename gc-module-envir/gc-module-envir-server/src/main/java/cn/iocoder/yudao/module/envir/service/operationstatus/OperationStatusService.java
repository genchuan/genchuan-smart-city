package cn.iocoder.yudao.module.envir.service.operationstatus;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envir.controller.admin.operationstatus.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.operationstatus.OperationStatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 运营状态字典 Service 接口
 *
 * @author 芋道源码
 */
public interface OperationStatusService {

    /**
     * 创建运营状态字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOperationStatus(@Valid OperationStatusSaveReqVO createReqVO);

    /**
     * 更新运营状态字典
     *
     * @param updateReqVO 更新信息
     */
    void updateOperationStatus(@Valid OperationStatusSaveReqVO updateReqVO);

    /**
     * 删除运营状态字典
     *
     * @param id 编号
     */
    void deleteOperationStatus(Long id);

    /**
     * 获得运营状态字典
     *
     * @param id 编号
     * @return 运营状态字典
     */
    OperationStatusDO getOperationStatus(Long id);

    /**
     * 获得运营状态字典分页
     *
     * @param pageReqVO 分页查询
     * @return 运营状态字典分页
     */
    PageResult<OperationStatusDO> getOperationStatusPage(OperationStatusPageReqVO pageReqVO);

}