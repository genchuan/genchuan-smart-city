package cn.iocoder.yudao.module.park.service.park.basicAssociation.monitorparttype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.monitorparttype.vo.monitorPartTypePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.monitorparttype.vo.monitorPartTypeSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.monitorparttype.monitorPartTypeDO;
import jakarta.validation.Valid;

/**
 * 监测部件类别 Service 接口
 *
 * @author zhucongquan
 */
public interface monitorPartTypeService {

    /**
     * 创建监测部件类别
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createmonitorPartType(@Valid monitorPartTypeSaveReqVO createReqVO);

    /**
     * 更新监测部件类别
     *
     * @param updateReqVO 更新信息
     */
    void updatemonitorPartType(@Valid monitorPartTypeSaveReqVO updateReqVO);

    /**
     * 删除监测部件类别
     *
     * @param id 编号
     */
    void deletemonitorPartType(Long id);

    /**
     * 获得监测部件类别
     *
     * @param id 编号
     * @return 监测部件类别
     */
    monitorPartTypeDO getmonitorPartType(Long id);

    /**
     * 获得监测部件类别分页
     *
     * @param pageReqVO 分页查询
     * @return 监测部件类别分页
     */
    PageResult<monitorPartTypeDO> getmonitorPartTypePage(monitorPartTypePageReqVO pageReqVO);

}
