package cn.iocoder.yudao.module.kitchen.service.entrectifyrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.EntRectifyRecordPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.EntRectifyRecordSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.entrectifyrecord.EntRectifyRecordDO;
import jakarta.validation.Valid;

/**
 * 企业整改记录 Service 接口
 *
 * @author 亘川智城
 */
public interface EntRectifyRecordService {

    /**
     * 创建企业整改记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEntRectifyRecord(@Valid EntRectifyRecordSaveReqVO createReqVO);

    /**
     * 更新企业整改记录
     *
     * @param updateReqVO 更新信息
     */
    void updateEntRectifyRecord(@Valid EntRectifyRecordSaveReqVO updateReqVO);

    /**
     * 删除企业整改记录
     *
     * @param id 编号
     */
    void deleteEntRectifyRecord(Long id);

    /**
     * 获得企业整改记录
     *
     * @param id 编号
     * @return 企业整改记录
     */
    EntRectifyRecordDO getEntRectifyRecord(Long id);

    /**
     * 获得企业整改记录分页
     *
     * @param pageReqVO 分页查询
     * @return 企业整改记录分页
     */
    PageResult<EntRectifyRecordDO> getEntRectifyRecordPage(EntRectifyRecordPageReqVO pageReqVO);

}
