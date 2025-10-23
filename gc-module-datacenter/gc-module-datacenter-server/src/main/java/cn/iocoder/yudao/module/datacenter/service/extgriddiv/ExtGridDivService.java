package cn.iocoder.yudao.module.datacenter.service.extgriddiv;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.extgriddiv.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.extgriddiv.ExtGridDivDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 扩展网格划分 Service 接口
 *
 * @author zcq
 */
public interface ExtGridDivService {

    /**
     * 创建扩展网格划分
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createExtGridDiv(@Valid ExtGridDivSaveReqVO createReqVO);

    /**
     * 更新扩展网格划分
     *
     * @param updateReqVO 更新信息
     */
    void updateExtGridDiv(@Valid ExtGridDivSaveReqVO updateReqVO);

    /**
     * 删除扩展网格划分
     *
     * @param id 编号
     */
    void deleteExtGridDiv(Long id);

    /**
     * 获得扩展网格划分
     *
     * @param id 编号
     * @return 扩展网格划分
     */
    ExtGridDivDO getExtGridDiv(Long id);

    /**
     * 获得扩展网格划分分页
     *
     * @param pageReqVO 分页查询
     * @return 扩展网格划分分页
     */
    PageResult<ExtGridDivDO> getExtGridDivPage(ExtGridDivPageReqVO pageReqVO);

}