package com.PaycellProject.SB.CRUD.MVC.Project.service;


import com.PaycellProject.SB.CRUD.MVC.Project.model.Items;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ItemService {
    List<Items> getAllItems();
    void saveItem(Items item);
    Items getItemById(long id);
    void deleteItemById(long id);

    Page<Items> findPaginated(int pageNum, int pageSize,
                              String sortField,
                              String sortDirection);
}