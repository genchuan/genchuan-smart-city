package cn.iocoder.yudao.module.envirhealth.service.publictoilet.publictoilet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.PublicToiletDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.PublicToiletDetailDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.StatisticsRespVO;
import jakarta.validation.Valid;

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
     * 批量删除公厕
     *
     * @param ids 编号列表
     */
    void deletePublicToiletBatch(List<Long> ids);

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


    /**
     * 获取公厕统计数据（按状态分组）
     * @return 统计数据
     */
    StatisticsRespVO getPublicToiletStatistics();

    /**
     * 获取公厕核心指标统计（总数量、正常运营、保洁达标、无投诉）
     *
     * @return 核心指标统计数据
     */
    PublicToiletCardAllVO getPublicToiletCardAll();

    /**
     * 获取公厕环状图统计(全部)
     */
    PublicToiletPieChartsRespVO getPublicToiletPieCharts();

    /**
     * 获取公厕柱状图统计(全部)
     */
    List<BarItemVO> getCleaningQualifiedRateByArea();

    /**
     * 卡片/圆环图/柱状图统计(全部)
     */
    PublicToiletAllRespVO getAll();
}