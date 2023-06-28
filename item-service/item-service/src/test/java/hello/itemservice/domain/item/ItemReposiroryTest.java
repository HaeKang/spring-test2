package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemReposiroryTest {

    ItemReposirory itemReposirory = new ItemReposirory();

    @AfterEach
    void afterEach(){
        itemReposirory.clearStore();
    }

    @Test
    void save(){
        // given
        Item item = new Item("itemA", 10000, 10);

        // when
        Item saveItem = itemReposirory.save(item);

        // then
        Item findItem = itemReposirory.findById(item.getId());
        assertThat(findItem).isEqualTo(saveItem);
    }

    @Test
    void findAll(){
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemReposirory.save(item1);
        itemReposirory.save(item2);

        List<Item> result = itemReposirory.findAll();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);

    }

    @Test
    void updateItem(){
        Item item = new Item("item1", 10000, 10);
        Item savedItem = itemReposirory.save(item);
        Long itemId = savedItem.getId();

        Item updateParam = new Item("item2", 20000, 20);
        itemReposirory.update(itemId, updateParam);

        Item findItem = itemReposirory.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());

    }

}