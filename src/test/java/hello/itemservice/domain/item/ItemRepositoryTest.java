package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    private ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item itemA = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(itemA);

        //then
        Item foundItem = itemRepository.findById(savedItem.getId());
        assertThat(savedItem).isEqualTo(foundItem);
    }

    @Test
    void findById() {
        //given
        Item itemA = new Item("itemA", 10000, 10);
        itemA = itemRepository.save(itemA);

        //when
        Item foundItem = itemRepository.findById(itemA.getId());

        //then
        assertThat(itemA).isEqualTo(foundItem);
    }

    @Test
    void findAll() {
        //given
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 5000, 100);
        itemA = itemRepository.save(itemA);
        itemB = itemRepository.save(itemB);

        //when
        ArrayList<Item> items = itemRepository.findAll();

        //then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(itemA, itemB);
    }

    @Test
    void update() {
        //given
        Item itemA = new Item("itemA", 10000, 10);
        itemA = itemRepository.save(itemA);

        Item updateParam = new Item("itemB", 5000, 100);
        //when
        Item updatedItem = itemRepository.update(itemA.getId(), updateParam);

        //then
        assertThat(updatedItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(updatedItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(updatedItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}