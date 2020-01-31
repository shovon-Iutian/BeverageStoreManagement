package com.example.appengine.java8.Management;

import com.example.appengine.java8.DTO.VoteTime;
import com.example.appengine.java8.Entity.VoteTimeEntity;
import com.example.appengine.java8.Service.VoteTimeManagementService;
import com.google.appengine.api.datastore.Entity;

import java.util.Date;

public class VoteTimeManagement extends AbstractCrudManagement<VoteTime> implements VoteTimeManagementService<VoteTime> {

    private VoteTimeEntity voteTimeEntity = new VoteTimeEntity();

    @Override
    protected Entity convertDtoToEntity(VoteTime voteTime) {
        Entity voteEntity= new Entity(voteTimeEntity.getVoteTimeKind(),voteTimeEntity.getVoteTimeKey());
        voteEntity.setProperty(voteTimeEntity.getVoteTimeFirstProperty(), voteTime.getStartDate());
        voteEntity.setProperty(voteTimeEntity.getVoteTimeSecondProperty(), voteTime.getEndDate());
        return voteEntity;
    }

    @Override
    protected VoteTime convertEntityToDto(Entity entity) {
        VoteTime voteTime = new VoteTime();
        voteTime.setKey(entity.getKey());
        voteTime.setStartDate((Date) entity.getProperty(voteTimeEntity.getVoteTimeFirstProperty()));
        voteTime.setEndDate((Date) entity.getProperty(voteTimeEntity.getVoteTimeSecondProperty()));
        return voteTime;
    }
}
