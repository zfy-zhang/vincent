package com.vincent.personal.service.impl;

import com.vincent.personal.dto.MetaDto;
import com.vincent.personal.modal.vo.MetaVo;
import com.vincent.personal.service.IMetaService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IDEA
 * author:vincent
 * Date:2018/11/8
 */
@Service
public class MetaServiceImpl implements IMetaService {
    @Override
    public MetaDto getMeta(String type, String name) {
        return null;
    }

    @Override
    public Integer countMeta(Integer mid) {
        return null;
    }

    @Override
    public List<MetaVo> getMetas(String types) {
        return null;
    }

    @Override
    public void saveMetas(Integer cid, String names, String type) {

    }

    @Override
    public void saveMeta(String type, String name, Integer mid) {

    }

    @Override
    public List<MetaDto> getMetaList(String type, String orderby, int limit) {
        return null;
    }

    @Override
    public void delete(int mid) {

    }

    @Override
    public void saveMeta(MetaVo metas) {

    }

    @Override
    public void update(MetaVo metas) {

    }
}
