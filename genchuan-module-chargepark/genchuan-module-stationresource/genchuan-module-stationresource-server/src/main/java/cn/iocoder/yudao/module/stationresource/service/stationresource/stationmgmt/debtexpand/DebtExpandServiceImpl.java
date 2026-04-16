package cn.iocoder.yudao.module.stationresource.service.stationresource.stationmgmt.debtexpand;

import cn.hutool.core.collection.CollUtil;

import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.DebtExpandPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.DebtExpandSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.chart.DebtExpandChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.ops.DebtExpandCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.ops.ImportRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.ops.UpdateDebtExpandReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.debtexpand.DebtExpandDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.stationmgmt.debtexpand.DebtExpandMapper;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel.VrvExcelUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import org.springframework.web.multipart.MultipartFile;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.stationresource.enums.ErrorCodeConstants.*;

/**
 * 联合追缴拓场配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class DebtExpandServiceImpl implements DebtExpandService {

    @Resource
    private DebtExpandMapper debtExpandMapper;

    @Override
    public DebtExpandChartRespVO getDebtExpandChart() {
        DebtExpandChartRespVO resp = new DebtExpandChartRespVO();

        // 1. 卡片统计（XML查）
        DebtExpandChartRespVO.CardDataItem card = debtExpandMapper.selectCardData();
        resp.setCardData(card);

        // 2. 拓场进度趋势（XML查）
        List<DebtExpandChartRespVO.ProgressLineItem> lineList = debtExpandMapper.selectProgressLineList();
        resp.setProgressLineList(lineList);

        // 3. 追缴成功率柱状图（XML查）
        List<DebtExpandChartRespVO.RecoveryBarItem> barList = debtExpandMapper.selectRecoveryBarList();
        resp.setRecoveryBarList(barList);

        return resp;
    }
    @Override
    public void myUpdateDebtExpand(UpdateDebtExpandReqVO updateReqVO) {
        // 校验存在
        validateDebtExpandExists(updateReqVO.getId());
        // 更新
        DebtExpandDO updateObj = BeanUtils.toBean(updateReqVO, DebtExpandDO.class);
        debtExpandMapper.updateById(updateObj);
    }
    @Override
    public void updateDebtExpandStatus(List<Long> ids, String status) {
        // 批量更新状态 + 审核时间 + 审核人
        debtExpandMapper.update(new LambdaUpdateWrapper<DebtExpandDO>()
                .in(DebtExpandDO::getId, ids)
                .set(DebtExpandDO::getStatus, status)
                // ====== 新增：审核时间（当前时间）======
                .set(DebtExpandDO::getAuditTime, LocalDateTime.now())
                // ====== 新增：审核人ID（当前登录用户ID）======
                .set(DebtExpandDO::getAuditUserId, SecurityFrameworkUtils.getLoginUserId())
        );
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ImportRespVO importDebtExpand(MultipartFile file, boolean updateSupport) {
        ImportRespVO resp = new ImportRespVO();
        resp.setSuccessCount(0);
        resp.setFailureCount(0);
        resp.setFailureList(new ArrayList<>());

        try {
            // 1. 解析 Excel（直接用你现有的 CreateReqVO，不用新建VO）
            Map<String, Object> resultMap = VrvExcelUtils.importExcelAndReturnEntity(
                    file,
                    DebtExpandCreateReqVO.class.getName()
            );
            List<DebtExpandCreateReqVO> reqList = (List<DebtExpandCreateReqVO>) resultMap.get("entityList");

            if (CollUtil.isEmpty(reqList)) {
                throw exception("导入数据不能为空");
            }

            int successCount = 0;
            List<ImportRespVO.ImportFailure> failures = new ArrayList<>();

            // 2. 遍历导入
            for (int i = 0; i < reqList.size(); i++) {
                DebtExpandCreateReqVO req = reqList.get(i);
                int rowIndex = i + 2;

                try {
                    // ==============================================
                    // 核心：直接复用已写好的 addDebtExpand 方法
                    // ==============================================
                    if (updateSupport) {
                        // 更新模式：先查是否存在 → 存在更新，不存在新增
                        DebtExpandDO exist = debtExpandMapper.selectOne(DebtExpandDO::getStationId, req.getStationId());
                        if (exist != null) {
                            // 有就更新（你可以自己写 update 方法，这里保持逻辑一致）
                            DebtExpandDO updateDO = BeanUtils.toBean(req, DebtExpandDO.class);
                            updateDO.setId(exist.getId());
                            debtExpandMapper.updateById(updateDO);
                        } else {
                            // 复用新增方法
                            addDebtExpand(req);
                        }
                    } else {
                        // 纯新增：完全复用现有方法（自带 status=未生效，progress=0）
                        addDebtExpand(req);
                    }

                    successCount++;

                } catch (Exception e) {
                    // 记录失败行
                    ImportRespVO.ImportFailure failure = new ImportRespVO.ImportFailure();
                    failure.setRowIndex(rowIndex);
                    failure.setMessage(e.getMessage());
                    failures.add(failure);
                }
            }

            // 封装返回
            resp.setSuccessCount(successCount);
            resp.setFailureCount(failures.size());
            resp.setFailureList(failures);
            return resp;

        } catch (Exception e) {
            // 文件解析失败
            ImportRespVO.ImportFailure failure = new ImportRespVO.ImportFailure();
            failure.setRowIndex(1);
            failure.setMessage("导入失败：" + e.getMessage());
            resp.getFailureList().add(failure);
            resp.setFailureCount(1);
            return resp;
        }
    }
    @Override
    public Long createDebtExpand(DebtExpandSaveReqVO createReqVO) {
        // 插入
        DebtExpandDO debtExpand = BeanUtils.toBean(createReqVO, DebtExpandDO.class);
        debtExpandMapper.insert(debtExpand);

        // 返回
        return debtExpand.getId();
    }

    @Override
    public void updateDebtExpand(DebtExpandSaveReqVO updateReqVO) {
        // 校验存在
        validateDebtExpandExists(updateReqVO.getId());
        // 更新
        DebtExpandDO updateObj = BeanUtils.toBean(updateReqVO, DebtExpandDO.class);
        debtExpandMapper.updateById(updateObj);
    }

    @Override
    public void deleteDebtExpand(Long id) {
        // 校验存在
        validateDebtExpandExists(id);
        // 删除
        debtExpandMapper.deleteById(id);
    }

    @Override
        public void deleteDebtExpandListByIds(List<Long> ids) {
        // 删除
        debtExpandMapper.deleteByIds(ids);
        }


    private void validateDebtExpandExists(Long id) {
        if (debtExpandMapper.selectById(id) == null) {
            throw exception(DEBT_EXPAND_NOT_EXISTS);
        }
    }

    @Override
    public DebtExpandDO getDebtExpand(Long id) {
        return debtExpandMapper.selectById(id);
    }

    @Override
    public PageResult<DebtExpandDO> getDebtExpandPage(DebtExpandPageReqVO pageReqVO) {
        return debtExpandMapper.selectPage(pageReqVO);
    }

    @Override
    public Long addDebtExpand(DebtExpandCreateReqVO createReqVO) {
        // ===================== 【1：校验：同一个场站不能重复添加】 =====================
        DebtExpandDO exist = debtExpandMapper.selectOne(DebtExpandDO::getStationId, createReqVO.getStationId());
        if (exist != null) {
            // 直接抛出业务异常，前端会提示
            throw exception("合作场站已存在，请勿重复创建");
        }

        // 2. 转换 DO
        DebtExpandDO debtExpand = BeanUtils.toBean(createReqVO, DebtExpandDO.class);

        // 给 status 设置默认值
        debtExpand.setStatus("未生效");
        //进度设置为0
        debtExpand.setProgress(0);

        // 3. 插入数据库
        debtExpandMapper.insert(debtExpand);

        // 4. 返回主键
        return debtExpand.getId();
    }
}
