package cn.iocoder.yudao.module.evaluate.service.publicrecord;

import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.publicrecord.vo.PublicRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.publicrecord.vo.PublicRecordSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.publicrecord.PublicRecordDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 评价结果公示 Service 接口
 *
 * @author 亘川智城
 */
public interface PublicRecordService {

    /**
     * 创建评价结果公示
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPublicRecord(@Valid PublicRecordSaveReqVO createReqVO);

    /**
     * 更新评价结果公示
     *
     * @param updateReqVO 更新信息
     */
    void updatePublicRecord(@Valid PublicRecordSaveReqVO updateReqVO);

    /**
     * 删除评价结果公示
     *
     * @param id 编号
     */
    void deletePublicRecord(Long id);

    /**
     * 获得评价结果公示
     *
     * @param id 编号
     * @return 评价结果公示
     */
    PublicRecordDO getPublicRecord(Long id);

    /**
     * 获得评价结果公示分页
     *
     * @param pageReqVO 分页查询
     * @return 评价结果公示分页
     */
    PageResult<PublicRecordDO> getPublicRecordPage(PublicRecordPageReqVO pageReqVO);

}