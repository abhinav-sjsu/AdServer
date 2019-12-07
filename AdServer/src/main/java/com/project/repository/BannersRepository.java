package com.project.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Banners;

@Repository
public interface BannersRepository extends CrudRepository<Banners,Integer> {
	List<Banners> findByCampaignId(Integer cid);
}
