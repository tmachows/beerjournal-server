package com.beerjournal.breweriana.item.persistence;

import com.beerjournal.breweriana.file.persistence.FileRepository;
import com.beerjournal.breweriana.utils.Converters;
import com.beerjournal.breweriana.utils.UpdateListener;
import com.beerjournal.infrastructure.error.BeerJournalException;
import com.beerjournal.infrastructure.error.ErrorInfo;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final ItemCrudRepository crudRepository;
    private final FileRepository fileRepository;
    private final Set<UpdateListener<Item>> itemUpdateListeners;

    public Optional<Item> findOneById(ObjectId id) {
        return crudRepository.findOneById(id);
    }

    public Item save(Item item) {
        Item savedItem = crudRepository.save(item);
        notifyInsert(item);
        return savedItem;
    }

    public Item delete(String itemId) {
        Item itemToDelete = crudRepository.findOneById(Converters.toObjectId(itemId))
                .orElseThrow(() -> new BeerJournalException(ErrorInfo.ITEM_NOT_FOUND));

        deleteImages(itemToDelete);
        notifyDelete(itemToDelete);
        return itemToDelete;
    }

    public Item update(Item item) {
        Item updatedItem = crudRepository.save(item);
        notifyUpdate(updatedItem);
        return updatedItem;
    }

    private void deleteImages(Item item) {
        item.getImageIds().forEach(fileRepository::deleteFileById);
    }

    private void notifyInsert(Item item) {
        itemUpdateListeners.forEach(listener -> listener.onInsert(item));
    }

    private void notifyDelete(Item itemToDelete) {
        itemUpdateListeners.forEach(listener -> listener.onDelete(itemToDelete));
    }

    private void notifyUpdate(Item updatedItem) {
        itemUpdateListeners.forEach(listener -> listener.onUpdate(updatedItem));
    }
}
