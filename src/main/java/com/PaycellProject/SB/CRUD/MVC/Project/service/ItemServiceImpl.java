package com.PaycellProject.SB.CRUD.MVC.Project.service;

import com.PaycellProject.SB.CRUD.MVC.Project.model.Items;
import com.PaycellProject.SB.CRUD.MVC.Project.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemRepo itemRepository;

    @Override
    public List<Items> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public void saveItem(Items item) {
        this.itemRepository.save(item);
    }

    @Override
    public Items getItemById(long id) {
        Optional<Items> optionalItem = itemRepository.findById(id);
        Items item = null;
        if (optionalItem.isPresent()) {
            item = optionalItem.get();
        } else {
            throw new RuntimeException("Item not found for id : " + id);
        }
        return item;
    }

    @Override
    public void deleteItemById(long id) {
        this.itemRepository.deleteById(id);
    }


    @Override
    public Page<Items> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return this.itemRepository.findAll(pageable);
    }
}
