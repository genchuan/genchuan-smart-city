package cn.iocoder.yudao.module.evaluate.service.archiverecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.archiverecord.vo.ArchiveRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.archiverecord.vo.ArchiveRecordSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.archiverecord.ArchiveRecordDO;
import jakarta.validation.Valid;

/**
 * 评价结果存档 Service 接口
 *
 * @author 亘川智城
 */
public interface ArchiveRecordService {

    /**
     * 创建评价结果存档
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createArchiveRecord(@Valid ArchiveRecordSaveReqVO createReqVO);

    /**
     * 更新评价结果存档
     *
     * @param updateReqVO 更新信息
     */
    void updateArchiveRecord(@Valid ArchiveRecordSaveReqVO updateReqVO);

    /**
     * 删除评价结果存档
     *
     * @param id 编号
     */
    void deleteArchiveRecord(Long id);

    /**
     * 获得评价结果存档
     *
     * @param id 编号
     * @return 评价结果存档
     */
    ArchiveRecordDO getArchiveRecord(Long id);

    /**
     * 获得评价结果存档分页
     *
     * @param pageReqVO 分页查询
     * @return 评价结果存档分页
     */
    PageResult<ArchiveRecordDO> getArchiveRecordPage(ArchiveRecordPageReqVO pageReqVO);

}