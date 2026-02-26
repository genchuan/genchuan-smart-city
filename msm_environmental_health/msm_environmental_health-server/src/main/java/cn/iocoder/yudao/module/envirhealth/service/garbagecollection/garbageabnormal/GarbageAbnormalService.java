package cn.iocoder.yudao.module.envirhealth.service.garbagecollection.garbageabnormal;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.GarbageAbnormalPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.GarbageAbnormalSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.GarbageCollectionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageAbnormalDetailDO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageAbnormalDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import java.util.List;

/**
 * 垃圾异常记录 Service 接口
 *
 * @author 芋道源码
 */
public interface GarbageAbnormalService {

    /**
     * 创建垃圾异常记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGarbageAbnormal(@Valid GarbageAbnormalSaveReqVO createReqVO);

    /**
     * 更新垃圾异常记录
     *
     * @param updateReqVO 更新信息
     */
    void updateGarbageAbnormal(@Valid GarbageAbnormalSaveReqVO updateReqVO);

    /**
     * 删除垃圾异常记录
     *
     * @param id 编号
     */
    void deleteGarbageAbnormal(Long id);

    /**
     * 获得垃圾异常记录
     *
     * @param id 编号
     * @return 垃圾异常记录
     */
    GarbageAbnormalDO getGarbageAbnormal(Long id);

    /**
     * 获得垃圾异常记录分页
     *
     * @param pageReqVO 分页查询
     * @return 垃圾异常记录分页
     */
    PageResult<GarbageAbnormalDO> getGarbageAbnormalPage(GarbageAbnormalPageReqVO pageReqVO);

    /**
     * 获得垃圾异常详情分页
     *
//     * @param pageReqVO 分页查询
     * @return 垃圾异常记录详情分页
     */
    PageResult<GarbageAbnormalDetailDO> getGarbageAbnormalDetailPage(GarbageAbnormalPageReqVO pageReqVO);
}