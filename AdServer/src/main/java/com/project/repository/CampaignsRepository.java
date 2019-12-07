package com.project.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Campaigns;


@Repository
public interface CampaignsRepository extends CrudRepository<Campaigns,Integer> {
	List<Campaigns> findByBannerId(Integer bid);
}
