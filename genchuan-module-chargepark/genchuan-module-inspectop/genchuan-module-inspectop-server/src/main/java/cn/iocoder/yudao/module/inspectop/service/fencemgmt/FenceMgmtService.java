package cn.iocoder.yudao.module.inspectop.service.fencemgmt;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.fencemgmt.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.fencemgmt.FenceMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 电子围栏 Service 接口
 *
 * @author zhucongquan
 */
public interface FenceMgmtService {

    /**
     * 创建电子围栏
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFenceMgmt(@Valid FenceMgmtSaveReqVO createReqVO);

    /**
     * 更新电子围栏
     *
     * @param updateReqVO 更新信息
     */
    void updateFenceMgmt(@Valid FenceMgmtSaveReqVO updateReqVO);

    /**
     * 删除电子围栏
     *
     * @param id 编号
     */
    void deleteFenceMgmt(Long id);

    /**
    * 批量删除电子围栏
    *
    * @param ids 编号
    */
    void deleteFenceMgmtListByIds(List<Long> ids);

    /**
     * 获得电子围栏
     *
     * @param id 编号
     * @return 电子围栏
     */
    FenceMgmtDO getFenceMgmt(Long id);

    /**
     * 获得电子围栏分页
     *
     * @param pageReqVO 分页查询
     * @return 电子围栏分页
     */
    PageResult<FenceMgmtDO> getFenceMgmtPage(FenceMgmtPageReqVO pageReqVO);

}