package com.PaycellProject.SB.CRUD.MVC.Project.repository;

import com.PaycellProject.SB.CRUD.MVC.Project.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // ??


@Repository
public interface ItemRepo extends JpaRepository<Items, Long> {

}