package cn.iocoder.yudao.module.waterdetection.service.watersampletestsummary;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.watersampletestsummary.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersampletestsummary.WaterSampleTestSummaryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.watersampletestsummary.WaterSampleTestSummaryMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 外检统计水质检测结果汇总 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class WaterSampleTestSummaryServiceImpl implements WaterSampleTestSummaryService {

    @Resource
    private WaterSampleTestSummaryMapper waterSampleTestSummaryMapper;

    @Override
    public Long createWaterSampleTestSummary(WaterSampleTestSummarySaveReqVO createReqVO) {
        // 插入
        WaterSampleTestSummaryDO waterSampleTestSummary = BeanUtils.toBean(createReqVO, WaterSampleTestSummaryDO.class);
        waterSampleTestSummaryMapper.insert(waterSampleTestSummary);
        // 返回
        return waterSampleTestSummary.getId();
    }

    @Override
    public void updateWaterSampleTestSummary(WaterSampleTestSummarySaveReqVO updateReqVO) {
        // 校验存在
        validateWaterSampleTestSummaryExists(updateReqVO.getId());
        // 更新
        WaterSampleTestSummaryDO updateObj = BeanUtils.toBean(updateReqVO, WaterSampleTestSummaryDO.class);
        waterSampleTestSummaryMapper.updateById(updateObj);
    }

    @Override
    public void deleteWaterSampleTestSummary(Long id) {
        // 校验存在
        validateWaterSampleTestSummaryExists(id);
        // 删除
        waterSampleTestSummaryMapper.deleteById(id);
    }

    private void validateWaterSampleTestSummaryExists(Long id) {
        if (waterSampleTestSummaryMapper.selectById(id) == null) {
            throw exception(WATER_SAMPLE_TEST_SUMMARY_NOT_EXISTS);
        }
    }

    @Override
    public WaterSampleTestSummaryDO getWaterSampleTestSummary(Long id) {
        return waterSampleTestSummaryMapper.selectById(id);
    }

    @Override
    public PageResult<WaterSampleTestSummaryDO> getWaterSampleTestSummaryPage(WaterSampleTestSummaryPageReqVO pageReqVO) {
        return waterSampleTestSummaryMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class) // 添加事务，异常则回滚所有导入
    public WaterSampleTestImportRespVO importWaterSampleList(List<WaterSampleTestImportExcelVO> importWaterSamples, boolean isUpdateSupport) {

        // 1. 参数校验
        if (CollUtil.isEmpty(importWaterSamples)) {
            throw exception(WATER_SAMPLE_TEST_SUMMARY_NOT_EXISTS);
        }

        // 2. 遍历，逐个创建 or 更新
        WaterSampleTestImportRespVO respVO = WaterSampleTestImportRespVO.builder()
                .createSampleNames(new ArrayList<>())
                .updateSampleNames(new ArrayList<>())
                .failureSampleNames(new LinkedHashMap<>())
                .build();

        importWaterSamples.forEach(importWaterSample -> {
            // 2.2.1 查询是否存在（返回列表）
            List<WaterSampleTestSummaryDO> exitWaterList = waterSampleTestSummaryMapper.selectListBySampleName(importWaterSample.getSampleNo());

            // 如果查询结果为空，执行插入
            if (CollUtil.isEmpty(exitWaterList)) {
                waterSampleTestSummaryMapper.insert(BeanUtils.toBean(importWaterSample, WaterSampleTestSummaryDO.class));
                respVO.getCreateSampleNames().add(importWaterSample.getSampleNo());
                return;
            }

            // 如果存在，判断是否允许更新
            if (!isUpdateSupport) {
                respVO.getFailureSampleNames().put(importWaterSample.getSampleNo(), WATER_SAMPLE_RESULT_EXISTS.getMsg());
                return;
            }

            // 更新第一个匹配的记录（或按业务需求处理）
            WaterSampleTestSummaryDO firstMatch = exitWaterList.get(0); // 取第一个匹配项
            WaterSampleTestSummaryDO updateWaterSample = BeanUtils.toBean(importWaterSample, WaterSampleTestSummaryDO.class);
            updateWaterSample.setId(firstMatch.getId()); // 设置 ID 用于更新
            waterSampleTestSummaryMapper.updateById(updateWaterSample);
            respVO.getUpdateSampleNames().add(importWaterSample.getSampleNo());
        });
        return respVO;
    }

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public WaterDeviceDataRespVO receiveDeviceData(WaterDeviceDataReqVO deviceData) {
//        // 1. 设备认证校验（这里可以做更复杂的设备认证逻辑）
//        validateDevice(deviceData);
//
//        // 2. 初始化结果对象
//        List<String> successSampleNos = new ArrayList<>();
//        Map<String, String> failureSamples = new LinkedHashMap<>();
//
//        // 3. 处理每一条检测数据
//        for (WaterDeviceDataReqVO.DeviceDetailVO detail : deviceData.getDetails()) {
//            try {
//                // 3.1 根据检测项目映射到对应的数据库字段
//                WaterSampleTestSummaryDO waterSample = convertDeviceDetailToDO(deviceData, detail);
//
//                // 3.2 检查是否已存在相同样品编号的记录
//                List<WaterSampleTestSummaryDO> existList = waterSampleTestSummaryMapper
//                        .selectListBySampleName(detail.getYangpinbianhao());
//
//                if (CollUtil.isEmpty(existList)) {
//                    // 新增记录
//                    waterSampleTestSummaryMapper.insert(waterSample);
//                } else {
//                    // 更新记录（取第一条匹配的记录）
//                    WaterSampleTestSummaryDO existRecord = existList.get(0);
//                    waterSample.setId(existRecord.getId());
//                    waterSampleTestSummaryMapper.updateById(waterSample);
//                }
//
//                successSampleNos.add(detail.getYangpinbianhao());
//
//            } catch (Exception e) {
//                // 记录失败信息
//                failureSamples.put(detail.getYangpinbianhao(), e.getMessage());
//            }
//        }
//
//        // 4. 构建返回结果
//        return WaterDeviceDataRespVO.builder()
//                .success(true)
//                .code("200")
//                .message("设备数据接收完成")
//                .successCount(successSampleNos.size())
//                .failureCount(failureSamples.size())
//                .successSampleNos(successSampleNos)
//                .failureSamples(failureSamples)
//                .timestamp(System.currentTimeMillis())
//                .build();
//    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String receiveDeviceData(WaterDeviceDataReqVO deviceData) {
        // 1. 设备认证校验
        validateDevice(deviceData);

        // 2. 处理每一条检测数据
        for (WaterDeviceDataReqVO.DeviceDetailVO detail : deviceData.getDetails()) {
            try {
                // 转换并保存数据
                WaterSampleTestSummaryDO waterSample = convertDeviceDetailToDO(deviceData, detail);

                // 检查是否已存在相同样品编号的记录
                List<WaterSampleTestSummaryDO> existList = waterSampleTestSummaryMapper
                        .selectListBySampleName(detail.getYangpinbianhao());

                if (CollUtil.isEmpty(existList)) {
                    // 新增记录
                    waterSampleTestSummaryMapper.insert(waterSample);
                } else {
                    // 更新记录（取第一条匹配的记录）
                    WaterSampleTestSummaryDO existRecord = existList.get(0);
                    waterSample.setId(existRecord.getId());
                    waterSampleTestSummaryMapper.updateById(waterSample);
                }
            } catch (Exception e) {
                // 记录错误日志，但继续处理其他数据
//                log.error("处理设备数据失败，样品编号: {}", detail.getYangpinbianhao(), e);
            }
        }

        // 3. 直接返回字符串 "success"
        return "success";
    }

    /**
     * 设备认证校验
     */
    private void validateDevice(WaterDeviceDataReqVO deviceData) {
        // 这里可以添加更复杂的设备认证逻辑
        if (StrUtil.isBlank(deviceData.getYqbh())) {
            throw exception(WATER_SAMPLE_TEST_SUMMARY_NOT_EXISTS);
        }
        // 可以验证设备密码、IP白名单等
    }

    /**
     * 将设备数据转换为数据库对象
     */
    private WaterSampleTestSummaryDO convertDeviceDetailToDO(WaterDeviceDataReqVO deviceData,
                                                             WaterDeviceDataReqVO.DeviceDetailVO detail) {
        WaterSampleTestSummaryDO waterSample = new WaterSampleTestSummaryDO();

        // 基础信息映射
        waterSample.setClientName(deviceData.getDwmc()); // 检测单位 -> 委托单位
        waterSample.setReceiveDate(detail.getJianceriqi()); // 检测日期 -> 收样日期
        waterSample.setSampleNo(detail.getYangpinbianhao()); // 样品编号
        waterSample.setSampleName(detail.getYangpinmingcheng()); // 样品名称
        waterSample.setSamplingLocation(detail.getJiancedidian()); // 检测地点 -> 采样地点

        // 根据检测项目映射到对应的检测值字段
        String jiancexiangmu = detail.getJiancexiangmu();
        String jiancezhi = detail.getJiancezhi();

        // 检测项目到数据库字段的映射
        switch (jiancexiangmu) {
            case "pH":
                waterSample.setPhValue(jiancezhi);
                break;
            case "氨氮":
            case "氨(以N计)":
                waterSample.setAmmoniaN(jiancezhi);
                break;
            case "浊度":
            case "浑浊度":
                waterSample.setTurbidity(jiancezhi);
                break;
            case "氟化物":
                waterSample.setFluoride(jiancezhi);
                break;
            case "镉":
                waterSample.setCadmium(jiancezhi);
                break;
            case "铬(六价)":
                waterSample.setChromium(jiancezhi);
                break;
            case "汞":
                waterSample.setMercury(jiancezhi);
                break;
            case "硫酸盐":
                waterSample.setSulfate(jiancezhi);
                break;
            case "铝":
                waterSample.setAluminum(jiancezhi);
                break;
            case "氯化物":
                waterSample.setChloride(jiancezhi);
                break;
            case "锰":
                waterSample.setManganese(jiancezhi);
                break;
            case "铅":
                waterSample.setLead(jiancezhi);
                break;
            case "溶解性总固体":
                waterSample.setDissolvedSolids(jiancezhi);
                break;
            case "色度":
                waterSample.setColorDegree(jiancezhi);
                break;
            case "砷":
                waterSample.setArsenic(jiancezhi);
                break;
            case "铁":
                waterSample.setIron(jiancezhi);
                break;
            case "铜":
                waterSample.setCopper(jiancezhi);
                break;
            case "硝酸盐":
                waterSample.setNitrateN(jiancezhi);
                break;
            case "锌":
                waterSample.setZinc(jiancezhi);
                break;
            case "总硬度":
                waterSample.setTotalHardness(jiancezhi);
                break;
            // 可以继续添加其他检测项目的映射
            default:
                // 如果检测项目不匹配已知字段，可以记录到备注或其他字段
                break;
        }

        return waterSample;
    }

}