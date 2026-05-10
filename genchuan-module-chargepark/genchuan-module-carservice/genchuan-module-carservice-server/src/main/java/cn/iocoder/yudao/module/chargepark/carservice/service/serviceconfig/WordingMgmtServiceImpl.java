package cn.iocoder.yudao.module.chargepark.carservice.service.serviceconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo.WordingMgmtBatchSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo.WordingMgmtPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo.WordingMgmtSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.serviceconfig.WordingMgmtDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.serviceconfig.WordingMgmtMapper;
import cn.iocoder.yudao.module.chargepark.carservice.enums.serviceconfig.WordingMgmtStatusEnum;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.module.chargepark.carservice.enums.LogRecordConstants.*;

import java.util.HashSet;
import java.util.Set;

import java.util.List;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.WORDING_MGMT_NAME_DUPLICATE;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.WORDING_MGMT_NOT_EXISTS;

/**
 * 话术管理 Service 实现类
 *
 * @author carservice
 */
@Service
@Validated
public class WordingMgmtServiceImpl implements WordingMgmtService {

    @Resource
    private WordingMgmtMapper wordingMgmtMapper;

    @Override
    public Long createWordingMgmt(WordingMgmtSaveReqVO createReqVO) {
        validateNameUnique(null, createReqVO.getName());
        WordingMgmtDO wordingMgmt = BeanUtils.toBean(createReqVO, WordingMgmtDO.class);
        if (wordingMgmt.getStatus() == null || wordingMgmt.getStatus().isEmpty()) {
            wordingMgmt.setStatus(WordingMgmtStatusEnum.DISABLED.getLabel());
        }
        wordingMgmtMapper.insert(wordingMgmt);
        return wordingMgmt.getId();
    }

    @Override
    public void updateWordingMgmt(WordingMgmtSaveReqVO updateReqVO) {
        validateWordingMgmtExists(updateReqVO.getId());
        validateNameUnique(updateReqVO.getId(), updateReqVO.getName());
        WordingMgmtDO updateObj = BeanUtils.toBean(updateReqVO, WordingMgmtDO.class);
        wordingMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteWordingMgmt(Long id) {
        validateWordingMgmtExists(id);
        wordingMgmtMapper.deleteById(id);
    }

    @Override
    public void deleteWordingMgmtListByIds(List<Long> ids) {
        wordingMgmtMapper.deleteByIds(ids);
    }

    private void validateWordingMgmtExists(Long id) {
        if (wordingMgmtMapper.selectById(id) == null) {
            throw exception(WORDING_MGMT_NOT_EXISTS);
        }
    }

    /**
     * 校验话术名称唯一性。
     * 创建时 id 传 null;更新时传当前 id,允许同名(自身)
     */
    private void validateNameUnique(Long id, String name) {
        if (name == null || name.isEmpty()) {
            return;
        }
        WordingMgmtDO existing = wordingMgmtMapper.selectOne(new LambdaQueryWrapperX<WordingMgmtDO>()
                .eq(WordingMgmtDO::getName, name));
        if (existing == null) {
            return;
        }
        if (id == null || !Objects.equals(existing.getId(), id)) {
            throw exception(WORDING_MGMT_NAME_DUPLICATE);
        }
    }

    @Override
    public WordingMgmtDO getWordingMgmt(Long id) {
        return wordingMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<WordingMgmtDO> getWordingMgmtPage(WordingMgmtPageReqVO pageReqVO) {
        return wordingMgmtMapper.selectPage(pageReqVO);
    }

    @Override
    @LogRecord(type = WORDING_TYPE, subType = WORDING_ENABLE_SUB,
            bizNo = "{{#id}}", success = WORDING_ENABLE_SUCCESS)
    public void enableWordingMgmt(Long id) {
        validateWordingMgmtExists(id);
        WordingMgmtDO update = new WordingMgmtDO();
        update.setId(id);
        update.setStatus(WordingMgmtStatusEnum.ENABLED.getLabel());
        wordingMgmtMapper.updateById(update);
    }

    @Override
    @LogRecord(type = WORDING_TYPE, subType = WORDING_DISABLE_SUB,
            bizNo = "{{#id}}", success = WORDING_DISABLE_SUCCESS)
    public void disableWordingMgmt(Long id) {
        validateWordingMgmtExists(id);
        WordingMgmtDO update = new WordingMgmtDO();
        update.setId(id);
        update.setStatus(WordingMgmtStatusEnum.DISABLED.getLabel());
        wordingMgmtMapper.updateById(update);
    }

    /**
     * 校验话术名称唯一性,返回 true 表示该名称可用。
     *
     * 行为说明:
     * - id 为空(创建场景):有任何同名记录都判定为不可用
     * - id 非空(编辑场景):允许与自身同名
     * - **软删除范围**:此校验仅检查未被逻辑删除的记录(MyBatis-Plus 自动加 deleted=0),
     *   被软删除的话术名称可以被新建复用,符合业务直觉
     * - **多租户**:由 yudao 框架自动隔离,跨租户同名互不影响
     */
    @Override
    public boolean checkNameUnique(String name, Long id) {
        if (name == null || name.isEmpty()) {
            return true;
        }
        WordingMgmtDO existing = wordingMgmtMapper.selectOne(new LambdaQueryWrapperX<WordingMgmtDO>()
                .eq(WordingMgmtDO::getName, name));
        if (existing == null) {
            return true;
        }
        return id != null && Objects.equals(existing.getId(), id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = WORDING_TYPE, subType = WORDING_SAVE_SUB,
            bizNo = "0", success = WORDING_SAVE_SUCCESS)
    public void batchSaveWordingMgmt(WordingMgmtBatchSaveReqVO reqVO) {
        List<WordingMgmtBatchSaveReqVO.Item> items = reqVO.getItems();
        LogRecordContext.putVariable("count", items == null ? 0 : items.size());
        // Pass 1: 全量预校验(字段 + 存在性 + 名称唯一性,含"同批次内重名"防护)
        Set<String> nameInBatch = new HashSet<>();
        for (WordingMgmtBatchSaveReqVO.Item it : items) {
            if (!nameInBatch.add(it.getName())) {
                // 同一批次内就有重名,直接抛
                throw exception(WORDING_MGMT_NAME_DUPLICATE);
            }
            if (it.getId() != null) {
                validateWordingMgmtExists(it.getId());
            }
            validateNameUnique(it.getId(), it.getName());
        }
        // Pass 2: 校验全部通过后统一写入;事务回滚兜底
        for (WordingMgmtBatchSaveReqVO.Item it : items) {
            WordingMgmtSaveReqVO save = new WordingMgmtSaveReqVO();
            save.setId(it.getId());
            save.setName(it.getName());
            save.setContent(it.getContent());
            save.setType(it.getType());
            if (it.getId() == null) {
                createWordingMgmt(save);
            } else {
                updateWordingMgmt(save);
            }
        }
    }

}
