// DataMapMapper.java
package cn.iocoder.yudao.module.industry.dal.mysql.culturesportstourism.dpzl.datamap;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap.vo.CultureDataMapQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap.vo.CultureDataMapRespVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CultureDataMapMapper extends BaseMapper<CultureDataMapRespVO> {
    List<CultureDataMapRespVO> queryDataMap(CultureDataMapQueryReqVO queryVO);
}