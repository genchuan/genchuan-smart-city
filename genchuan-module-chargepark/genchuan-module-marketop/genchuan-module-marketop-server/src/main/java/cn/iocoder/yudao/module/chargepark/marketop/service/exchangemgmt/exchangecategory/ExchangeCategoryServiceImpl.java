package cn.iocoder.yudao.module.chargepark.marketop.service.exchangemgmt.exchangecategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo.ExchangeCategoryChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo.ExchangeCategoryCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo.ExchangeCategoryImportExcelVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo.ExchangeCategoryPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo.ExchangeCategoryUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeCategoryDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.exchangemgmt.ExchangeCategoryMapper;
import cn.iocoder.yudao.module.chargepark.marketop.enums.ExchangeCategoryScopeEnum;
import cn.iocoder.yudao.module.chargepark.marketop.enums.ExchangeCategoryStatusEnum;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.LogRecordConstants.*;

@Service
@Validated
public class ExchangeCategoryServiceImpl implements ExchangeCategoryService {

    @Resource
    private ExchangeCategoryMapper exchangeCategoryMapper;

    @Override
    public PageResult<ExchangeCategoryDO> getPage(ExchangeCategoryPageReqVO reqVO) {
        return exchangeCategoryMapper.selectPage(reqVO);
    }

    @Override
    public ExchangeCategoryDO get(Long id) {
        return exchangeCategoryMapper.selectById(id);
    }

    @Override
    @LogRecord(type = EXCHANGE_CATEGORY_TYPE, subType = EXCHANGE_CATEGORY_CREATE_SUB_TYPE, bizNo = "{{#exchangeCategory.id}}",
            success = EXCHANGE_CATEGORY_CREATE_SUCCESS)
    public Long create(ExchangeCategoryCreateReqVO reqVO) {
        // 校验名称唯一
        validateNameUnique(null, reqVO.getName());
        // 插入，默认状态为「未生效」
        ExchangeCategoryDO exchangeCategory = BeanUtils.toBean(reqVO, ExchangeCategoryDO.class);
        exchangeCategory.setStatus(ExchangeCategoryStatusEnum.NOT_EFFECTIVE.getValue());
        exchangeCategoryMapper.insert(exchangeCategory);
        // 记录操作日志上下文
        LogRecordContext.putVariable("exchangeCategory", exchangeCategory);
        return exchangeCategory.getId();
    }

    @Override
    @LogRecord(type = EXCHANGE_CATEGORY_TYPE, subType = EXCHANGE_CATEGORY_UPDATE_SUB_TYPE, bizNo = "{{#reqVO.id}}",
            success = EXCHANGE_CATEGORY_UPDATE_SUCCESS)
    public void update(ExchangeCategoryUpdateReqVO reqVO) {
        ExchangeCategoryDO exchangeCategoryDO = validateExists(reqVO.getId());
        // 校验名称唯一
        validateNameUnique(reqVO.getId(), reqVO.getName());
        ExchangeCategoryDO updateObj = BeanUtils.toBean(reqVO, ExchangeCategoryDO.class);
        exchangeCategoryMapper.updateById(updateObj);
        // 记录操作日志上下文
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, BeanUtils.toBean(exchangeCategoryDO, ExchangeCategoryUpdateReqVO.class));
        LogRecordContext.putVariable("exchangeCategory", updateObj);
    }

    @Override
    @LogRecord(type = EXCHANGE_CATEGORY_TYPE, subType = EXCHANGE_CATEGORY_ENABLE_SUB_TYPE, bizNo = "{{#id}}",
            success = EXCHANGE_CATEGORY_ENABLE_SUCCESS)
    public void enable(Long id, Long userId) {
        ExchangeCategoryDO exchangeCategory = validateExists(id);
        // 未生效→已生效，或 已禁用→已生效
//        if (!ExchangeCategoryStatusEnum.NOT_EFFECTIVE.getValue().equals(exchangeCategory.getStatus()) &&
//                !ExchangeCategoryStatusEnum.DISABLED.getValue().equals(exchangeCategory.getStatus())) {
//            throw exception(EXCHANGE_CATEGORY_NOT_EXISTS);
//        }
        exchangeCategory.setStatus(ExchangeCategoryStatusEnum.EFFECTIVE.getValue());
        exchangeCategory.setAuditorId(userId);
        exchangeCategory.setAuditTime(LocalDateTime.now());
        exchangeCategory.setEffectTime(LocalDateTime.now());
        exchangeCategoryMapper.updateById(exchangeCategory);
        // 记录操作日志上下文
        LogRecordContext.putVariable("exchangeCategoryName", exchangeCategory.getName());
    }

    @Override
    @LogRecord(type = EXCHANGE_CATEGORY_TYPE, subType = EXCHANGE_CATEGORY_DISABLE_SUB_TYPE, bizNo = "{{#id}}",
            success = EXCHANGE_CATEGORY_DISABLE_SUCCESS)
    public void disable(Long id) {
        ExchangeCategoryDO exchangeCategory = validateExists(id);
        // 已生效→已禁用
//        if (!ExchangeCategoryStatusEnum.EFFECTIVE.getValue().equals(exchangeCategory.getStatus())) {
//            throw exception(EXCHANGE_CATEGORY_NOT_EXISTS);
//        }
        exchangeCategory.setStatus(ExchangeCategoryStatusEnum.DISABLED.getValue());
        exchangeCategoryMapper.updateById(exchangeCategory);
        // 记录操作日志上下文
        LogRecordContext.putVariable("exchangeCategoryName", exchangeCategory.getName());
    }

    @Override
    public ExchangeCategoryChartRespVO getChart() {
        List<ExchangeCategoryDO> allList = exchangeCategoryMapper.selectList();

        ExchangeCategoryChartRespVO respVO = new ExchangeCategoryChartRespVO();
        // 类目总数
        respVO.setCategoryCount(allList.size());
        // 所有类目商品总数
        int productCount = allList.stream()
                .mapToInt(r -> r.getGoodsCount() != null ? r.getGoodsCount() : 0)
                .sum();
        respVO.setProductCount(productCount);

        // 按状态分组统计
        Map<String, Integer> statusMap = new java.util.LinkedHashMap<>();
        for (ExchangeCategoryDO item : allList) {
            String key = item.getStatus() != null ? item.getStatus() : "unknown";
            statusMap.merge(key, 1, Integer::sum);
        }
        List<ExchangeCategoryChartRespVO.StatusItem> statusCountList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : statusMap.entrySet()) {
            ExchangeCategoryChartRespVO.StatusItem si = new ExchangeCategoryChartRespVO.StatusItem();
            si.setStatus(entry.getKey());
            si.setCount(entry.getValue());
            statusCountList.add(si);
        }
        respVO.setStatusCountList(statusCountList);

        // 按范围分组统计
        Map<String, Integer> scopeMap = new java.util.LinkedHashMap<>();
        for (ExchangeCategoryDO item : allList) {
            String key = item.getScope() != null ? item.getScope() : "unknown";
            scopeMap.merge(key, 1, Integer::sum);
        }
        List<ExchangeCategoryChartRespVO.ScopeItem> scopeCountList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : scopeMap.entrySet()) {
            ExchangeCategoryChartRespVO.ScopeItem si = new ExchangeCategoryChartRespVO.ScopeItem();
            si.setScope(entry.getKey());
            si.setCount(entry.getValue());
            scopeCountList.add(si);
        }
        respVO.setScopeCountList(scopeCountList);

        return respVO;
    }

    private ExchangeCategoryDO validateExists(Long id) {
        ExchangeCategoryDO exchangeCategory = exchangeCategoryMapper.selectById(id);
        if (exchangeCategory == null) {
            throw exception(EXCHANGE_CATEGORY_NOT_EXISTS);
        }
        return exchangeCategory;
    }

    private void validateNameUnique(Long id, String name) {
        if (name == null) {
            return;
        }
        ExchangeCategoryDO existing = exchangeCategoryMapper.selectOne(ExchangeCategoryDO::getName, name);
        if (existing != null && !existing.getId().equals(id)) {
            throw exception(EXCHANGE_CATEGORY_NAME_EXISTS);
        }
    }

    @Override
    public List<ExchangeCategoryDO> getSimpleList() {
        return exchangeCategoryMapper.selectList();
    }

    @Override
    public void importData(List<ExchangeCategoryImportExcelVO> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (ExchangeCategoryImportExcelVO vo : list) {
            if (vo.getName() == null || vo.getName().trim().isEmpty()) {
                throw exception(EXCHANGE_CATEGORY_IMPORT_NAME_EMPTY);
            }
            if (vo.getGoodsCount() == null) {
                throw exception(EXCHANGE_CATEGORY_IMPORT_GOODS_COUNT_EMPTY);
            }
            if (vo.getEffectTime() == null) {
                throw exception(EXCHANGE_CATEGORY_IMPORT_EFFECT_TIME_EMPTY);
            }
            String scopeValue = ExchangeCategoryScopeEnum.valueOfLabel(vo.getScope());
            if (scopeValue == null) {
                throw exception(EXCHANGE_CATEGORY_IMPORT_SCOPE_INVALID, vo.getScope());
            }
            ExchangeCategoryDO doObj = BeanUtils.toBean(vo, ExchangeCategoryDO.class);
            doObj.setScope(scopeValue);
            doObj.setStatus(ExchangeCategoryStatusEnum.NOT_EFFECTIVE.getValue());
            exchangeCategoryMapper.insert(doObj);
        }
    }

}
