package cn.iocoder.yudao.module.studentmgmt.service.targetmgmt;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.targetmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.targetmgmt.TargetMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 指标管理 Service 接口
 *
 * @author 芋道源码
 */
public interface TargetMgmtService {

    /**
     * 创建指标管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTargetMgmt(@Valid TargetMgmtSaveReqVO createReqVO);

    /**
     * 更新指标管理
     *
     * @param updateReqVO 更新信息
     */
    void updateTargetMgmt(@Valid TargetMgmtSaveReqVO updateReqVO);

    /**
     * 删除指标管理
     *
     * @param id 编号
     */
    void deleteTargetMgmt(Long id);

    /**
    * 批量删除指标管理
    *
    * @param ids 编号
    */
    void deleteTargetMgmtListByIds(List<Long> ids);

    /**
     * 获得指标管理
     *
     * @param id 编号
     * @return 指标管理
     */
    TargetMgmtDO getTargetMgmt(Long id);

    /**
     * 获得指标管理分页
     *
     * @param pageReqVO 分页查询
     * @return 指标管理分页
     */
    PageResult<TargetMgmtDO> getTargetMgmtPage(TargetMgmtPageReqVO pageReqVO);

}