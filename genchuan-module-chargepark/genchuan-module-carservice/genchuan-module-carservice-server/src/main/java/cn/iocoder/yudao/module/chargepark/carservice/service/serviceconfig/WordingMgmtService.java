package cn.iocoder.yudao.module.chargepark.carservice.service.serviceconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo.WordingMgmtBatchSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo.WordingMgmtPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo.WordingMgmtSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.serviceconfig.WordingMgmtDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 话术管理 Service 接口
 *
 * @author carservice
 */
public interface WordingMgmtService {

    Long createWordingMgmt(@Valid WordingMgmtSaveReqVO createReqVO);

    void updateWordingMgmt(@Valid WordingMgmtSaveReqVO updateReqVO);

    void deleteWordingMgmt(Long id);

    void deleteWordingMgmtListByIds(List<Long> ids);

    WordingMgmtDO getWordingMgmt(Long id);

    PageResult<WordingMgmtDO> getWordingMgmtPage(WordingMgmtPageReqVO pageReqVO);

    /** 启用：未生效 → 已生效 */
    void enableWordingMgmt(Long id);

    /** 禁用：已生效 → 未生效 */
    void disableWordingMgmt(Long id);

    /** 校验话术名称唯一性,id 非空时排除自身 */
    boolean checkNameUnique(String name, Long id);

    /** 批量保存:items 里无 id 走 insert,有 id 走 update;整体事务,两遍扫描(先全部校验再全部写入) */
    void batchSaveWordingMgmt(@Valid WordingMgmtBatchSaveReqVO reqVO);

}
