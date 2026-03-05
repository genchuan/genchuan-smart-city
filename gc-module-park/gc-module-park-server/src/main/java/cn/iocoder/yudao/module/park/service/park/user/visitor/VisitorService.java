package cn.iocoder.yudao.module.park.service.park.user.visitor;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.user.visitor.vo.VisitorPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.visitor.vo.VisitorSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.visitor.VisitorDO;
import jakarta.validation.Valid;

/**
 * 访客 Service 接口
 *
 * @author 亘川智城
 */
public interface VisitorService {

    /**
     * 创建访客
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createVisitor(@Valid VisitorSaveReqVO createReqVO);

    /**
     * 更新访客
     *
     * @param updateReqVO 更新信息
     */
    void updateVisitor(@Valid VisitorSaveReqVO updateReqVO);

    /**
     * 删除访客
     *
     * @param id 编号
     */
    void deleteVisitor(Long id);

    /**
     * 获得访客
     *
     * @param id 编号
     * @return 访客
     */
    VisitorDO getVisitor(Long id);

    /**
     * 获得访客分页
     *
     * @param pageReqVO 分页查询
     * @return 访客分页
     */
    PageResult<VisitorDO> getVisitorPage(VisitorPageReqVO pageReqVO);

}
