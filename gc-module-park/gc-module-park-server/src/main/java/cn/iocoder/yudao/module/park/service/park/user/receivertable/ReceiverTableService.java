package cn.iocoder.yudao.module.park.service.park.user.receivertable;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.user.receivertable.vo.ReceiverTablePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.receivertable.vo.ReceiverTableSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.receivertable.ReceiverTableDO;
import jakarta.validation.Valid;

/**
 * 接收方 Service 接口
 *
 * @author 亘川智城
 */
public interface ReceiverTableService {

    /**
     * 创建接收方
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createReceiverTable(@Valid ReceiverTableSaveReqVO createReqVO);

    /**
     * 更新接收方
     *
     * @param updateReqVO 更新信息
     */
    void updateReceiverTable(@Valid ReceiverTableSaveReqVO updateReqVO);

    /**
     * 删除接收方
     *
     * @param id 编号
     */
    void deleteReceiverTable(Long id);

    /**
     * 获得接收方
     *
     * @param id 编号
     * @return 接收方
     */
    ReceiverTableDO getReceiverTable(Long id);

    /**
     * 获得接收方分页
     *
     * @param pageReqVO 分页查询
     * @return 接收方分页
     */
    PageResult<ReceiverTableDO> getReceiverTablePage(ReceiverTablePageReqVO pageReqVO);

}
