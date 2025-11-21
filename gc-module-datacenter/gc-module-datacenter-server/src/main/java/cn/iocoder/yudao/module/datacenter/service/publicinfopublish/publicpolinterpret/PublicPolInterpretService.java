package cn.iocoder.yudao.module.datacenter.service.publicinfopublish.publicpolinterpret;

import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicpolinterpret.vo.PublicPolInterpretPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicpolinterpret.vo.PublicPolInterpretSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.publicinfopublish.publicpolinterpret.PublicPolInterpretDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 政策解读发布 Service 接口
 *
 * @author 亘川智城
 */
public interface PublicPolInterpretService {

    /**
     * 创建政策解读发布
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPublicPolInterpret(@Valid PublicPolInterpretSaveReqVO createReqVO);

    /**
     * 更新政策解读发布
     *
     * @param updateReqVO 更新信息
     */
    void updatePublicPolInterpret(@Valid PublicPolInterpretSaveReqVO updateReqVO);

    /**
     * 删除政策解读发布
     *
     * @param id 编号
     */
    void deletePublicPolInterpret(Long id);

    /**
     * 获得政策解读发布
     *
     * @param id 编号
     * @return 政策解读发布
     */
    PublicPolInterpretDO getPublicPolInterpret(Long id);

    /**
     * 获得政策解读发布分页
     *
     * @param pageReqVO 分页查询
     * @return 政策解读发布分页
     */
    PageResult<PublicPolInterpretDO> getPublicPolInterpretPage(PublicPolInterpretPageReqVO pageReqVO);

}