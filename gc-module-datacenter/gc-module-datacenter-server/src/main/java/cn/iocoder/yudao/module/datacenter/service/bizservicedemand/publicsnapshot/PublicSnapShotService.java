package cn.iocoder.yudao.module.datacenter.service.bizservicedemand.publicsnapshot;

import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicsnapshot.vo.PublicSnapShotPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicsnapshot.vo.PublicSnapShotSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.bizservicedemand.publicsnapshot.PublicSnapShotDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 随手拍数据 Service 接口
 *
 * @author 亘川智城
 */
public interface PublicSnapShotService {

    /**
     * 创建随手拍数据
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPublicSnapShot(@Valid PublicSnapShotSaveReqVO createReqVO);

    /**
     * 更新随手拍数据
     *
     * @param updateReqVO 更新信息
     */
    void updatePublicSnapShot(@Valid PublicSnapShotSaveReqVO updateReqVO);

    /**
     * 删除随手拍数据
     *
     * @param id 编号
     */
    void deletePublicSnapShot(Long id);

    /**
     * 获得随手拍数据
     *
     * @param id 编号
     * @return 随手拍数据
     */
    PublicSnapShotDO getPublicSnapShot(Long id);

    /**
     * 获得随手拍数据分页
     *
     * @param pageReqVO 分页查询
     * @return 随手拍数据分页
     */
    PageResult<PublicSnapShotDO> getPublicSnapShotPage(PublicSnapShotPageReqVO pageReqVO);

}