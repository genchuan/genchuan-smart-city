package cn.iocoder.yudao.module.industry.service.park.user.parkvisitor;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkvisitor.vo.ParkVisitorPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkvisitor.vo.ParkVisitorSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkvisitor.ParkVisitorDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 访客 Service 接口
 *
 * @author lxs
 */
public interface ParkVisitorService {

    /**
     * 创建访客
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkVisitor(@Valid ParkVisitorSaveReqVO createReqVO);

    /**
     * 更新访客
     *
     * @param updateReqVO 更新信息
     */
    void updateParkVisitor(@Valid ParkVisitorSaveReqVO updateReqVO);

    /**
     * 删除访客
     *
     * @param id 编号
     */
    void deleteParkVisitor(Long id);

    /**
     * 获得访客
     *
     * @param id 编号
     * @return 访客
     */
    ParkVisitorDO getParkVisitor(Long id);

    /**
     * 获得访客分页
     *
     * @param pageReqVO 分页查询
     * @return 访客分页
     */
    PageResult<ParkVisitorDO> getParkVisitorPage(ParkVisitorPageReqVO pageReqVO);

}
