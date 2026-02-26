package cn.iocoder.yudao.module.envir.service.river;

import java.util.*;

import cn.iocoder.yudao.module.envir.dal.dataobject.publicinstitution.PublicInstitutionDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.river.RiverDetailDO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envir.controller.admin.river.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.river.RiverDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 河道 Service 接口
 *
 * @author 芋道源码
 */
public interface RiverService {

    /**
     * 创建河道
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRiver(@Valid RiverSaveReqVO createReqVO);

    /**
     * 更新河道
     *
     * @param updateReqVO 更新信息
     */
    void updateRiver(@Valid RiverSaveReqVO updateReqVO);

    /**
     * 删除河道
     *
     * @param id 编号
     */
    void deleteRiver(Long id);

    /**
     * 获得河道
     *
     * @param id 编号
     * @return 河道
     */
    RiverDO getRiver(Long id);

    /**
     * 获得河道分页
     *
     * @param pageReqVO 分页查询
     * @return 河道分页
     */
    PageResult<RiverDO> getRiverPage(RiverPageReqVO pageReqVO);

    /**
     * 获得河道列表(详情)
     */
    List<RiverDetailDO> getRiverListDetail();
}