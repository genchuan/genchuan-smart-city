package cn.iocoder.yudao.module.studentmgmt.service.treatmgmt;

import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.treatmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.treatmgmt.TreatMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.studentinfo.StudentInfoMapper;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.treatmgmt.TreatMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.TreatStatusEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.TreatTypeEnum;
import cn.iocoder.yudao.module.system.api.dict.DictDataApi;
import com.alibaba.fastjson.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.TREAT_MGMT_NOT_EXISTS;

/**
 * 就诊管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TreatMgmtServiceImpl implements TreatMgmtService {

    @Resource
    private TreatMgmtMapper treatMgmtMapper;
    @Resource
    private DictDataApi dictDataApi;
    @Resource
    private StudentInfoMapper studentInfoMapper;

    @Override
    public Long createTreatMgmt(TreatMgmtSaveReqVO createReqVO) {
        // 插入
        TreatMgmtDO treatMgmt = BeanUtils.toBean(createReqVO, TreatMgmtDO.class);
        treatMgmtMapper.insert(treatMgmt);

        // 返回
        return treatMgmt.getId();
    }

    @Override
    public void updateTreatMgmt(TreatMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateTreatMgmtExists(updateReqVO.getId());
        // 更新
        TreatMgmtDO updateObj = BeanUtils.toBean(updateReqVO, TreatMgmtDO.class);
        treatMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteTreatMgmt(Long id) {
        // 校验存在
        validateTreatMgmtExists(id);
        // 删除
        treatMgmtMapper.deleteById(id);
    }

    @Override
    public void deleteTreatMgmtListByIds(List<Long> ids) {
        // 删除
        treatMgmtMapper.deleteByIds(ids);
    }


    private TreatMgmtDO validateTreatMgmtExists(Long id) {
        TreatMgmtDO treatMgmtDO = treatMgmtMapper.selectById(id);
        if (treatMgmtDO == null) {
            throw exception(TREAT_MGMT_NOT_EXISTS);
        }
        return treatMgmtDO;
    }

    @Override
    public TreatMgmtDO getTreatMgmt(Long id) {
        return treatMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<TreatMgmtDO> getTreatMgmtPage(TreatMgmtPageReqVO pageReqVO) {
        return treatMgmtMapper.selectPage(pageReqVO);
    }

    @Override
    public Boolean appoint(TreatMgmtAppointReqVO reqVO) {
        // 提交就诊预约申请，自动将状态置为待审核，记录创建人信息，
        TreatMgmtDO treatMgmt = BeanUtils.toBean(reqVO, TreatMgmtDO.class);
        treatMgmt.setStatus(TreatStatusEnum.PENDING.getStatus());
        //获取当前用户
        String username = SecurityFrameworkUtils.getLoginUserNickname();
        treatMgmt.setCreator(username);
        treatMgmt.setRegisterTime(LocalDateTime.now());
        treatMgmt.setApplyTime(LocalDateTime.now());
        // 插入
        int i = treatMgmtMapper.insert(treatMgmt);
        if (i > 0) {
            return true;
        }

        return false;
    }

    @Override
    public Boolean audit(TreatMgmtAuditReqVO reqVO) {
        int total = 0;
        for (Long id : reqVO.getIds()) {
            // 校验存在
            TreatMgmtDO treatMgmtDO = validateTreatMgmtExists(id);
            String status = treatMgmtDO.getStatus();
            if (!status.equals(TreatStatusEnum.PENDING.getStatus())) {
                throw exception("不是待审核状态，不可审核");
            }
            treatMgmtDO.setStatus(TreatStatusEnum.VISITED.getStatus());
            //获取当前用户
            String username = SecurityFrameworkUtils.getLoginUserNickname();
            treatMgmtDO.setAuditUser(username);
            // 更新
            int i = treatMgmtMapper.updateById(treatMgmtDO);
            total += i;
        }
        if (total > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean register(TreatMgmtRegisterReqVO reqVO) {
        int total = 0;
        for (Long id : reqVO.getIds()) {
            // 校验存在
            TreatMgmtDO treatMgmtDO = validateTreatMgmtExists(id);
//            String status = treatMgmtDO.getStatus();
//            if (!status.equals(TreatStatusEnum.PENDING.getStatus())) {
//                throw exception("不是待审核状态，不可就诊");
//            }
            // 完成就诊登记，记录就诊内容及登记时间，更新就诊状态
            treatMgmtDO.setStatus(TreatStatusEnum.VISITED.getStatus());
            treatMgmtDO.setRegisterTime(reqVO.getRegisterTime());
            treatMgmtDO.setTreatContent(reqVO.getTreatContent());
            // 更新
            int i = treatMgmtMapper.updateById(treatMgmtDO);
            total += i;
        }
        if (total > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean feedback(TreatMgmtFeedbackReqVO reqVO) {

        // 校验存在
        TreatMgmtDO treatMgmtDO = validateTreatMgmtExists(reqVO.getId());
        String status = treatMgmtDO.getStatus();
        if (!status.equals(TreatStatusEnum.VISITED.getStatus())) {
            throw exception("不是待已就诊状态，不可反馈");
        }
        // 完成就诊登记，记录就诊内容及登记时间，更新就诊状态
        treatMgmtDO.setStatus(TreatStatusEnum.VISITED.getStatus());
        treatMgmtDO.setFeedbackTime(reqVO.getFeedbackTime());
        treatMgmtDO.setReserve1(reqVO.getFeedbackContent());
        // 更新
        int i = treatMgmtMapper.updateById(treatMgmtDO);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public TreatMgmtChartRespVO chart(TreatMgmtChartReqVO reqVO) {
        TreatMgmtChartRespVO vo = new TreatMgmtChartRespVO();

        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }


        // 1. 卡片数据
        //totalCount (integer): 本期考评总记录数。
        vo = treatMgmtMapper.selectTotalTreatCount(startTime, endTime,
                TreatStatusEnum.VISITED.getStatus(),
                TreatTypeEnum.EMERGENCY.getStatus(),
                TreatTypeEnum.OUTPATIENT.getStatus(),
                TreatTypeEnum.OTHER.getStatus());

        // 2. 趋势数据
        List<JSONObject> sevenDayTreatCountJsonList = treatMgmtMapper.select7dayTreatCount();
        // 最近七天的日期
        List<JSONObject> trendList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            JSONObject trendJson = new JSONObject();
            String dataStr = LocalDate.now().minusDays(i).toString();

            trendJson.put("date", dataStr);

            // 取出sevenDayTreatCountJsonList与dataStr匹配的数据
            Integer count = 0;
            for (JSONObject json : sevenDayTreatCountJsonList) {
                String applyTime = json.getString("applyTime");
                if (applyTime.equals(dataStr)) {
                    count = json.getInteger("count");
                    break;
                }
            }
            trendJson.put("count", count);

            trendList.add(trendJson);
        }
        vo.setRecentWeekTreatTrend(trendList);

        return vo;
    }

    @Override
    public TreatMgmtDistributionRespVO treatDistribution(TreatMgmtChartReqVO reqVO) {
        TreatMgmtDistributionRespVO vo = new TreatMgmtDistributionRespVO();

        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }


        // 1. 卡片数据
        //totalCount (integer): 本期考评总记录数。
        List<JSONObject> treatTypeDistributionList = treatMgmtMapper.selectTotalTreatCountByType(startTime, endTime);
        // treatTypeDistributionList 里的 type转换成中文
        List<JSONObject> typeList = new ArrayList<>();
        for (JSONObject json : treatTypeDistributionList) {
            String type = json.getString("type");
            JSONObject typeJson = new JSONObject();
            String dictDataLabel = "";
            CommonResult<List<DictDataRespDTO>> dictDataList = dictDataApi.getDictDataList(TreatTypeEnum.DICT_TYPE);
            if (dictDataList.getData() != null) {
                for (DictDataRespDTO dictData : dictDataList.getData()) {
                    if (dictData.getValue().equals(type)) {
                        dictDataLabel = dictData.getLabel();
                        break;
                    }
                }
            }
            typeJson.put("type", dictDataLabel);
            typeJson.put("value", json.getInteger("count"));
            typeList.add(typeJson);
        }
        vo.setTreatTypeDistribution(typeList);

        // 2. 趋势数据
        // 查询所有学生的年级
        List<String> gradeList = studentInfoMapper.selectAllGrade();
        List<JSONObject> gradeDistribution = new ArrayList<>();
        for (String grade : gradeList) {

            JSONObject json = new JSONObject();

            List<JSONObject> gradeCountList = treatMgmtMapper.selectGradeDistribution(startTime, endTime, grade);
            json.put("grade", grade);
            Integer value = 0;
            if (null != gradeCountList && gradeCountList.size() > 0) {
                JSONObject jsonObject = gradeCountList.get(0);
                value = jsonObject.getInteger("value");
            }
            json.put("value", value);
            gradeDistribution.add(json);
        }
        vo.setGradeDistribution(gradeDistribution);
        return vo;
    }

}