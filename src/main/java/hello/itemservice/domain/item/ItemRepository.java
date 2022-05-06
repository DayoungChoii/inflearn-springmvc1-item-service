package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();
    private static Long seq = 0L;

    public Item save(Item item) {
        item.setId(++seq);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        Item foundItem = store.get(id);
        return foundItem;
    }

    public ArrayList<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public Item update(Long itemId, Item updateParam) {
        Item item = findById(itemId);

        item.setItemName(updateParam.getItemName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());

        return item;
    }

    public void clearStore() {
        store.clear();
    }
}
