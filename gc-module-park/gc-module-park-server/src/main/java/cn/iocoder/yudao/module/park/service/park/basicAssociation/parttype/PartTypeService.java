package cn.iocoder.yudao.module.park.service.park.basicAssociation.parttype;

import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.parttype.vo.PartTypePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.parttype.vo.PartTypeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.parttype.PartTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 管理部件类别 Service 接口
 *
 * @author zhucongquan
 */
public interface PartTypeService {

    /**
     * 创建管理部件类别
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPartType(@Valid PartTypeSaveReqVO createReqVO);

    /**
     * 更新管理部件类别
     *
     * @param updateReqVO 更新信息
     */
    void updatePartType(@Valid PartTypeSaveReqVO updateReqVO);

    /**
     * 删除管理部件类别
     *
     * @param id 编号
     */
    void deletePartType(Long id);

    /**
     * 获得管理部件类别
     *
     * @param id 编号
     * @return 管理部件类别
     */
    PartTypeDO getPartType(Long id);

    /**
     * 获得管理部件类别分页
     *
     * @param pageReqVO 分页查询
     * @return 管理部件类别分页
     */
    PageResult<PartTypeDO> getPartTypePage(PartTypePageReqVO pageReqVO);

}