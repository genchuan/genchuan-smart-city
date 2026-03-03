package cn.iocoder.yudao.module.envirhealth.service.garbagecollection.garbageabnormal;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.GarbageAbnormalPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.GarbageAbnormalSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.card.abnormal.GarbageAbnormalCardAbnormalRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.card.review.GarbageAbnormalCardReviewRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.circle.abnormal.GarbageAbnormalCircleAbnormalVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.circle.review.GarbageAbnormalCircleReviewVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.column.abnormal.GarbageAbnormalColumnAbnormalVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.column.review.GarbageAbnormalColumnHandleTimeVO;
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
     * 批量垃圾异常记录
     *
     * @param ids 编号列表
     */
    void deleteGarbageAbnormalBatch(List<Long> ids);

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

    /**
     * 获得垃圾异常统计数据（卡片-异常）
     *
     * @return 垃圾异常统计数据
     */
    GarbageAbnormalCardAbnormalRespVO getGarbageAbnormalCardAbnormal();

    /**
     * 获取异常类型占比（环状图）
     *
     * @return 异常类型占比列表
     */
    List<GarbageAbnormalCircleAbnormalVO> getGarbageAbnormalTypeCircleAbnormal();

    /**
     * 获取区域分布占比（环状图）
     *
     * @return 区域分布占比列表
     */
    List<GarbageAbnormalCircleAbnormalVO> getGarbageAbnormalAreaDistributionCircle();

    /**
     * 获取不同责任人的待处置异常数量对比（柱状图）
     *
     * @return 责任人待处置异常数量列表
     */
    List<GarbageAbnormalColumnAbnormalVO> getHandlerAbnormalColumn();

    /**
     * 获得垃圾异常统计数据（卡片-待复核）
     *
     * @return 垃圾异常统计数据
     */
    GarbageAbnormalCardReviewRespVO getGarbageAbnormalCardReview();

    /**
     * 获取复核结果占比（环状图）
     *
     * @return 复核结果占比列表
     */
    List<GarbageAbnormalCircleReviewVO> getGarbageAbnormalReviewResultCircle();

    /**
     * 获取异常类型占比（环状图）- 只统计待复核的数据
     *
     * @return 异常类型占比列表
     */
    List<GarbageAbnormalCircleAbnormalVO> getGarbageAbnormalTypeCircleForReview();

    /**
     * 获取异常处置平均时长对比（柱状图）
     * @return 各维度平均处置时长列表
     */
    List<GarbageAbnormalColumnHandleTimeVO> getAvgHandleTimeColumn();
}