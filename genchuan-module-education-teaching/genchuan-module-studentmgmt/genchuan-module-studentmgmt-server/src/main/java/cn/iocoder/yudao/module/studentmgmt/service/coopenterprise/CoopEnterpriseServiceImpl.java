package cn.iocoder.yudao.module.studentmgmt.service.coopenterprise;

import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.BaseChartReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartCountVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartTrendVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.coopenterprise.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.coopenterprise.CoopEnterpriseDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.coopenterprise.CoopEnterpriseMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.CoopEnterpriseStatusEnum;
import cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.studentmgmt.enums.StudentMgmtDictTypeEnum;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import cn.iocoder.yudao.module.system.api.dict.DictDataApi;
import com.alibaba.fastjson.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.COOP_ENTERPRISE_NOT_EXISTS;

/**
 * 校企合作 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CoopEnterpriseServiceImpl implements CoopEnterpriseService {

    @Resource
    private CoopEnterpriseMapper coopEnterpriseMapper;

    @Resource
    private DictDataApi dictDataApi;
    @Resource
    private DeptApi deptApi;

    @Override
    public Long createCoopEnterprise(CoopEnterpriseSaveReqVO createReqVO) {
        // 插入
        CoopEnterpriseDO coopEnterprise = BeanUtils.toBean(createReqVO, CoopEnterpriseDO.class);
        coopEnterpriseMapper.insert(coopEnterprise);

        // 返回
        return coopEnterprise.getId();
    }

    @Override
    public void updateCoopEnterprise(CoopEnterpriseSaveReqVO updateReqVO) {
        // 校验存在
        validateCoopEnterpriseExists(updateReqVO.getId());
        // 更新
        CoopEnterpriseDO updateObj = BeanUtils.toBean(updateReqVO, CoopEnterpriseDO.class);
        coopEnterpriseMapper.updateById(updateObj);
    }

    @Override
    public void deleteCoopEnterprise(Long id) {
        // 校验存在
        validateCoopEnterpriseExists(id);
        // 删除
        coopEnterpriseMapper.deleteById(id);
    }

    @Override
        public void deleteCoopEnterpriseListByIds(List<Long> ids) {
        // 删除
        coopEnterpriseMapper.deleteByIds(ids);
        }


    private CoopEnterpriseDO validateCoopEnterpriseExists(Long id) {
        CoopEnterpriseDO coopEnterpriseDO = coopEnterpriseMapper.selectById(id);
        if ( coopEnterpriseDO == null) {
            throw exception(COOP_ENTERPRISE_NOT_EXISTS);
        }
        return coopEnterpriseDO;
    }

    @Override
    public CoopEnterpriseDO getCoopEnterprise(Long id) {
        return coopEnterpriseMapper.selectById(id);
    }

    @Override
    public PageResult<CoopEnterpriseDO> getCoopEnterprisePage(CoopEnterprisePageReqVO pageReqVO) {
        return coopEnterpriseMapper.selectPage(pageReqVO);
    }

    @Override
    public Boolean maintain(CoopEnterpriseMaintainReqVO updateReqVO) {
        // 校验存在
        CoopEnterpriseDO coopEnterprise = validateCoopEnterpriseExists(updateReqVO.getId());
        // 更新
        CoopEnterpriseDO updateObj = BeanUtils.toBean(updateReqVO, CoopEnterpriseDO.class);
        String loginUserNickname = SecurityFrameworkUtils.getLoginUserNickname();
        updateObj.setUpdater(loginUserNickname);
        updateObj.setUpdateTime(LocalDateTime.now());

        int i = coopEnterpriseMapper.updateById(updateObj);
        return i > 0;
    }

    @Override
    public CoopEnterpriseChartRespVO chart(CoopEnterpriseChartReqVO reqVO) {
        CoopEnterpriseChartRespVO vo = new CoopEnterpriseChartRespVO();

        Long deptId = reqVO.getDeptId();

        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        };

        // 1. 卡片数据
        vo = coopEnterpriseMapper.selectTotalCount(startTime, endTime,
                CoopEnterpriseStatusEnum.PENDING.getStatus(),
                CoopEnterpriseStatusEnum.ENDED.getStatus(), deptId);
        // 如果统计为空，则设置为0
        if (vo.getCooperatingEnterprise() == null) {
            vo.setCooperatingEnterprise(0);
        }
        if (vo.getFinishedEnterprise() == null) {
            vo.setFinishedEnterprise(0);
        }
        if (vo.getTotalEnterprise() == null) {
            vo.setTotalEnterprise(0);
        }

        List<JSONObject> deptCoopCountList = coopEnterpriseMapper.selectDeptCoopCountList(startTime, endTime, deptId);
        List<ChartTrendVO> coopTrendList = coopEnterpriseMapper.selectCoopTrendList(startTime, endTime, deptId);

        vo.setDeptCoopCount(deptCoopCountList);
        vo.setCoopTrend(coopTrendList);
        return vo;
    }

    @Override
    public CoopEnterpriseDistributionRespVO enterpriseDistribution(BaseChartReqVO reqVO) {
        CoopEnterpriseDistributionRespVO vo = new CoopEnterpriseDistributionRespVO();
        LocalDateTime[] timeRange = reqVO.getTimeRange();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (null != timeRange) {
            startTime = timeRange[0];
            endTime = timeRange[1];
        }
        List<JSONObject> typeList = coopEnterpriseMapper.selectTypeListList(startTime, endTime);
        List<JSONObject> typeDistributionList = new ArrayList<>();
        // 将类型转换成中文的label
        for (JSONObject json : typeList) {
            String type = json.getString("type");
            JSONObject typeDate = new JSONObject();

            String dictDataLabel = type;
            CommonResult<List<DictDataRespDTO>> dictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.COOP_ENTERPRISE_ENTERPRISE_TYPE.getType());
            if (dictDataList.getData() != null) {
                for (DictDataRespDTO dictData : dictDataList.getData()) {
                    if (dictData.getValue().equals(type)) {
                        dictDataLabel = dictData.getLabel();
                        typeDate.put("name", dictDataLabel);
                        typeDate.put("value", json.getInteger("count"));
                        typeDistributionList.add(typeDate);
                        break;
                    }
                }
            }
        }
        List<JSONObject> deptDistributionList = coopEnterpriseMapper.selectDistributionList(startTime, endTime);
        List<ChartCountVO> deptNameList = new ArrayList<>();
        // 将部门ID转换成部门名称
        for (JSONObject json : deptDistributionList) {
            ChartCountVO bean = new ChartCountVO();

            CommonResult<DeptRespDTO> dept = deptApi.getDept(Long.valueOf(json.getInteger("deptId")));
            DeptRespDTO data = dept.getData();
            if (data == null) {
                throw new ServiceException(ErrorCodeConstants.DEPT_NOT_EXISTS);
            }

            bean.setName(data.getName());
            bean.setValue(json.getInteger("coopCount"));
            deptNameList.add(bean);
        }

        vo.setTypeDistribution(typeDistributionList);
        vo.setDeptDistribution(deptNameList);
        return vo;
    }

}