package com.vincent.personal.service.impl;

import com.vincent.personal.modal.vo.RelationshipVoKey;
import com.vincent.personal.service.IRelationshipService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IDEA
 * author:vincent
 * Date:2018/11/8
 */
@Service
public class RelationshipServiceImpl implements IRelationshipService {
    @Override
    public void deleteById(Integer cid, Integer mid) {

    }

    @Override
    public Long countById(Integer cid, Integer mid) {
        return null;
    }

    @Override
    public void insertVo(RelationshipVoKey relationshipVoKey) {

    }

    @Override
    public List<RelationshipVoKey> getRelationshipById(Integer cid, Integer mid) {
        return null;
    }
}
