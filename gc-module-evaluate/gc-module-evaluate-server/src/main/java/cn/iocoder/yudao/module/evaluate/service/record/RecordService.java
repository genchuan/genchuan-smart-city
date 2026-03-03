package cn.iocoder.yudao.module.evaluate.service.record;

import cn.iocoder.yudao.module.evaluate.controller.admin.inspection.record.vo.RecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.inspection.record.vo.RecordSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.record.RecordDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 考察记录 Service 接口
 *
 * @author 亘川智城
 */
public interface RecordService {

    /**
     * 创建考察记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRecord(@Valid RecordSaveReqVO createReqVO);

    /**
     * 更新考察记录
     *
     * @param updateReqVO 更新信息
     */
    void updateRecord(@Valid RecordSaveReqVO updateReqVO);

    /**
     * 删除考察记录
     *
     * @param id 编号
     */
    void deleteRecord(Long id);

    /**
     * 获得考察记录
     *
     * @param id 编号
     * @return 考察记录
     */
    RecordDO getRecord(Long id);

    /**
     * 获得考察记录分页
     *
     * @param pageReqVO 分页查询
     * @return 考察记录分页
     */
    PageResult<RecordDO> getRecordPage(RecordPageReqVO pageReqVO);

}