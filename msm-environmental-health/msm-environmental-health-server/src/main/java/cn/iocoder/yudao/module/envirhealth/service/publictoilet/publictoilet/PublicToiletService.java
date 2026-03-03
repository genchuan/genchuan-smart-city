package cn.iocoder.yudao.module.envirhealth.service.publictoilet.publictoilet;

import cn.iocoder.yudao.module.envirhealth.controller.admin.area.vo.AreaOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.GarbageCollectionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet.PublicToiletPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet.PublicToiletSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageCollectionDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.PublicToiletDetailDO;
import cn.iocoder.yudao.module.envirhealth.util.options.vo.OptionVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.PublicToiletDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import java.util.List;

/**
 * 公厕 Service 接口
 *
 * @author 芋道源码
 */
public interface PublicToiletService {

    /**
     * 创建公厕
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPublicToilet(@Valid PublicToiletSaveReqVO createReqVO);

    /**
     * 更新公厕
     *
     * @param updateReqVO 更新信息
     */
    void updatePublicToilet(@Valid PublicToiletSaveReqVO updateReqVO);

    /**
     * 删除公厕
     *
     * @param id 编号
     */
    void deletePublicToilet(Long id);

    /**
     * 获得公厕
     *
     * @param id 编号
     * @return 公厕
     */
    PublicToiletDO getPublicToilet(Long id);

    /**
     * 获得公厕分页
     *
     * @param pageReqVO 分页查询
     * @return 公厕分页
     */
    PageResult<PublicToiletDO> getPublicToiletPage(PublicToiletPageReqVO pageReqVO);


    /**
     * 获得公厕分页(详情)
     *
     * @param pageReqVO 分页查询
     * @return 公厕分页
     */
    PageResult<PublicToiletDetailDO> getPublicToiletDetailPage(PublicToiletPageReqVO pageReqVO);

    /**
     * 获得公厕名称下拉框选项
     * @return 下拉框选项列表
     */
    List<OptionVO> getPublicToiletNameOptions();
}